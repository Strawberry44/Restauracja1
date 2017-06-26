import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by Grzeg on 12.06.2017.
 */
public class Kelner {
    private String imie;
    private int staz;
    private boolean czyWolny;
    private static Kelner[] kelnerTab;
    private Klient aktualnyKlient;
    private FileIO wczytaj;
    private String fileNameKelnerzy = "kelnerzy.txt";

    public Kelner(final int rozmiar) {
        wczytaj = new FileIO();
        kelnerTab = new Kelner[rozmiar];
        for (int i = 0; i < kelnerTab.length; i++) {
            kelnerTab[i] = new Kelner();
        }

        wczytaj.wczytajKelnerow(kelnerTab, fileNameKelnerzy);
    }

    public Kelner() {

    }

    public void wypelnij(String... args) {
        imie = args[0];
        staz = Integer.parseInt(args[1]);
        czyWolny = true;
    }

    private Kelner znajdzKelnera(String nazwaKel) throws NoSuchElementException {
        for (int i = 0; i < kelnerTab.length; i++) {
            if (nazwaKel.equals(kelnerTab[i].imie))
                return kelnerTab[i];
        }

        throw new NoSuchElementException();
    }

    public void uwolnijKelnera() {

        Scanner sc = new Scanner(System.in);
        System.out.print("Wprowadz imie kelnera ktorego chcesz uwolnic: ");
        String nameKelnera = sc.nextLine();
        Kelner kelner;

        try {
            kelner = znajdzKelnera(nameKelnera);
        }
        catch (NoSuchElementException e)
        {
            System.out.println("Nie znaleziono kelnera o podanej nazwie");
            return;
        }

        if (!kelner.czyWolny) {
            System.out.println("Kelner " + nameKelnera + " jest gotowy do obslugi nowych klientow");
            kelner.setCzyWolny(true);

        } else{
            System.out.println("Kelner " + nameKelnera + " nie jest zajety");
        }
    }

    public boolean przyjmijZamowienie(Menu menu, Finanse finanse) {
        System.out.println();
        System.out.println("* Przyjmowanie zamowienia *");
        Kelner aktualnyKelner;
        try {
            aktualnyKelner = dajWolnegoKelnera();
        } catch (NoSuchElementException e) {
            System.out.println("Aktualnie wszyscy kelnerzy sa zajeci.");
            return false;
        }

        System.out.println("Bedzie cie oblugiwal " + aktualnyKelner.imie
                + " posiada " + aktualnyKelner.staz + " lat doswiadczenia.");


        Scanner in = new Scanner(System.in);
        String imie;
        String nazwisko;

        System.out.println();
        System.out.print("*" + aktualnyKelner.imie + "* Wprowadz imie klienta: ");
        imie = in.nextLine();
        System.out.print("*" + aktualnyKelner.imie + "* Wprowadz nazwisko: ");
        nazwisko = in.nextLine();

        try {
            aktualnyKlient = finanse.znajdzKlienta(imie, nazwisko);
        } catch (NoSuchElementException e) {
            System.out.println("*" + aktualnyKelner.imie + "* Nie znaleziono w tablicy klientow");

            return false;
        }

        menu.wyswietlDania();

        String nazwaWybranegoDania = aktualnyKlient.zamow();
        Danie wybraneDanie;

        try {
            wybraneDanie = menu.czyIstnieje(nazwaWybranegoDania);
        } catch (NoSuchElementException e) {
            System.out.println("*" + aktualnyKelner.imie + "* Nie mamy podanego dania w menu");

            return false;
        }

        if (wybraneDanie.getCena() > aktualnyKlient.getIloscGotowki()) {
            System.out.println("*" + aktualnyKelner.imie + "* Nie masz odpowiednio duzo gotowki  koszt:" + //
                    wybraneDanie.getCena() + " masz: " + aktualnyKlient.getIloscGotowki());
        } else {
            podajDanie(nazwaWybranegoDania, aktualnyKelner.imie);
            aktualnyKelner.czyWolny = false;
            aktualnyKlient.zmniejGotowke(wybraneDanie.getCena());
        }


        return true;
    }

    private Kelner dajWolnegoKelnera() throws NoSuchElementException {
        for (int i = 0; i < kelnerTab.length; i++) {
            if (kelnerTab[i].czyWolny)
                return kelnerTab[i];

        }

        throw new NoSuchElementException();
    }

    private void podajDanie(String nazwaDania, String nazwaKelnera) {
        System.out.println("*" + nazwaKelnera + "* Danie " + nazwaDania + " podane do stolu");
        System.out.println("Kelner " + nazwaKelnera + " czeka az zaplacisz");
        System.out.println();
    }

    public void setCzyWolny(boolean s) {
        czyWolny = s;
    }

    public String toString() {
        return imie + " " + staz;
    }


}
