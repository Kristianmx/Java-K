package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Reservacion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservacionModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnectio = ConfigDB.openConnection();
        Reservacion objReserva = (Reservacion) obj;
        try{
            String sql = "INSERT INTO reservacion (id_pasajero_fk,id_vuelo_fk,fecha_reservacion,asiento) VALUES (?,?,?,?);";

            PreparedStatement objPrepare = objConnectio.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1,objReserva.getIdPasajero());
            objPrepare.setInt(2,objReserva.getIdVuelo());
            objPrepare.setString(3,objReserva.getFechaReserva());
            objPrepare.setString(4,objReserva.getAsiento());
            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();
            if (objResult.next()){
                System.out.println("entro" + objReserva.toString());
                objReserva.setIdReserva(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null,"Reservation Inserted");
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,"ERROR Insert:"+error.getMessage() );
        }
        ConfigDB.closeConnection();
        return objReserva;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listReserva = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = "SELECT * FROM reservacion;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Reservacion objReserva =new Reservacion();
                objReserva.setIdReserva(objResult.getInt("id_reservacion"));
                objReserva.setIdPasajero(objResult.getInt("id_pasajero_fk"));
                objReserva.setIdVuelo(objResult.getInt("id_vuelo_fk"));
                objReserva.setFechaReserva(objResult.getString("fecha_reservacion"));
                objReserva.setAsiento(objResult.getNString("asiento"));
                listReserva.add(objReserva);
            }
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, "ERROR List: "+error.getMessage());
        }
        ConfigDB.closeConnection();
        return listReserva;
    }

    @Override

    public boolean update(Object obj){
        Connection objCOnnection = ConfigDB.openConnection();
        Reservacion objReserva = (Reservacion) obj;
        boolean isUpdate=false;

        try{
            String sql ="UPDATE reservacion SET id_pasajero_fk =?, id_vuelo_fk =?,fecha_reservacion =?,asiento=? WHERE id_reservacion =?;";
            PreparedStatement objPrepare = objCOnnection.prepareStatement(sql);

            objPrepare.setInt(1,objReserva.getIdPasajero());
            objPrepare.setInt(2,objReserva.getIdVuelo());
            objPrepare.setString(3,objReserva.getFechaReserva());
            objPrepare.setString(4,objReserva.getAsiento());
            objPrepare.setInt(5,objReserva.getIdReserva());

            int totalRowAffect = objPrepare.executeUpdate();
            if (totalRowAffect>0){
                isUpdate=true;
                JOptionPane.showMessageDialog(null, "The update was successful");
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, "ERROR Update: " + error.getMessage());
        }
        ConfigDB.closeConnection();
        return isUpdate;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objCOnnection = ConfigDB.openConnection();
        Reservacion objReserva = (Reservacion) obj;
        boolean isDeleted=false;
        try {
            String sql ="DELETE FROM reservacion WHERE id_reservacion = ?;";

            PreparedStatement objPrepare = objCOnnection.prepareStatement(sql);

            objPrepare.setInt(1,objReserva.getIdReserva());
            int totalAffect = objPrepare.executeUpdate();
            if (totalAffect>0){
                isDeleted=true;
                JOptionPane.showMessageDialog(null,"The delete was successfull...");
            }
        }catch (Exception error ){
            JOptionPane.showMessageDialog(null,"ERROR Delete: "+error.getMessage());
        }
        ConfigDB.closeConnection();
        return isDeleted;
    }

    @Override
    public Object SearchById(int id) {
        Connection objCOnnection = ConfigDB.openConnection();
        Reservacion objReserva = null;

        try {
            String sql = "SELECT * FROM reservacion WHERE id_reservacion =?;";
            PreparedStatement objPrepare = objCOnnection.prepareStatement(sql);
            objPrepare.setInt(1,id);
            ResultSet objResult =objPrepare.executeQuery();
            while (objResult.next()) {
            objReserva = new Reservacion();
                objReserva.setIdReserva(objResult.getInt("id_reservacion"));
                objReserva.setIdPasajero(objResult.getInt("id_pasajero_fk"));
                objReserva.setIdVuelo(objResult.getInt("id_vuelo_fk"));
                objReserva.setFechaReserva(objResult.getString("fecha_reservacion"));
                objReserva.setAsiento(objResult.getNString("asiento"));
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null,"ERROR Search: " +error.getMessage());
        }
        ConfigDB.closeConnection();
        return objReserva;
    }
    public List<Object> SearchByFlight(String tex){
        Connection objCOnnection = ConfigDB.openConnection();
        Reservacion objReserva = null;
        List<Object> list =new ArrayList<>();
        try {
            String sql = "select *  from reservacion inner join vuelo Where vuelo.destino Like  ? ;";
            PreparedStatement objPrepare = objCOnnection.prepareStatement(sql);
            objPrepare.setString(1,"%"+tex+"%");
            ResultSet objResult =objPrepare.executeQuery();
            while (objResult.next()) {
                objReserva = new Reservacion();
                objReserva.setIdReserva(objResult.getInt("id_reservacion"));
                objReserva.setIdPasajero(objResult.getInt("id_pasajero_fk"));
                objReserva.setIdVuelo(objResult.getInt("id_vuelo_fk"));
                objReserva.setFechaReserva(objResult.getString("fecha_reservacion"));
                objReserva.setAsiento(objResult.getNString("asiento"));
                objReserva.setDestino(objResult.getNString("destino"));

                list.add(objReserva);
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null,"ERROR Search: " +error.getMessage());
        }
        ConfigDB.closeConnection();
        return list ;
    }

}
