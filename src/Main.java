/**
 * Created by Grzeg on 12.06.2017.
 */
public class Main {
    private static final int rozmiarMenu = 4;   //iloscDan
    private static final int rozmiarKlientow = 3;
    private static final int rozmiarKelnerow = 2;

    public static void main(String[] args) {
        Restauracja restauracja = new Restauracja(rozmiarMenu,rozmiarKlientow,rozmiarKelnerow);
        restauracja.otworz();
    }

}
