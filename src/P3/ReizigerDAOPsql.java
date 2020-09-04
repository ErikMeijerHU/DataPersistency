package P3;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReizigerDAOPsql implements ReizigerDAO {
    private Connection conn;
    private AdresDAOPsql adao;

    public ReizigerDAOPsql(Connection conn) {
        this.conn = conn;
        adao = new AdresDAOPsql(conn);
    }

    @Override
    public boolean save(Reiziger reiziger) {
        String saveQuery = "insert into reiziger (reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum) values (?, ?, ?, ?, ?)";
        try(PreparedStatement ps = conn.prepareStatement(saveQuery)) {
            ps.setInt(1, reiziger.getId());
            ps.setString(2, reiziger.getVoorletters());
            ps.setString(3, reiziger.getTussenvoegsel());
            ps.setString(4, reiziger.getAchternaam());
            ps.setDate(5, Date.valueOf(reiziger.getGeboortedatum()));
            ps.execute();
            ps.close();
            return true;
        }catch (SQLException e){
            System.out.println("Error saving new reiziger");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean update(Reiziger reiziger) {
        String updateQuery = "UPDATE reiziger SET voorletters = ?, tussenvoegsel = ?, achternaam = ?, geboortedatum = ? WHERE reiziger_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(updateQuery)){
            ps.setString(1, reiziger.getVoorletters());
            ps.setString(2, reiziger.getTussenvoegsel());
            ps.setString(3, reiziger.getAchternaam());
            ps.setDate(4, Date.valueOf(reiziger.getGeboortedatum()));
            ps.setInt(5, reiziger.getId());
            ps.execute();
            ps.close();
            return true;
        }
        catch (SQLException e){
            System.out.println("Error updating reiziger");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        String deleteQuery = "DELETE FROM reiziger WHERE reiziger_id = ?";
        try(PreparedStatement ps = conn.prepareStatement(deleteQuery)) {
            ps.setInt(1, reiziger.getId());
            ps.execute();
            ps.close();

            if (reiziger.getAdres()!=null) {
                adao.delete(reiziger.getAdres());
            }
            return true;
        }catch (SQLException e){
            System.out.println("Error deleting reiziger");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Reiziger findById(int id) {
        String findByIdQuery = "SELECT * FROM reiziger WHERE reiziger_id = ?";
        try(PreparedStatement ps = conn.prepareStatement(findByIdQuery)) {
            ps.setInt(1, id);
            ResultSet results = ps.executeQuery();
            results.next();
            Reiziger reiziger = new Reiziger(id, results.getString("voorletters"), results.getString("tussenvoegsel"), results.getString("achternaam"), results.getDate("geboortedatum").toLocalDate());
            Adres adres = adao.findByReiziger(reiziger);
            if (adres!=null){reiziger.setAdres(adres);}
            ps.close();
            return reiziger;
        }catch (SQLException e){
            System.out.println("Error trying to find reiziger by id");
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Reiziger findByGbdatum(LocalDate geboortedatum) {
        String findByGbQuery = "SELECT * FROM reiziger WHERE geboortedatum = ?";
        try(PreparedStatement ps = conn.prepareStatement(findByGbQuery)) {
            ps.setDate(1, Date.valueOf(geboortedatum));
            ResultSet results = ps.executeQuery();
            results.next();
            Reiziger reiziger = new Reiziger(results.getInt("reiziger_id"), results.getString("voorletters"), results.getString("tussenvoegsel"), results.getString("achternaam"), results.getDate("geboortedatum").toLocalDate());
            Adres adres = adao.findByReiziger(reiziger);
            if (adres!=null){reiziger.setAdres(adres);}
            ps.close();
            return reiziger;
        }catch (SQLException e){
            System.out.println("Error trying to find reiziger by geboortedatum");
            System.out.println(e);;
            return null;
        }
    }

    @Override
    public ArrayList<Reiziger> findAll() {
        String findAllQuery = "SELECT * FROM reiziger;";
        ArrayList<Reiziger> allReizigers = new ArrayList<>();
        try(PreparedStatement ps = conn.prepareStatement(findAllQuery)){
            ResultSet results = ps.executeQuery();
            while (results.next()){
                Reiziger reiziger = new Reiziger(results.getInt("reiziger_id"), results.getString("voorletters"), results.getString("tussenvoegsel"), results.getString("achternaam"), results.getDate("geboortedatum").toLocalDate());
                Adres adres = adao.findByReiziger(reiziger);
                if (adres!=null){reiziger.setAdres(adres);}
                allReizigers.add(reiziger);
            }
            ps.close();
            return allReizigers;
        }
        catch (SQLException e){
            System.out.println("Error getting all reizigers");
            System.out.println(e);
            return null;
        }
    }
}
