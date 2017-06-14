import java.util.Scanner;

/**
 * Created by Grzeg on 12.06.2017.
 */
public class Kelner extends Osoba {
    private int staz;
    private boolean czyWolny;
    private static Kelner[] kelnerTab;
    private FileIO odczyt;
    private Klient aktualnyKlient;
    private FileIO wczytaj;
    private String fileNameKelnerzy = "kelnerzy.txt";

    @Override
    public void wypelnij(String...args) {
        imie = args[0];
        staz = Integer.parseInt(args[1]);
        czyWolny = true;
    }

    public boolean przyjmijZamowienie(Menu menu, Finanse finanse)
    {
        System.out.println();
        System.out.println("* Przyjmowanie zamowienia *");
        Kelner aktualnyKelner = dajWolnegoKelnera();
        if(aktualnyKelner == null) {
            System.out.println("Aktualnie wszyscy kelnerzy sa zajeci.");
            return false;
        }else
        {
            System.out.println("Bedzie cie oblugiwal "+aktualnyKelner.imie
            +" posiada "+ aktualnyKelner.staz + " lat doswiadczenia.");
        }


        Scanner in = new Scanner(System.in);
        String imie, nazwisko;
        System.out.println();
        System.out.println(aktualnyKelner.imie+"| Wprowadz imie klienta: ");
        imie = in.nextLine();
        System.out.println(aktualnyKelner.imie+"* Wprowadz nazwisko: ");
        nazwisko = in.nextLine();

        aktualnyKlient = finanse.znajdzKlienta(imie, nazwisko);
        if(aktualnyKlient == null)
        {
            System.out.println(aktualnyKelner.imie+"* Nie znaleziono w tablicy klientow");
            return false;
        }
        else
        {
            String nazwaWybranegoDania = aktualnyKlient.zamow();
            Danie wybraneDanie;
            wybraneDanie = menu.czyIstnieje(nazwaWybranegoDania);
            if(wybraneDanie == null)
            {
                System.out.println(aktualnyKelner.imie+"* Nie mamy podanego dania w menu");
                return false;
            }
            else
            {
                if(wybraneDanie.getCena() > aktualnyKlient.getIloscGotowki())
                {
                    System.out.println(aktualnyKelner.imie+"* Nie masz odpowiednio duzo gotowki  koszt:" +wybraneDanie.getCena() +" masz: "+aktualnyKlient.getIloscGotowki());
                }
                else
                {
                    podajDanie(nazwaWybranegoDania, aktualnyKelner.imie);
                    aktualnyKelner.czyWolny = false;
                    aktualnyKlient.zmniejGotowke(wybraneDanie.getCena());
                }
            }
        }

        return true;
    }

    private Kelner dajWolnegoKelnera()
    {
        for (int i = 0; i < kelnerTab.length; i++) {
            if(kelnerTab[i].czyWolny == true)
                return kelnerTab[i];

        }
        return null;
    }

    private void podajDanie(String nazwaDania, String nazwaKelnera)
    {
        System.out.println(nazwaKelnera+" Danie "+nazwaDania+" podane do stolu");
        System.out.println("Kelner "+nazwaKelnera + " czeka az zaplacisz");
        System.out.println();
    }
    public void setCzyWolny(boolean s)
    {
        czyWolny = s;
    }
    public String toString()
    {
        return imie +" " + staz;
    }

    public Kelner(final int rozmiar)
    {
        wczytaj = new FileIO();
        kelnerTab = new Kelner[rozmiar];
        for (int i = 0; i < kelnerTab.length; i++) {
            kelnerTab[i] = new Kelner();
        }

        wczytaj.wczytajOsoby(kelnerTab, fileNameKelnerzy);
    }
    public Kelner()
    {

    }

}
