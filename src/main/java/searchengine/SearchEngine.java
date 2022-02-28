package searchengine;

import java.util.ArrayList;
import java.util.List;

/**
 * SearchEngine is responsible for taking the input
 *
 * @author Catherine Temitayo Jørgensen catj@itu.dk
 * @author Darinka Gordillo Maldonado dmal@itu.dk
 * @author Patricia Bresku patbr@itu.dk
 */

public class SearchEngine {
  private FileReader reader;
  private List<Website> pages;
  private InvertedIndex invertedIndex;
  private QueryHandler queryHandler;

  /**
   * SearchEngine initializes an object of type FileReader that reads a file.
   * 
   * @param filename
   */

  public SearchEngine(String filename) {

    reader = new FileReader(filename);
    pages = reader.getPages();
    invertedIndex = new InvertedIndex(pages);
    queryHandler = new QueryHandler(filename);
  }

  public ArrayList<Website> search(String searchTerm) {
    return queryHandler.intersectedQuery(searchTerm, invertedIndex);

  }
}
