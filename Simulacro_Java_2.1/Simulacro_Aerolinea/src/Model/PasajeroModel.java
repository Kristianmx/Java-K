package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Pasajero;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PasajeroModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Pasajero objPasajero = (Pasajero)  obj;

        try {
            String sql = " INSERT INTO pasajero (nombre,apellido,documento_identidad) VALUES (?,?,?);";
           PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

           objPrepare.setString(1,objPasajero.getNombre());
           objPrepare.setString(2, objPasajero.getApellido());
           objPrepare.setString(3,objPasajero.getDocumento());

           objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();
            if (objResult.next()){
                objPasajero.setId_pasajero(objResult.getInt(1));
            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, "ERROR Insert: "+ error.getMessage());
        }
        ConfigDB.closeConnection();
        return objPasajero;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listPasajeros = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try{
            String sql = " SELECT * FROM pasajero;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                Pasajero objPasajero = new Pasajero();
                objPasajero.setId_pasajero(objResult.getInt("id_pasajero"));
                objPasajero.setNombre(objResult.getNString("nombre"));
                objPasajero.setApellido(objResult.getNString("apellido"));
                objPasajero.setDocumento(objResult.getNString("documento_identidad"));
                listPasajeros.add(objPasajero);
            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,"ERROR List: "+ error.getMessage());
        }
        ConfigDB.closeConnection();
        return listPasajeros;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Pasajero objPasajero= (Pasajero)obj;
        boolean isUpdate = false;
        try{
            String sql = "UPDATE pasajero SET nombre = ?, apellido = ?, documento_identidad = ? WHERE id_pasajero = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objPasajero.getNombre());
            objPrepare.setString(2,objPasajero.getApellido());
            objPrepare.setString(3,objPasajero.getDocumento());

            objPrepare.setInt(4,objPasajero.getId_pasajero());

            int totalRowAffect = objPrepare.executeUpdate();
            if (totalRowAffect>0){
                isUpdate=true;
                JOptionPane.showMessageDialog(null, "The update was successful");
            }
        }catch (Exception error) {
            JOptionPane.showMessageDialog(null,"ERROR Update: " + error.getMessage());
        }
        ConfigDB.closeConnection();
        return isUpdate;
    }

    @Override
    public boolean delete(Object obj) {
        Pasajero objPasajero = (Pasajero) obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isDeleted = false;
        try {
            String sql ="DELETE FROM pasajero WHERE id_pasajero=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objPasajero.getId_pasajero());

            int totalAffect=objPrepare.executeUpdate();
            if (totalAffect >0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The delete was successfull...");
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null,"ERROR Delete: "+ error.getMessage());
        }
        ConfigDB.closeConnection();
        return isDeleted;
    }

    @Override
    public Object SearchById(int id) {
        Connection objConnection = ConfigDB.openConnection();
        Pasajero objPasajero = null;
        try {
            String sql = "SELECT * FROM pasajero WHERE id_pasajero =?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,id);

            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()){
                objPasajero=new Pasajero();
                objPasajero.setId_pasajero(objResult.getInt("id_pasajero"));
                objPasajero.setNombre(objResult.getNString("nombre"));
                objPasajero.setApellido(objResult.getNString("apellido"));
                objPasajero.setDocumento(objResult.getNString("documento_identidad"));

            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null,"ERROR Search: "+ error.getMessage());
        }
        ConfigDB.closeConnection();
        return objPasajero;
    }
    public Object SearchByName(String name) {
        Connection objConnection = ConfigDB.openConnection();
        Pasajero objPasajero = null;
        try {
            String sql = "SELECT * FROM pasajero WHERE nombre LIKE ? ;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1,"%"+name+"%");

            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()){
                objPasajero=new Pasajero();
                objPasajero.setId_pasajero(objResult.getInt("id_pasajero"));
                objPasajero.setNombre(objResult.getNString("nombre"));
                objPasajero.setApellido(objResult.getNString("apellido"));
                objPasajero.setDocumento(objResult.getNString("documento_identidad"));

            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null,"ERROR Search: "+ error.getMessage());
        }
        ConfigDB.closeConnection();
        return objPasajero;
    }
}
