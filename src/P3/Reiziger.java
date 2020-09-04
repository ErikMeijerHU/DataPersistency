package P3;
import java.time.LocalDate;

public class Reiziger {
    private int id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private LocalDate geboortedatum;
    private Adres adres = null;

    public Reiziger(int id, String voorletters, String tussenvoegsel, String achternaam, LocalDate geboortedatum) {
        this.id = id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(LocalDate geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public String toString() {
        if (adres!=null) {
            if (this.tussenvoegsel != null) {
                return "Reiziger {#" + id + ": " + voorletters + " " + tussenvoegsel + " " + achternaam + " (" + geboortedatum.toString() + "), " + adres.toString() + "}";
            } else {
                return "Reiziger {#" + id + ": " + voorletters + " " + achternaam + " (" + geboortedatum.toString() + "), " + adres.toString() + "}";
            }
        }
        else {
            if (this.tussenvoegsel != null) {
                return "Reiziger {#" + id + ": " + voorletters + " " + tussenvoegsel + " " + achternaam + " (" + geboortedatum.toString() + "), Geen Adres}";
            } else {
                return "Reiziger {#" + id + ": " + voorletters + " " + achternaam + " (" + geboortedatum.toString() + "), Geen Adres}";
            }
        }
    }
}
