package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Avion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AvionModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Avion objAvion = (Avion) obj;
        try {
            String sql ="INSERT INTO avion (modelo,capacidad) VALUES (?,?); ";
            PreparedStatement objPrepare = objConnection.            prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objAvion.getModelo());
            objPrepare.setInt(2,objAvion.getCapacidad());

            objPrepare.execute();

            ResultSet objRest = objPrepare.getGeneratedKeys();

            if (objRest.next()){
                objAvion.setId(objRest.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "plane Inserted");

        }catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "ERROR Insert: " + error.getMessage());
        }
        ConfigDB.closeConnection();
        return objAvion;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listAviones = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try{
        String sql ="SELECT * FROM avion;";
        PreparedStatement objPrepare =objConnection.prepareStatement(sql);
        ResultSet objResult = objPrepare.executeQuery();

        while (objResult.next()){
            Avion objAvion = new Avion();
            objAvion.setId(objResult.getInt("id_avion"));
            objAvion.setModelo(objResult.getNString("modelo"));
            objAvion.setCapacidad(objResult.getInt("capacidad"));

            listAviones.add(objAvion);
        }

    }catch (SQLException error){
        JOptionPane.showMessageDialog(null,"ERROR List: "+error.getMessage());
    }
        ConfigDB.closeConnection();
        return listAviones;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Avion objAvion = (Avion) obj;
        boolean isUpdated= false;

        try{
            String slq = "UPDATE avion SET modelo = ?, capacidad = ? WHERE id_avion = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(slq);
            objPrepare.setString(1,objAvion.getModelo());
            objPrepare.setInt( 2, objAvion.getCapacidad());

            objPrepare.setInt(3,objAvion.getId());

            int totalRowAffec = objPrepare.executeUpdate();
            if (totalRowAffec>0){
                isUpdated =true;
                JOptionPane.showMessageDialog(null, "The update was successful");
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, "ERROR Upddte: " + error.getMessage());
        }

        ConfigDB.closeConnection();
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        Avion objAvion = ( Avion) obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isDeleted = false;

        try{
            String sql ="DELETE  FROM avion WHERE id_avion=?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objAvion.getId());

            int totalAffet= objPrepare.executeUpdate();

            if(totalAffet >0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The delete was successfull...");
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, "ERROR Delete: " + error.getMessage());
        }
        ConfigDB.closeConnection();
        return isDeleted;
    }

    @Override
    public Object SearchById(int id) {
        Connection objConnection = ConfigDB.openConnection();
        Avion objAvion = null;

        try{
            String sql ="SELECT * FROM avion WHERE id_avion=?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1 ,id);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                 objAvion=new Avion();
                objAvion.setId(objResult.getInt("id_avion"));
                objAvion.setModelo(objResult.getNString("modelo"));
                objAvion.setCapacidad(objResult.getInt("capacidad"));
            }

        }catch (Exception error) {
            JOptionPane.showMessageDialog(null, "ERROR Search: " + error.getMessage());
        }
        ConfigDB.closeConnection();
        return objAvion;

    }
}
