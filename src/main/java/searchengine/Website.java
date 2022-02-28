package searchengine;

import java.util.List;

/**
 * Website is the fundamental element of a search engine.
 * It has a url, a title, and a list of words.
 * 
 * @author Catherine Temitayo Jørgensen catj@itu.dk
 * @author Darinka Gordillo Maldonado dmal@itu.dk
 * @author Patricia Bresku patbr@itu.dk
 */

public class Website {
    String title;
    String url;
    List<String> words;
    int score;

    /**
     * Creates a {@code Website} object from a url, a title, and a list of words
     * contained on the website.
     * 
     * @param url   the website's url
     * @param title the website's title
     * @param words the website's list of words
     */
    public Website(String url, String title, List<String> words) {
        this.url = url;
        this.title = title;
        this.words = words;
        this.score = 0;
    }

    /**
     * @return website's url.
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return website's title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the list of words contained in the website.
     */
    public List<String> getWords() {
        return words;
    }

    /**
     * Checks if a word is in the website.
     * 
     * @param searchTerm the query word
     * @return true if the word is in the website.
     */
    Boolean containsWord(String searchTerm) {
        return words.contains(searchTerm);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    // public int getFrequency(String word) {
    // // handle case where word doesn't exist in the map
    // return wordFrequency.get(word);
    // }

}
