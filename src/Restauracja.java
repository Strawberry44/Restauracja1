import java.util.Scanner;

/**
 * Created by Grzeg on 12.06.2017.
 */
public class Restauracja {
    private Finanse finanse;
    private Menu menu;
    private BazaKelnerow bazaKelnerow;

    public Restauracja(int rozMenu, int rozKli, int rozKel)
    {
        menu = new Menu(rozMenu);
        finanse = new Finanse(rozKli);
        bazaKelnerow = new BazaKelnerow(rozKel);
    }
    public void otworz()
    {
        Scanner sc = new Scanner(System.in);
        int wybor =0;
        do
        {
                System.out.println();
                System.out.println("1. Aby zamowic jedzenie\n2. Aby odblokować kelnera\n3. Aby wyjsc");
                System.out.print("Twoj wybor: ");

            if(sc.hasNextInt())
            {
                wybor = sc.nextInt();
                if (wybor == 1) {
                    bazaKelnerow.przyjmijZamowienie(menu, finanse);
                } else if (wybor == 2) {
                    bazaKelnerow.uwolnijKelnera();
                } else if (wybor == 3) {
                    finanse.aktualizujListKlientow();
                    break;
                }
            }
            else {
                System.out.println("Blad");
                sc.next();
            }
        }while(true);


        sc.nextLine();

    }

}
