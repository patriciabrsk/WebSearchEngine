package searchengine;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.TestInstance.Lifecycle;

import java.util.List;
import java.util.Arrays;

@TestInstance(Lifecycle.PER_CLASS)
public class InvertedIndexTest {
    FileReader reader;
    List<Website> websites;
    InvertedIndex iIndex;
    
    @BeforeAll
    public void setUp() {
        reader = new FileReader("data/test-file.txt");
        websites = reader.getPages();
        iIndex = new InvertedIndex(websites);
    }
    @Test
    public void testKeys() {
        assertEquals(websites.get(1).getWords(), Arrays.asList("word1", "word3"));
    }

    @Test
    public void testLookUp(){
        assertEquals(2, iIndex.lookup("word1").size());
        assertEquals(1, iIndex.lookup("word2").size());
        assertEquals(1, iIndex.lookup("word3").size());
    }
}