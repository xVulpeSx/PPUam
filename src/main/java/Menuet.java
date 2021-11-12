import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class Menuet {
    private WordListHandler wordListHandler = new WordListHandler();
    private int count = 0;
    private List<String> listOfLanguages = new ArrayList<>();

    public WordList getWordLists(){
        return wordListHandler.getWordLists();
    }

    public List<String> searchForWordInWordList(String wordToSearch, WordList wordLists){
        List<String> result = new ArrayList<>();

//        wordLists.getWordList().entrySet().forEach(list ->{
//            for(String word : list.getValue()){
//                if(word.equals(wordToSearch)){
//                    result.add(list.getKey());
//                }
//            }
//        });

        wordLists.getWordList()
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().contains(wordToSearch))
                .forEachOrdered(entry -> result.add(entry.getKey()));

        counter(result);

        return result;
    }

    public void addLanguage(String pathName){
        wordListHandler.addLanguage(pathName);
    }

    private void counter(List<String> newList){
        count++;

        for(String language : newList){
            if(!listOfLanguages.contains(language)){
                listOfLanguages.add(language);
            }
        }
    }

    private String formatString(){
        String result = "Zapytano:" + count + " razy. Znalezione jezyki to: ";

        for(String language : listOfLanguages){
            result += language + ", ";
        }

        return result;
    }

    public void addToPdf() throws FileNotFoundException, DocumentException {
        Document document = new Document();

        PdfWriter.getInstance(document, new FileOutputStream("dataPdf.pdf"));

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK);
        Chunk chunk = new Chunk(formatString(), font);

        document.add(chunk);
        document.close();
    }

    public Menuet(WordListHandler wordListHandler) {
        this.wordListHandler = wordListHandler;
    }
}
