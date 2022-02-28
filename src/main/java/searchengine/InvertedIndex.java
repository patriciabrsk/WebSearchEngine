package searchengine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * InvertedIndex processes the data and instantiates Website objects.
 * 
 * @author Catherine Temitayo Jørgensen catj@itu.dk
 * @author Darinka Gordillo Maldonado dmal@itu.dk
 * @author Patricia Bresku patbr@itu.dk
 */

public class InvertedIndex {

    HashMap<String, HashSet<Website>> data;
    List<Website> listOfPages;

    public InvertedIndex(List<Website> pages) {

        this.listOfPages = pages;
        data = new HashMap<String, HashSet<Website>>();
        populateIndex();
    }

    public HashSet<Website> lookup(String word) {

        if (data.containsKey(word)) {
            return data.get(word);

        } else {

            return new HashSet<Website>();
        }
    }

    /**
     * Look up an array of words. Returns a HashSet of websites that contain all the
     * words in the input array, else returns an empty array.
     *
     * @param words the array list of words to search.
     * @return 
     */

    public HashSet<Website> lookup(ArrayList<String> words) {
        HashSet<Website> initialSet = lookup(words.get(0));
        for (int i = 1; i < words.size(); i++) {
            String word = words.get(i);
        if (data.containsKey(word) && lookup(word).size() > 0){
            initialSet.retainAll(lookup(word));
        }else{
            initialSet.clear();
            break;
        }

        return initialSet;
    }

    /**
     * Gets keys from the data and adds the corresponding websites to the hashmap.
     * 
     * @return data which is a {@link HashMap} collection that contains
     *         {@link String} as key and {@link HashSet} of Website as value
     *         of websites containing the query word
     */
    public HashMap<String, HashSet<Website>> populateIndex() {
        for (Website website : listOfPages) {
            for (String word : website.getWords()) {
                if (data.containsKey(word)) {
                    data.get(word).add(website);
                } else {
                    HashSet<Website> websiteset = new HashSet<Website>();
                    websiteset.add(website);
                    data.put(word, websiteset);
                }
            }
        }
        return data;
    }

    /**
     * @return data
     */
    public HashMap<String, HashSet<Website>> getData() {
        return data;
    }
}
