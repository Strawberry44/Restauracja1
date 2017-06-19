/**
 * Created by Grzeg on 12.06.2017.
 */
public class Danie {
    //zle jest poukladane i sformatowane
    private String nazwa;
    private double porcje;
    private double cena;


    public String getNazwa() {
        return nazwa;
    }

    public double getCena() {
        return cena;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setPorcje(double porcje) {
        this.porcje = porcje;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "Nazwa='" + nazwa + '\'' +
                ", porcje=" + porcje +
                ", cena=" + cena;
    }
}
