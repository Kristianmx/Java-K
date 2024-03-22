package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConfigLibreria {
    public static Connection objConnection = null;

    public static Connection openConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://bosuoyimg2jrjrtmdfa5-mysql.services.clever-cloud.com:3306/bosuoyimg2jrjrtmdfa5";
            String user = "u4mzopgjtz05fi6d";
            String password = "lxfaBioGH75u9U4qG3xV";

            objConnection = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Conexión Correcta");
        } catch (ClassNotFoundException error) {
            System.out.println("ERROR de Driver: " + error.getMessage());
        } catch (SQLException error) {
            System.out.println("ERROR:" + error.getMessage());
        }
        return objConnection;
    }

    public static void closeConnection() {
        try {
            if (objConnection != null) {
                objConnection.close();
                System.out.println("Conexión Cerrada");
            }
        } catch (SQLException error) {
            System.out.println("ERROR:" + error.getMessage());

        }
    }

}