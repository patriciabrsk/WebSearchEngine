package searchengine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * FileReader is responsible for reading a database of websites
 * from a file.
 * 
 * @author Catherine Temitayo Jørgensen catj@itu.dk
 * @author Darinka Gordillo Maldonado dmal@itu.dk
 * @author Patricia Bresku patbr@itu.dk
 */

public class FileReader {
    List<String> lines;
    List<Website> pages;
    String filename;

    /**
     * FileReader initialises a FileReader with the given filename.
     * 
     * @param filename The name of the file we want to load.
     *                 Directory path has to be included.
     */
    FileReader(String filename) {
        this.filename = filename;
        pages = new ArrayList<>();
        readLines();
    }

    /**
     * Getter method that returns the list of pages.
     * 
     * @return pages The list of pages.
     */
    public List<Website> getPages() {
        return pages;
    }

    /**
     * Reads all the lines from the file and adds them to the list.
     */
    public void readLines() {

        try {
            lines = Files.readAllLines(Paths.get(filename));
            int lastIndex = lines.size();
            for (int i = lines.size() - 1; i >= 0; --i) {
                if (lines.get(i).startsWith("*PAGE")) {
                    String url = lines.get(i);
                    url = url.replace("*PAGE:", "");
                    String title = lines.get(i + 1);
                    List<String> words = lines.subList(i + 2, lastIndex);

                    if (words.size() > 0) {
                        Website website = new Website(url, title, words);
                        pages.add(website);
                    }
                    lastIndex = i;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.reverse(pages);
    }

}
