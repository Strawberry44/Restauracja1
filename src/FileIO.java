import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Grzeg on 14.06.2017.
 */
public class FileIO {

    private File odczyt;
    private PrintWriter zapis;
    private String fileNameMenu;

    public void wczytajKlientow(Klient[] tab, String path)
    {
        odczyt = new File(path);
        try {
            Scanner in = new Scanner(odczyt);
            int licznik = 0;
            while(in.hasNextLine())
            {//puste przestrzenie
                String line = in.nextLine();
                String[] splited = line.split(" ");

                tab[licznik] = new Klient();
                tab[licznik].wypelnij(splited[0], splited[1], splited[2]);

                licznik++;
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Nie znaleziono pliku: "+ path);
        }

    }


    public void wczytajKelnerow(List list, String path)
    {
        odczyt = new File(path);
        try {
            Scanner in = new Scanner(odczyt);
            int licznik = 0;
            while(in.hasNextLine())
            {//puste przestrzenie
                String line = in.nextLine();
                String[] splited = line.split(" ");
                Kelner k = new Kelner();
                k.wypelnij(splited[0], splited[1]);
                list.add(k);
                licznik++;
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Nie znaleziono pliku: "+ path);
        }

    }
    public void wczytajDania(Danie[] tab){
        odczyt = new File(fileNameMenu);
        try {
            Scanner in = new Scanner(odczyt);
            int licznik = 0;
            while(in.hasNextLine())
            {
                String line = in.nextLine();
                String[] splited = line.split(" ");
                tab[licznik] = new Danie();
                tab[licznik].setNazwa(splited[0]);
                tab[licznik].setPorcje(Double.parseDouble(splited[1]));
                tab[licznik].setCena(Double.parseDouble(splited[2]));
                licznik++;

            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Nie znaleziono pliku: "+ fileNameMenu);
        }


    }

    public void zapisDoPliku(Klient[] tab, String path)  //do uaktualniania stanu gotowki klijenta po zamowieniu
    {
        try {
            zapis = new PrintWriter(path);
            for (int i = 0; i < tab.length; i++) {
                zapis.println(tab[i].toString());
            }
            System.out.println("Zapisano zmiany");
            zapis.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Nie znaleziono pliku: "+ path);
        }
    }
    public FileIO() {
        this.fileNameMenu = "dania.txt";
    }
}
