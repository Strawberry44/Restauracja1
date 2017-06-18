import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Created by Grzeg on 12.06.2017.
 */
public class Menu {
    private Danie danieTab[];
    private FileIO odczyt;
    public void wyswietlDania()
    {
        System.out.println("-----------------------");
        System.out.println("Menu:");
        for (int i = 0; i < danieTab.length; i++) {
            System.out.println(danieTab[i].toString());
        }
        System.out.println("danieDnia zwraca losowe danie");
        System.out.println("-----------------------");

    }
    public Danie danieDnia()
    {
        Random rand = new Random();
        int temp = rand.nextInt(danieTab.length);
        return danieTab[temp];
    }
    public Danie czyIstnieje(String nazwa) throws NoSuchElementException
    {
        for (int i = 0; i < danieTab.length; i++) {
            if(nazwa.equals(danieTab[i].getNazwa()))
                return danieTab[i];
            else if(nazwa.equals("danieDnia")) {
                Danie d =  danieDnia();
                System.out.println("Wylosowano: "+ d.toString());
                return d;
            }
        }
        throw new NoSuchElementException();
    }
    Menu(final int rozmiar){
        danieTab = new Danie[rozmiar];
        odczyt = new FileIO();
        odczyt.wczytajDania(danieTab);
    }
}
