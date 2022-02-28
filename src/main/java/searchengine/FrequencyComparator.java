package searchengine;
import java.util.Comparator;
/**
 * FrequencyComparator
 * 
 * @author Catherine Temitayo Jørgensen catj@itu.dk
 * @author Darinka Gordillo Maldonado dmal@itu.dk
 * @author Patricia Bresku patbr@itu.dk
 */

public class FrequencyComparator implements Comparator<Website> {
    
    /** 
     * @param 
     * @param
     * @return int
     */

    //interface class 
    
    public int compare(Website website1, Website website2) {
        return website1.getScore()-website2.getScore();
    }
    
}
