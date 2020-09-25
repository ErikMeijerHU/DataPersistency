package P5.Domein;

import java.util.ArrayList;
import java.util.Date;

public class OVChipkaart {
    private int kaartNummer;
    private Date geldigTot;
    private int klasse;
    private float saldo;
    private Reiziger reiziger;
    private ArrayList<Integer> productenIds = new ArrayList<>();

    public static ArrayList<OVChipkaart> alleOvChipkaarten = new ArrayList<>();

    public OVChipkaart(int kaartNummer, Date geldig_tot, int klasse, float saldo, Reiziger reiziger) {
        this.kaartNummer = kaartNummer;
        this.geldigTot = geldig_tot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger = reiziger;

        if(!alleOvChipkaarten.contains(this)){
            alleOvChipkaarten.add(this);
        }
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

    public ArrayList<Integer> getProductenIds() {return productenIds;}

    public void setProductenIds(ArrayList<Product> producten) {
        productenIds.clear();
        for (Product product : producten){
            if (!productenIds.contains(product.getProductNummer())){
                this.productenIds.add(product.getProductNummer());
            }
        }

    }

    public void deleteOvChipkaart(OVChipkaart ovChipkaart) {
        // Chipkaart bij de reiziger eruit halen.
        ovChipkaart.getReiziger().getOVChipkaarten().remove(ovChipkaart);

        // Bij alle producten de OV Chipkaart uit de lijst met chipkaarten halen.
        for (Product product : Product.alleProducten) {
            product.getOvChipkaarten().remove(Integer.valueOf(ovChipkaart.kaartNummer));
        }
        // Chipkaart uit de lijst met alle chipkaarten halen.
        alleOvChipkaarten.remove(ovChipkaart);
    }

    public void addProduct(Product product){
        this.getProductenIds().add(product.getProductNummer());
    }

    public static OVChipkaart findById(int id){
        for(OVChipkaart ovChipkaart : alleOvChipkaarten){
            if(ovChipkaart.getKaartNummer() == id){
                return ovChipkaart;
            }
        }
        return null;
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
