import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.OngoingStubbing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;


public class MenuetTest{

    WordListHandler wordListHandler = mock(WordListHandler.class);

    Menuet underTest = new Menuet(wordListHandler);

    WordList wordList = new WordList();

    @BeforeAll
    void beforeAll(){
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
    void testWordToSearch_noResult(){
        String wordToSearch = "wordToSearch";

        when(wordListHandler.getWordLists()).thenReturn(wordList);

        List<String> result = underTest.getLanguages(wordToSearch);

        Assertions.assertEquals(0, result.size());
    }

//    @Test
//    void testWordToSearch_resultInOneLanguage(){
//        String wordToSearch = "piwo";
//
//        when(wordListHandler.getWordLists())
//                .then((Answer<?>) wordList);
//
//        List<String> result = underTest.getLanguages(wordToSearch);
//
//        Assertions.assertEquals(1, result.size());
//        Assertions.assertEquals("polski", result.get(0));
//    }

}
