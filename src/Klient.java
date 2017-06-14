import java.util.Scanner;

/**
 * Created by Grzeg on 12.06.2017.
 */
public class Klient extends Osoba {
    private String nazwisko;
    private double iloscGotowki;

    @Override
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
        System.out.println(imie + " Chce zamowic: ");
        return in.nextLine();
    }
    public void zmniejGotowke(double kwota)
    {
        System.out.println("Klient "+ imie +" " + nazwisko+
        " placi "+ kwota + "zl");
        iloscGotowki -= kwota;
        System.out.println("Aktualna ilosc gotowki wynosi: "+ (iloscGotowki+kwota)+" - "+kwota+" = "+iloscGotowki);
    }

    public void odejdzOdStolu(Kelner k) //kelner staje sie wolny
    {
        k.setCzyWolny(true);
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

    public void setIloscGotowki(double iloscGotowki) {
        this.iloscGotowki = iloscGotowki;
    }
}
