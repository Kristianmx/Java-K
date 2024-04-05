package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Vuelo;
import com.mysql.cj.jdbc.ConnectionGroup;

import javax.swing.*;
import java.lang.ref.PhantomReference;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VueloModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Vuelo objVuelo = (Vuelo) obj;
        try{
            String sql = "INSERT INTO vuelo (destino,fecha_salida,hora_salida,id_avion_fk) VALUES (?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objVuelo.getDestino());
            objPrepare.setString(2,objVuelo.getFechaSalida());
            objPrepare.setString(3,objVuelo.getHoraSalida());
            objPrepare.setInt(4,objVuelo.getIdAvion());
            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();
            if (objResult.next()){
                objVuelo.setIdVuelo(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "plane Inserted");

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,"ERROR Insert: " + error.getMessage());
        }
        ConfigDB.closeConnection();
        return objVuelo;
    }
    @Override
    public List<Object> findAll() {
        List<Object> listVuelos = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try{
            String sql = "SELECT * FROM vuelo;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Vuelo objVuelo = new Vuelo();
                objVuelo.setIdVuelo(objResult.getInt("id_vuelo"));
                objVuelo.setDestino(objResult.getNString("destino"));
                objVuelo.setFechaSalida(objResult.getString("fecha_salida"));
                objVuelo.setHoraSalida(objResult.getString("hora_salida"));
                objVuelo.setIdAvion(objResult.getInt("id_avion_fk"));
                listVuelos.add(objVuelo);
            }
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,"ERROR List: " + error.getMessage());
        }
        ConfigDB.closeConnection();
        return listVuelos;
    }
    @Override
    public boolean update(Object obj) {
        Connection objCOnnection = ConfigDB.openConnection();
        Vuelo objVuelo = (Vuelo) obj;
        boolean isUpdated = false;
        try {
            String sql ="UPDATE vuelo SET destino=?, fecha_salida=?, hora_salida =? , id_avion_fk=? WHERE id_vuelo =?;";
            PreparedStatement objPrepare = objCOnnection.prepareStatement(sql);
            objPrepare.setString(1,objVuelo.getDestino());
            objPrepare.setString(2,objVuelo.getFechaSalida());
            objPrepare.setString(3,objVuelo.getHoraSalida());
            objPrepare.setInt(4,objVuelo.getIdAvion());
            objPrepare.setInt(5,objVuelo.getIdVuelo());
            int totalRowAffec= objPrepare.executeUpdate();
            if (totalRowAffec>0){
                isUpdated=true;
                JOptionPane.showMessageDialog(null, "The update was successful");
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null,"ERROR Update: "+ error.getMessage());
        }
        ConfigDB.closeConnection();
        return isUpdated;
    }
    @Override
    public boolean delete(Object obj) {
        Vuelo objVuelo = (Vuelo) obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isDeleted=false;
        try{
            String sql ="DELETE FROM vuelo WHERE id_vuelo=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,objVuelo.getIdVuelo());
            int totalAffect = objPrepare.executeUpdate();
            if (totalAffect > 0) {
                isDeleted=true;
                JOptionPane.showMessageDialog(null,"The delete was successfull...");
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null,"ERROR Delete: "+ error.getMessage());
        }
        ConfigDB.closeConnection();
        return isDeleted;
    }
    @Override
    public Object SearchById(int id) {
        Connection objConnection =ConfigDB.openConnection();
        Vuelo objVuelo = null;
        try {
            String sql = "SELECT * FROM vuelo WHERE id_vuelo =?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,id);
            ResultSet objResult =objPrepare.executeQuery();
            while (objResult.next()){
                objVuelo= new Vuelo();
                objVuelo.setIdVuelo(objResult.getInt("id_vuelo"));
                objVuelo.setDestino(objResult.getNString("destino"));
                objVuelo.setFechaSalida(objResult.getString("fecha_salida"));
                objVuelo.setHoraSalida(objResult.getString("hora_salida" ));
                objVuelo.setIdAvion(objResult.getInt("id_avion_fk"));
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null,"ERROR Search: " +error.getMessage());
        }
        ConfigDB.closeConnection();
        return objVuelo;
    }
    public List<Object> SearchByDestiny(String name) {

        List<Object> vueloList =new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Vuelo objVuelo = null;
        try {
            String sql ="SELECT * FROM vuelo WHERE destino LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,"%"+name+"%");

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objVuelo=new Vuelo();
                objVuelo.setIdVuelo(objResult.getInt("id_vuelo"));
                objVuelo.setDestino(objResult.getNString("destino"));
                objVuelo.setFechaSalida(objResult.getString("fecha_salida"));
                objVuelo.setHoraSalida(objResult.getString("hora_salida" ));
                objVuelo.setIdAvion(objResult.getInt("id_avion_fk"));
                vueloList.add(objVuelo);
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, "ERROR Search: " + error.getMessage());
        }
        ConfigDB.closeConnection();
        return vueloList;
    }
}
