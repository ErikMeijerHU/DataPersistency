package P4.Data;

import P4.Domein.Main;
import P4.Domein.OVChipkaart;
import P4.Domein.Reiziger;

import java.sql.*;
import java.util.ArrayList;

public class OVChipkaartDAOPsql implements OVChipkaartDAO{
    private Connection conn;
    private ReizigerDAOPsql rdao;

    public OVChipkaartDAOPsql(Connection conn) throws SQLException {
        this.conn = conn;
        rdao = new ReizigerDAOPsql(conn);
    }

    @Override
    public boolean save(OVChipkaart ovChipkaart) throws SQLException {
        // Query om nieuwe OV Chipkaart op te slaan in database.
        String saveQuery = "insert into ov_chipkaart (kaart_nummer, geldig_tot, klasse, saldo, reiziger_id) values (?, ?, ?, ?, ?)";

        // Gebruik van prepared statement om makkelijk variabelen in de query te doen.
        try(PreparedStatement ps = conn.prepareStatement(saveQuery)) {
            ps.setInt(1, ovChipkaart.getKaartNummer());
            ps.setDate(2, new Date(ovChipkaart.getGeldigTot().getTime()));
            ps.setInt(3, ovChipkaart.getKlasse());
            ps.setFloat(4, ovChipkaart.getSaldo());
            ps.setInt(5, ovChipkaart.getReiziger().getId());

        // Prepared statement uitvoeren en closen.
            ps.execute();
            ps.close();
            return true;
        }catch (SQLException e){
            System.out.println("Error saving new OV Chipkaart");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean update(OVChipkaart ovChipkaart) throws SQLException {
        // Query om een OV Chipkaart te updaten.
        String updateQuery = "UPDATE ov_chipkaart SET geldig_tot = ?, klasse = ?, saldo = ?, reiziger_id = ? WHERE kaart_nummer = ?;";

        // Variabelen in de query stoppen.
        try(PreparedStatement ps = conn.prepareStatement(updateQuery)) {
            ps.setDate(1, new Date(ovChipkaart.getGeldigTot().getTime()));
            ps.setInt(2, ovChipkaart.getKlasse());
            ps.setFloat(3, ovChipkaart.getSaldo());
            ps.setInt(4, ovChipkaart.getReiziger().getId());
            ps.setInt(5, ovChipkaart.getKaartNummer());

        // Prepared statement uitvoeren en closen.
            ps.execute();
            ps.close();
            return true;
        }catch (SQLException e){
            System.out.println("Error updating OV Chipkaart");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean delete(OVChipkaart ovChipkaart) throws SQLException {
        // De OV Chipkaart uit de lijst met chipkaarten van de reiziger halen.
        ovChipkaart.getReiziger().getOVChipkaarten().remove(ovChipkaart);

        // Delete query maken en uitvoeren
        String deleteQuery = "DELETE FROM ov_chipkaart WHERE kaart_nummer = ?";
        try(PreparedStatement ps = conn.prepareStatement(deleteQuery)) {
            ps.setInt(1, ovChipkaart.getKaartNummer());
            ps.execute();
            ps.close();
            return true;
        }catch (SQLException e){
            System.out.println("Error deleting OV Chipkaart");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public OVChipkaart findById(int id) throws SQLException {
        // Maak de query en voer hem uit.
        String findByIdQuery = "SELECT * FROM ov_chipkaart WHERE kaart_nummer = ?";
        try(PreparedStatement ps = conn.prepareStatement(findByIdQuery)) {
            ps.setInt(1, id);
            ResultSet results = ps.executeQuery();
            // Het aanmaken van het ovchipkaart object in een if zodat het niet fout gaat als er geen resultaat is.
            if (results.next()){
                // De database aanroepen om bijbehorende reiziger op te halen en daarna ovchipkaart object aanmaken.
                OVChipkaart ovChipkaart = new OVChipkaart(id, new Date(results.getDate("geldig_tot").getTime()), results.getInt("klasse"), results.getFloat("saldo"),  rdao.findById(results.getInt("reiziger_id")));
                ps.close();
                return ovChipkaart;
            }
            else {
                return null;
            }
        }catch (SQLException e){
            System.out.println("Error trying to find OV Chipkaart by id");
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ArrayList<OVChipkaart> findByReiziger(Reiziger reiziger) throws SQLException {
        String findByReizigerQuery = "SELECT * FROM ov_chipkaart WHERE reiziger_id = ?;";
        // Lijst maken van chipkaarten om te vullen uit de database.
        ArrayList<OVChipkaart> chipkaarten = new ArrayList<OVChipkaart>();
        // Query uitvoeren en lijst invullen.
        try(PreparedStatement ps = conn.prepareStatement(findByReizigerQuery)) {
            ps.setInt(1, reiziger.getId());
            ResultSet results = ps.executeQuery();
            while(results.next()) {
                OVChipkaart ovChipkaart = new OVChipkaart(results.getInt("kaart_nummer"), new Date(results.getDate("geldig_tot").getTime()), results.getInt("klasse"), results.getFloat("saldo"), reiziger);
                chipkaarten.add(ovChipkaart);
            }
            ps.close();
            return chipkaarten;
        }catch (SQLException e){

            System.out.println("Error trying to find ov chipkaart by reiziger, maybe reiziger doesn't have an ov chipkaart yet");
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ArrayList<OVChipkaart> findAll() throws SQLException {
        String findAllQuery = "SELECT * FROM ov_chipkaart;";
        ArrayList<OVChipkaart> chipkaarten = new ArrayList<>();
        try(PreparedStatement ps = conn.prepareStatement(findAllQuery)){
            ResultSet results = ps.executeQuery();

            //Alle reizigers ophalen
            ArrayList<Reiziger> reizigers = rdao.findAll();
            while (results.next()){
                // Door reizigers zoeken naar de passende reiziger bij de OV chipkaart, op deze manier hoeft er maar 1 keer de database geroepen te worden voor reizigers in plaats van als ik rdao.findById zou doen.
                for(Reiziger reiziger : reizigers){
                    if(reiziger.getId() == results.getInt("reiziger_id")){
                        OVChipkaart ovChipkaart = new OVChipkaart(results.getInt("kaart_nummer"), new Date(results.getDate("geldig_tot").getTime()), results.getInt("klasse"), results.getFloat("saldo"), reiziger);
                    }
                }
            }
            ps.close();
            return chipkaarten;
        }
        catch (SQLException e){
            System.out.println("Error getting all chipkaarten");
            System.out.println(e);
            return null;
        }
    }
}
