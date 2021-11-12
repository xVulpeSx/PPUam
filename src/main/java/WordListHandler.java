import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.Scanner;

@NoArgsConstructor
public class WordListHandler {
    private Gson gson = new Gson();
    private String basePathName = "data.json";
    JsonReader reader;

    public WordList getWordLists(){

        File file = new File(basePathName);
        Scanner reader;
        String data= "";
        try {
            reader = new Scanner(file);
            while (reader.hasNextLine()){
                data+=reader.nextLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return gson.fromJson(data, WordList.class);
    }

    public void addLanguage(String pathName){
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(pathName);

        File file = new File(pathName);
        Scanner reader;
        String newData= "";
        try {
            reader = new Scanner(file);
            while (reader.hasNextLine()){
                newData+=reader.nextLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        WordList listToAdd =  gson.fromJson(newData, WordList.class);
        WordList wordList = getWordLists();

        listToAdd.getWordList().entrySet().forEach(list ->{
            wordList.getWordList().put(list.getKey(), list.getValue());
        });

        String jsonString = gson.toJson(wordList);
        try {
            FileWriter fw = new FileWriter(new File(basePathName), false);
            fw.write(jsonString);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
