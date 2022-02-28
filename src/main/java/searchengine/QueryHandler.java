package searchengine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * QueryHandler is responsible for answering
 * more complex queries from the search engine.
 * 
 * @author catj Catherine Temitayo Jørgensen
 * @author dmal Darinka Gordillo Maldonado
 * @author patbr Patricia Bresku
 */

public class QueryHandler {
  InvertedIndex invertedIndex;
  FrequencyScorer frequencyScorer;

  public QueryHandler(String filename) {
    frequencyScorer = new FrequencyScorer();
  }

  /**
   * @param query the query string
   * @return ArrayList<String>
   */
  public ArrayList<String> getWordsFromQuery(String query) {

    ArrayList<String> result = new ArrayList<String>();
    String[] queryWordsArray = query.split("\\s+OR\\s+");
    for (String currentQuery : queryWordsArray) {
      String[] words = currentQuery.split("%20");
      for (String currentWord : words) {
        result.add(currentWord);
      }
    }
    return result;
  }

  
  /** 
   * Gets single word queries from the user and adds it to the list.
   * 
   * @param query the query string
   * @return ArrayList<String>
   */
  public ArrayList<String> getSingleQueryWords(String query) {
    ArrayList<String> result = new ArrayList<String>();
    String[] words = query.split("%20");
    for (String currentWord : words) {
      result.add(currentWord.toLowerCase().trim());
    }
    return result;
  }

  /** 
   * @param query the query String
   * @param invertedIndex  
   * @return the list of websites that matches the searched multiple query words once.
   */
  public ArrayList<Website> intersectedQuery(String query, InvertedIndex invertedIndex) {

    ArrayList<Website> websites = new ArrayList<>();
    String regex = "%20OR%20";
    String[] queries = query.split(regex);

    // Split into distinct words and look up and calculate words
    for (String singleQueryString : queries) {

      ArrayList<String> queryWords = getSingleQueryWords(singleQueryString);
      ArrayList<Website> resultingWebsites = lookupAndCalculateScore(queryWords, invertedIndex);
      websites.addAll(resultingWebsites);
    }
    ArrayList<Website> distinctWebsites = removeDuplicates(websites);

    // Sort list
    distinctWebsites.sort(new FrequencyComparator());
    return distinctWebsites;
  }

  /** 
   * 
   * @param wordsToCheck
   * @param invertedIndex
   * @return list of websites
   */
  public ArrayList<Website> lookupAndCalculateScore(ArrayList<String> wordsToCheck, InvertedIndex invertedIndex) {
    ArrayList<Website> result = new ArrayList<>();
    HashSet<Website> setOfWebsites = invertedIndex.lookup(wordsToCheck);
    result.addAll(setOfWebsites); 

    for (Website website : result) {
      frequencyScorer.calculateWebsiteScore(website, wordsToCheck);
    }
    return result;
  }

  
  /** 
   * Removes duplicates by first storing the list into a LinkedHashSet, 
   * which doesn't allow duplicates, and then convert this back to ArrayList.
   * 
   * @param websites list with duplicates.
   * @return ArrayList without duplicates.
   */
  private ArrayList<Website> removeDuplicates(ArrayList<Website> websites) {

    Set<Website> set = new LinkedHashSet<>();
    set.addAll(websites);
    websites.clear();
    websites.addAll(set);
    return websites;
  }
}