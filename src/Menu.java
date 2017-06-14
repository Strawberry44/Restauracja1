import java.util.Random;

/**
 * Created by Grzeg on 12.06.2017.
 */
public class Menu {
    private Danie danieTab[];
    private FileIO odczyt;
    public void wyswietlDania()
    {
        System.out.println();
        System.out.println("Dostepne dania:");
        for (int i = 0; i < danieTab.length; i++) {
            System.out.println(danieTab[i]);
        }
    }
    public Danie danieDnia()
    {
        Random rand = new Random();
        int temp = rand.nextInt(danieTab.length);
        return danieTab[temp];
    }
    public Danie czyIstnieje(String nazwa)
    {
        for (int i = 0; i < danieTab.length; i++) {
            if(nazwa.equals(danieTab[i].getNazwa()))
                return danieTab[i];
        }
        return null;
    }
    Menu(final int rozmiar){
        danieTab = new Danie[rozmiar];
        odczyt = new FileIO();
        odczyt.wczytajDania(danieTab);
    }
}
