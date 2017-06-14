/**
 * Created by Grzeg on 12.06.2017.
 */
public class Restauracja {
    private Finanse finanse;
    private Menu menu;
    private Kelner kelner;

    public Restauracja(int rozMenu, int rozKli, int rozKel)
    {
        menu = new Menu(rozMenu);
        finanse = new Finanse(rozKli);
        kelner = new Kelner(rozKel);
    }
    public void otworz()
    {
        kelner.przyjmijZamowienie(menu,finanse);
        kelner.przyjmijZamowienie(menu, finanse);
        finanse.aktualizujListKlientow();

    }

}
