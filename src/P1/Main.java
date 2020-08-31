package P1;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        //Create database connection.
        Connection db = DriverManager.getConnection("jdbc:postgresql://localhost/ovchip", "postgres", "Wachtw00rd");
        Statement statement = db.createStatement();

        //Run SQL query.
        ResultSet alleReizigers = statement.executeQuery("select * from reiziger");

        //Process result and console output.
        System.out.println("Alle Reizigers:");
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
    }
}
