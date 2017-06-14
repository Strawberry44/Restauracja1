/**
 * Created by Grzeg on 12.06.2017.
 */
public class Finanse {
    private Klient[] klientTab;
    //private double cenaZaObiad;
    private String fileNameKlienci = "klienci.txt";
    private FileIO fileIO;

    Finanse(final int rozmiar)
    {
        fileIO = new FileIO();
        klientTab = new Klient[rozmiar];
        for (int i = 0; i < klientTab.length; i++) {
            klientTab[i] = new Klient();
        }
        fileIO.wczytajOsoby(klientTab, fileNameKlienci);
    }

    public Klient znajdzKlienta(String imie, String nazwisko)
    {
        for (int i = 0; i < klientTab.length; i++) {
            if(imie.equals(klientTab[i].getImie()))
            {
                if(nazwisko.equals(klientTab[i].getNazwisko()))
                    return klientTab[i];
            }

        }
        return null;

    }
    public void aktualizujListKlientow()    //zapisuje do pliku txt aktualna tablice klientow
    {
        fileIO.zapisDoPliku(klientTab, fileNameKlienci);
    }
}
