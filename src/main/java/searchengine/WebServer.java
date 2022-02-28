package searchengine;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

/**
 * WebServer
 * 
 * @author Catherine Temitayo Jørgensen catj@itu.dk
 * @author Darinka Gordillo Maldonado dmal@itu.dk
 * @author Patricia Bresku patbr@itu.dk
 */

public class WebServer {
  static final int PORT = 8080;
  static final int BACKLOG = 0;
  static final Charset CHARSET = StandardCharsets.UTF_8;

  HttpServer server;
  SearchEngine searchEngine;
  

  WebServer(int port, String filename) throws IOException {
    searchEngine = new SearchEngine(filename);
    createContext(port);
    printMessage(port);
  }

  /**
   * @param port
   * @throws IOException
   */
  private void createContext(int port) throws IOException {
    server = HttpServer.create(new InetSocketAddress(port), BACKLOG);
    server.createContext("/", io -> respond(io, 200, "text/html", getFile("web/index.html")));
    server.createContext("/search", io -> search(io));
    server.createContext(
        "/favicon.ico", io -> respond(io, 200, "image/x-icon", getFile("web/favicon.ico")));
    server.createContext(
        "/code.js", io -> respond(io, 200, "application/javascript", getFile("web/code.js")));
    server.createContext(
        "/style.css", io -> respond(io, 200, "text/css", getFile("web/style.css")));
    server.start();
  }

  /**
   * @param port
   */
  void printMessage(int port) {
    String msg = " WebServer running on http://localhost:" + port + " ";
    System.out.println("╭" + "─".repeat(msg.length()) + "╮");
    System.out.println("│" + msg + "│");
    System.out.println("╰" + "─".repeat(msg.length()) + "╯");
  }

  /**
   * @param io
   */
  void search(HttpExchange io) {
    String searchTerm = io.getRequestURI().getRawQuery().split("=")[1];
    ArrayList<String> response = new ArrayList<String>();
   
    ArrayList<Website> websites = searchEngine.search(searchTerm);

    for (Website page : websites) {
      response.add(String.format("{\"url\": \"%s\", \"title\": \"%s\"}",
          page.getUrl(), page.getTitle()));
    }

    Collections.reverse(response);

    var bytes = response.toString().getBytes(CHARSET);
    respond(io, 200, "application/json", bytes);

  }

  /**
   * @param filename
   * @return byte
   */
  byte[] getFile(String filename) {
    try {
      return Files.readAllBytes(Paths.get(filename));
    } catch (IOException e) {
      e.printStackTrace();
      return new byte[0];
    }
  }

  /**
   * @param io
   * @param code
   * @param mime
   * @param response
   */
  void respond(HttpExchange io, int code, String mime, byte[] response) {
    try {
      io.getResponseHeaders()
          .set("Content-Type", String.format("%s; charset=%s", mime, CHARSET.name()));
      io.sendResponseHeaders(200, response.length);
      io.getResponseBody().write(response);
    } catch (Exception e) {
    } finally {
      io.close();
    }
  }

  /**
   * @param args
   * @throws IOException
   */
  public static void main(final String... args) throws IOException {
    String filename = Files.readString(Paths.get("config.txt")).strip();
    new WebServer(PORT, filename);
  }
}