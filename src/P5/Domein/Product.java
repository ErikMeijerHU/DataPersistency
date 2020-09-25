package P5.Domein;

import java.util.ArrayList;

public class Product {
    private int productNummer;
    private String naam;
    private String beschrijving;
    private float prijs;
    // Ik gebruik hier een list van ID's in plaats van de objecten zelf om looping te voorkomen
    private ArrayList<Integer> ovChipkaartenIds = new ArrayList<>();

    public static ArrayList<Product> alleProducten = new ArrayList<>();

    public Product(int productNummer, String naam, String beschrijving, float prijs) {
        this.productNummer = productNummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;

        if(!alleProducten.contains(this)) {
            alleProducten.add(this);
        }
    }

    public int getProductNummer() {
        return productNummer;
    }

    public void setProductNummer(int productNummer) {
        this.productNummer = productNummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public float getPrijs() {
        return prijs;
    }

    public void setPrijs(float prijs) {
        this.prijs = prijs;
    }

    public ArrayList<Integer> getOvChipkaarten() {
        return ovChipkaartenIds;
    }

    public void setOvChipkaarten(ArrayList<OVChipkaart> ovChipkaarten) {
        ovChipkaartenIds.clear();
        for (OVChipkaart ovChipkaart : ovChipkaarten){
            if (!ovChipkaartenIds.contains(ovChipkaart.getKaartNummer())){
                ovChipkaartenIds.add(ovChipkaart.getKaartNummer());
            }
        }
    }

    public void addOvChipkaart(OVChipkaart ovChipkaart){
        if (!ovChipkaartenIds.contains(ovChipkaart.getKaartNummer())) {
            this.ovChipkaartenIds.add(ovChipkaart.getKaartNummer());
        }
    }

    public void deleteProduct(Product product) {
        for (OVChipkaart ovChipkaart : OVChipkaart.alleOvChipkaarten) {
            if (ovChipkaart.getProductenIds().contains(product.productNummer)) {
                ovChipkaart.getProductenIds().remove(Integer.valueOf(product.productNummer));
            }
        }
        alleProducten.remove(product);
    }

    @Override
    public String toString() {
        return "Product{" +
                "Nummer = " + productNummer +
                ", Naam = '" + naam + '\'' +
                ", Beschrijving = '" + beschrijving + '\'' +
                ", Prijs = " + prijs +
                ", Kaart nummers = " + ovChipkaartenIds +
                "}";
    }

    public static Product findById(Integer id) {
        for(Product product : alleProducten){
            if(product.getProductNummer() == id){
                return product;
            }
        }
        return null;
    }
}
