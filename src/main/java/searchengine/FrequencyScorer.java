package searchengine;

import java.util.ArrayList;

/**
 * FrequencyScorer computes a score using a website and a given query.
 * 
 * @author Catherine Temitayo Jørgensen catj@itu.dk
 * @author Darinka Gordillo Maldonado dmal@itu.dk
 * @author Patricia Bresku patbr@itu.dk
 */

public class FrequencyScorer {

    /**
     * Calculates the score for each website based on the query. 
     * 
     * @param website is a list that only contains the websites that contain the query.
     * @param query the array list that contains the query terms inserted from the user.
     */

    public void calculateWebsiteScore(Website website, ArrayList<String> query) {
        int queryScore = 0;
        for (String currentWord : query) {
            int count = 0;
            for (String word : website.getWords()) {
                if (word.toLowerCase().equals(currentWord))
                    count++;
            }
            queryScore += count;
        }
        int websiteScore = website.getScore() + queryScore;
        website.setScore(websiteScore);
    }
}
