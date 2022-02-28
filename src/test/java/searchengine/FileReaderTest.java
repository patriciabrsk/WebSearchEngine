package searchengine;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.TestInstance.Lifecycle;

import org.junit.jupiter.api.TestInstance;

import java.util.List;

@TestInstance(Lifecycle.PER_CLASS)
public class FileReaderTest {

    FileReader reader;
    List<Website> websites;

    @BeforeAll
    public void setUp() {
        reader = new FileReader("data/test-file.txt");
        websites = reader.getPages();
    }

    @Test
    void testWrongPageExcluded() {
        assertEquals(2, websites.size());
    }

    @Test
    void testSpecificTitle() {
        assertEquals("title1", websites.get(0).getTitle());
    }

    @Test
    void testSpecificUrl() {
        assertEquals("http://page1.com", websites.get(0).getUrl());
    }

    @Test
    void testSpecificWords() {
        assertEquals("word1", websites.get(0).getWords().get(0));
    }

    @Test
    void isEmptyTest()  {
        assertFalse("data/test-file.txt".isEmpty());
    }

}
