import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Menuet makeEveryThingDone = new Menuet();

    public void scanAndRun(){
        int option = 1;
        Scanner in = new Scanner(System.in);

        while(option != 0){
            System.out.println("\n\nwitaj!\n " +
                    "powiedz co chcesz zrobic?\n" +
                    "1 - przeszukaj slownik\n" +
                    "2 - dodaj slownik z pliku\n" +
                    "0 - zakonczenie dzialania programu");

            option = in.nextInt();

            if(option == 1){
                System.out.println("Podaj slowo do przeszukania: ");
                String word = new Scanner(System.in).nextLine();
                List<String> list = makeEveryThingDone.getLanguages(word);
                if(list.isEmpty()){
                    System.out.println("Nie znaleziono");
                }else{
                    System.out.println("Znaleziono slowo w jezykach:");
                    System.out.println(list);
                }
                option = 1;
            }else if(option == 2){
                makeEveryThingDone.addLanguage("newData.json");
                System.out.println("dodano!\n");
            }else{
                try {
                    makeEveryThingDone.addToPdf();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
