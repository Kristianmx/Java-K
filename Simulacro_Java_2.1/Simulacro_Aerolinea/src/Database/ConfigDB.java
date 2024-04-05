package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    public static Connection objConnection = null;

    public static Connection openConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://bez8b5mmr8sw5dt6smzx-mysql.services.clever-cloud.com:3306/bez8b5mmr8sw5dt6smzx";
            String user = "uvnllpdxwfb2ycc4";
            String password = "3TKK9cUneqkwKBDkQK8j";
            objConnection = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa");

        }catch (ClassNotFoundException error) {
            System.out.println("ERROR de Driver: " + error.getMessage());
        } catch (SQLException error) {
            System.out.println("ERROR: " + error.getMessage());
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
