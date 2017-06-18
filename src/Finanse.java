import java.util.NoSuchElementException;

/**
 * Created by Grzeg on 12.06.2017.
 */
public class Finanse {

    private Klient[] klientTab; //nie uzywamy kod usuwamy
    private String fileNameKlienci = "klienci.txt";
    private FileIO fileIO;

    Finanse(final int rozmiar)
    {
        fileIO = new FileIO();
        klientTab = new Klient[rozmiar];

        fileIO.wczytajKlientow(klientTab, fileNameKlienci);
    }

    public Klient znajdzKlienta(String imie, String nazwisko) throws NoSuchElementException {
        for (int i = 0; i < klientTab.length; i++) {//pamietaj o przeciazaniu equlas hashcode
            if(imie.equals(klientTab[i].getImie()))
            {//klamry
                if(nazwisko.equals(klientTab[i].getNazwisko())) {
                    return klientTab[i];
                }
            }

        }
        //zwracanie nulla jest slabe
        throw new NoSuchElementException();
    }
    public void aktualizujListKlientow()
    {
        fileIO.zapisDoPliku(klientTab, fileNameKlienci);
    }
}
