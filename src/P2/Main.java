package P2;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static Connection getConnection(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/ovchip", "postgres", "Wachtw00rd");
            return connection;
        }catch (SQLException e){
            System.out.println("Error connecting to database");
            return null;
        }
    }

    public void closeConnection(Connection connection) {
        try {
            connection.close();
            System.out.println("Connection closed");
        }
        catch (SQLException e){
            System.out.println("Error closing connection");
            System.out.println(e);
        }
    }

    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        // Maak een nieuwe reiziger aan en persisteer deze in de database
        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(77, "S", "", "Boers", LocalDate.parse(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");
        System.out.println("");

        // Zoek reiziger met ID

        System.out.println("[Test] Reiziger zoeken met id 77 zou S Boers moeten geven:");
        System.out.println(rdao.findById(77));
        System.out.println("");

        // Zoek reiziger met ID

        System.out.println("[Test] Reiziger zoeken met geboortedatum 1981-03-14 zou S Boers moeten geven:");
        System.out.println(rdao.findByGbdatum(LocalDate.parse("1981-03-14")));
        System.out.println("");

        // Update bestaande reiziger

        System.out.println("[Test] Reiziger met ID 77 heeft eerst achternaam:");
        Reiziger reiziger = rdao.findById(77);
        System.out.println(reiziger.getAchternaam());
        reiziger.setAchternaam("Klaas");
        System.out.println("En na update:");
        rdao.update(reiziger);
        System.out.println(rdao.findById(77).getAchternaam());
        System.out.println("");

        // Delete reiziger

        reizigers = rdao.findAll();
        System.out.print("[Test] Eerst:" + reizigers.size() + " reizigers, na Delete: ");
        rdao.delete(rdao.findById(77));
        reizigers = null;
        reizigers = rdao.findAll();
        System.out.print(reizigers.size());
    }

    public static void main(String[] args) throws SQLException {
        ReizigerDAOPsql rdao = new ReizigerDAOPsql(getConnection());
        testReizigerDAO(rdao);
        }
    }

