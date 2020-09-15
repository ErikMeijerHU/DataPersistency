package P4.Domein;

import java.util.Date;

public class OVChipkaart {
    private int kaartNummer;
    private Date geldigTot;
    private int klasse;
    private float saldo;
    private Reiziger reiziger;

    public OVChipkaart(int kaartNummer, Date geldig_tot, int klasse, float saldo, Reiziger reiziger) {
        this.kaartNummer = kaartNummer;
        this.geldigTot = geldig_tot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger = reiziger;
    }

    public int getKaartNummer() {
        return kaartNummer;
    }

    public void setKaartNummer(int kaartNummer) {
        this.kaartNummer = kaartNummer;
    }

    public Date getGeldigTot() {
        return geldigTot;
    }

    public void setGeldigTot(Date geldig_tot) {
        this.geldigTot = geldig_tot;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    @Override
    public String toString() {
        return "{" +
                "Kaart Nummer=" + kaartNummer +
                ", Geldig Tot=" + geldigTot +
                ", Klasse=" + klasse +
                ", Saldo=" + saldo +
                '}';
    }
}
