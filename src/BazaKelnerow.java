import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by Grzeg on 12.06.2017.
 */
public class BazaKelnerow {

    private List<Kelner> kelnerzy;

    private Klient aktualnyKlient;
    private FileIO wczytaj;
    private String fileNameKelnerzy = "kelnerzy.txt";

    public BazaKelnerow(final int rozmiar) {
        wczytaj = new FileIO();
        kelnerzy = new ArrayList<>();


        wczytaj.wczytajKelnerow(kelnerzy, fileNameKelnerzy);
    }

    public BazaKelnerow() {

    }



    private Kelner znajdzKelnera(String nazwaKel) throws NoSuchElementException {
        for (int i = 0; i < kelnerzy.size(); i++) {
            if (nazwaKel.equals(kelnerzy.get(i).getImie()))
                return kelnerzy.get(i);
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

        if (!kelner.isCzyWolny()) {
            System.out.println("BazaKelnerow " + nameKelnera + " jest gotowy do obslugi nowych klientow");
            kelner.setCzyWolny(true);

        } else{
            System.out.println("BazaKelnerow " + nameKelnera + " nie jest zajety");
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

        System.out.println("Bedzie cie oblugiwal " + aktualnyKelner.getImie()
                + " posiada " + aktualnyKelner.getStaz() + " lat doswiadczenia.");


        Scanner in = new Scanner(System.in);
        String imie;
        String nazwisko;

        System.out.println();
        System.out.print("*" + aktualnyKelner.getImie() + "* Wprowadz imie klienta: ");
        imie = in.nextLine();
        System.out.print("*" + aktualnyKelner.getImie() + "* Wprowadz nazwisko: ");
        nazwisko = in.nextLine();

        try {
            aktualnyKlient = finanse.znajdzKlienta(imie, nazwisko);
        } catch (NoSuchElementException e) {
            System.out.println("*" + aktualnyKelner.getImie() + "* Nie znaleziono w tablicy klientow");

            return false;
        }

        menu.wyswietlDania();

        String nazwaWybranegoDania = aktualnyKlient.zamow();
        Danie wybraneDanie;

        try {
            wybraneDanie = menu.czyIstnieje(nazwaWybranegoDania);
        } catch (NoSuchElementException e) {
            System.out.println("*" + aktualnyKelner.getImie() + "* Nie mamy podanego dania w menu");

            return false;
        }

        if (wybraneDanie.getCena() > aktualnyKlient.getIloscGotowki()) {
            System.out.println("*" + aktualnyKelner.getImie() + "* Nie masz odpowiednio duzo gotowki  koszt:" + //
                    wybraneDanie.getCena() + " masz: " + aktualnyKlient.getIloscGotowki());
        } else {
            podajDanie(nazwaWybranegoDania, aktualnyKelner.getImie());
            aktualnyKelner.setCzyWolny(false);
            aktualnyKlient.zmniejGotowke(wybraneDanie.getCena());
        }


        return true;
    }

    private Kelner dajWolnegoKelnera() throws NoSuchElementException {
        for(Kelner it: kelnerzy) {
            if (it.isCzyWolny())
                return it;

        }

        throw new NoSuchElementException();
    }

    private void podajDanie(String nazwaDania, String nazwaKelnera) {
        System.out.println("*" + nazwaKelnera + "* Danie " + nazwaDania + " podane do stolu");
        System.out.println("BazaKelnerow " + nazwaKelnera + " czeka az zaplacisz");
        System.out.println();
    }






}
