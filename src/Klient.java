import java.util.Scanner;

/**
 * Created by Grzeg on 12.06.2017.
 */
public class Klient{
    private String imie;
    private String nazwisko;
    private double iloscGotowki;


    public void wypelnij(String... args) {
        imie = args[0];
        nazwisko = args[1];
        iloscGotowki = Double.parseDouble(args[2]);
    }
    public String toString()
    {
        return imie +" " + nazwisko+" "+iloscGotowki;
    }

    public String zamow()
    {
        Scanner in = new Scanner(System.in);
        System.out.println();
        System.out.print("*"+imie + "* Chce zamowic: ");
        return in.nextLine();
    }
    public void zmniejGotowke(double kwota)
    {
        System.out.println("Klient "+ imie +" " + nazwisko+
        " placi "+ kwota + "zl");
        iloscGotowki -= kwota;
        System.out.println("Aktualna ilosc gotowki wynosi: "+ (iloscGotowki+kwota)+" - "+kwota+" = "+iloscGotowki);
    }


    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public double getIloscGotowki() {
        return iloscGotowki;
    }

}
