package searchengine;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.TestInstance.Lifecycle;

import java.util.ArrayList;
import java.util.List;


@TestInstance(Lifecycle.PER_CLASS)

public class QueryHandlerTest {

    String path;
    FileReader reader;
    List<Website> websites;
    InvertedIndex invertedIndex;
    QueryHandler queryHandler;
    SearchEngine searchEngine;

    @BeforeAll
    public void setUp() {
        path = "data/test-file.txt";
        reader = new FileReader(path);
        websites = reader.getPages();
        invertedIndex  = new InvertedIndex(websites);
        queryHandler = new QueryHandler(path);
        searchEngine = new SearchEngine(path);
    }

    @Test
    public void testORSize(){ 
        ArrayList<String> a = queryHandler.getWordsFromQuery("word1 OR word2");
        ArrayList<String> b = queryHandler.getWordsFromQuery("word1 OR word2 OR word3");

        assertEquals(2 , a.size());
        assertEquals(3 , b.size());

    }

    @Test
    public void testSetsSize(){ 
        ArrayList<String> a = queryHandler.getWordsFromQuery("Title1 word1 OR Title1 word2");
        ArrayList<String> b = queryHandler.getWordsFromQuery("Title1 word1 word2 word3 OR Title1 word2 OR Title2 word2");

        assertEquals(2 , a.size());
        assertEquals(3 , b.size());

    }

    // @Test
    // public void testNoMatchFound(){
    //     ArrayList<String> a = queryHandler.getWordsFromQuery("word4%20word5%20word6");
    //     assertEquals(0 , searchEngine.searchList(a).size());
    // }

    
}
