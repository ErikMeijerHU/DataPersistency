package P1;

import java.sql.*;

public class Main {
    public static void main(String[] args){
        Connection db = null;
        ResultSet alleReizigers = null;
        try {
            db = DriverManager.getConnection("jdbc:postgresql://localhost/ovchip", "postgres", "Wachtw00rd");
        }catch (SQLException e){
            System.out.println("Error connecting to database");
        }

        try {
            Statement statement = db.createStatement();
            alleReizigers = statement.executeQuery("select * from reiziger");
            statement.close();
        }catch (SQLException e){
            System.out.println("Error running query");
        }

        System.out.println("Alle Reizigers:");
        assert alleReizigers != null;
        try {
            while (alleReizigers.next()){
                if(alleReizigers.getString("tussenvoegsel") != null){
                    System.out.println(
                            "    #" + alleReizigers.getString("reiziger_id") + ": "
                                    + alleReizigers.getString("voorletters") + " "
                                    + alleReizigers.getString("tussenvoegsel") + " "
                                    + alleReizigers.getString("achternaam") + " ("
                                    + alleReizigers.getString("geboortedatum") + ")"
                    );
                }
                else{
                    System.out.println(
                            "    #" + alleReizigers.getString("reiziger_id") + ": "
                                    + alleReizigers.getString("voorletters") + " "
                                    + alleReizigers.getString("achternaam") + " ("
                                    + alleReizigers.getString("geboortedatum") + ")"
                    );
                }
        }
        }catch (SQLException e){
            System.out.println("Error processing results");
        }

        try {
            db.close();
        }
        catch (SQLException e){
            System.out.println("Error closing connection");
        }

    }
}
