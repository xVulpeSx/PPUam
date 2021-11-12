import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;


public class MenuetTest{

    Menuet underTest = new Menuet();

    WordList wordList = new WordList();

    @BeforeEach
    public void beforeEach(){
        List<String> polski = new ArrayList<>();
        List<String> angielski = new ArrayList<>();

        polski.add("piwo");
        polski.add("common");

        angielski.add("beer");
        angielski.add("common");

        Map<String, List<String>> contentFromJson = new HashMap<>();
        contentFromJson.put("polski", polski);
        contentFromJson.put("angielski", angielski);

        wordList.setWordList(contentFromJson);
    }


    @Test
    public void testWordToSearch_noResult(){
        String wordToSearch = "wordToSearch";

        List<String> result = underTest.searchForWordInWordList(wordToSearch, wordList);

        Assertions.assertEquals(0, result.size());
    }

    @Test
    void testWordToSearch_resultInOneLanguage(){
        String wordToSearch = "piwo";

        List<String> result = underTest.searchForWordInWordList(wordToSearch, wordList);

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("polski", result.get(0));
    }

    @Test
    void testWordToSearch_resultInMultipleLanguage(){
        String wordToSearch = "common";

        List<String> result = underTest.searchForWordInWordList(wordToSearch, wordList);

        Assertions.assertEquals(2, result.size());
    }

}
