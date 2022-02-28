package searchengine;

public interface Scorer {

    public int getScore(Website website, String query);
}

