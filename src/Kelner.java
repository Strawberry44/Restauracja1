/**
 * Created by Grzeg on 26.06.2017.
 */
public class Kelner {
    private String imie;
    private int staz;
    private boolean czyWolny;

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setStaz(int staz) {
        this.staz = staz;
    }

    public void setCzyWolny(boolean czyWolny) {
        this.czyWolny = czyWolny;
    }

    public String getImie() {

        return imie;
    }

    public int getStaz() {
        return staz;
    }

    public boolean isCzyWolny() {
        return czyWolny;
    }

    public void wypelnij(String... args) {
        imie = args[0];
        staz = Integer.parseInt(args[1]);
        czyWolny = true;
    }
}
