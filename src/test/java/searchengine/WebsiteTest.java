package searchengine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.TestInstance.Lifecycle;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;
import java.util.ArrayList;

@TestInstance(Lifecycle.PER_CLASS)
public class WebsiteTest {

    List<String> listOfWords;
    Website website;
    
    @BeforeAll
    public void setUp(){
        listOfWords = new ArrayList<>();
        listOfWords.add("word3");
        website = new Website("www.itu.dk", "ITU" , listOfWords);
    }
    
    @Test
    public void TitleTest(){
        assertEquals("ITU", website.getTitle());
    }

    @Test
    public void UrlTest(){
        assertEquals("www.itu.dk", website.getUrl());
    }
    
    @Test
    public void wordsTest(){
        assertEquals(listOfWords, website.getWords());
    }
}
