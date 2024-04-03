package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Cita;
import Entity.Paciente;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CitaModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Cita objCita = (Cita)obj;
        try{
            String sql = "INSERT INTO cita (fecha_cita,hora_cita, motivo, id_paciente_cita, id_medico_cita) VALUES (?,?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1,objCita.getFecha());
            objPrepare.setTime(2,objCita.getHora());
            objPrepare.setString(3,objCita.getMotivo());
            objPrepare.setInt(4,objCita.getId_paciente());
            objPrepare.setInt(5,objCita.getId_medico());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            if (objResult.next()){
                objCita.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null,"appointment Inserted");
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,"ERROR Insert: "+error.getMessage());
        }
        ConfigDB.closeConnection();
        return objCita;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listCita = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try{
            String sql= "SELECT * FROM cita;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Cita objCita = new Cita();
                objCita.setId(objResult.getInt("id_cita"));
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                objCita.setFecha(dateFormat.format((objResult.getDate("fecha_cita"))));
                objCita.setHora(objResult.getTime("hora_cita"));
                objCita.setId_paciente(objResult.getInt("id_paciente_cita"));
                objCita.setId_medico(objResult.getInt("id_medico_cita"));
                objCita.setMotivo(objResult.getNString("motivo"));

                listCita.add(objCita);
            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,"ERROR List: "+error.getMessage());
        }
        ConfigDB.closeConnection();
        return listCita;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Cita objCita = (Cita) obj;
        boolean isUpdated = false;

        try {
            String sql ="UPDATE cita SET id_paciente_cita =?, id_medico_cita =?, fecha_cita =?, hora_cita =?,motivo=? WHERE id_cita = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objCita.getId_paciente());
            objPrepare.setInt(2,objCita.getId_medico());
            objPrepare.setString(3,objCita.getFecha());
            objPrepare.setTime(4,objCita.getHora());
            objPrepare.setString(5,objCita.getMotivo());
            objPrepare.setInt(6,objCita.getId());

            int totalRowAffec=objPrepare.executeUpdate();
            if (totalRowAffec>0){
                isUpdated=true;
                JOptionPane.showMessageDialog(null, "The update was successful");
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null,"ERROR Update: "+error.getMessage());
        }
        ConfigDB.closeConnection();
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Cita objCita = (Cita) obj;
        boolean isDeleted = false;
        try{
            String sql ="DELETE FROM cita WHERE id_cita =?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objCita.getId());

            int totalAffect = objPrepare.executeUpdate();

            if (totalAffect>0){
                isDeleted=true;
                JOptionPane.showMessageDialog(null, "The delete was successfull...");
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null,"ERROR Delete"+error.getMessage());
        }

        ConfigDB.closeConnection();
        return false;
    }
    public Cita SearchById(int id){
        Connection objConnection = ConfigDB.openConnection();
        Cita objCita = null;

        try{
            String sql ="SELECT * FROM cita WHERE id_cita =?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,id);

            ResultSet objResult = objPrepare.executeQuery();
            while(objResult.next()){
                objCita=new Cita();
                objCita.setId(objResult.getInt("id_cita"));
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                objCita.setFecha(dateFormat.format((objResult.getDate("fecha_cita"))));
                objCita.setHora(objResult.getTime("hora_cita"));
                objCita.setId_paciente(objResult.getInt("id_paciente_cita"));
                objCita.setId_medico(objResult.getInt("id_medico_cita"));
                objCita.setMotivo(objResult.getNString("motivo"));

            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, "ERROR Search: " + error.getMessage());
        }
        ConfigDB.closeConnection();
        return objCita;
    }
    public List<Cita> SearchByDate(String date){
        Connection objConnection = ConfigDB.openConnection();
        Cita objCita = null;

        List<Cita> listcitas = new ArrayList<>();
        try{
            String sql ="SELECT * FROM cita WHERE fecha_cita LIKE ?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,"%"+date+"%");

            ResultSet objResult = objPrepare.executeQuery();
            while(objResult.next()){
                objCita=new Cita();
                objCita.setId(objResult.getInt("id_cita"));
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                objCita.setFecha(dateFormat.format((objResult.getDate("fecha_cita"))));
                objCita.setHora(objResult.getTime("hora_cita"));
                objCita.setId_paciente(objResult.getInt("id_paciente_cita"));
                objCita.setId_medico(objResult.getInt("id_medico_cita"));
                objCita.setMotivo(objResult.getNString("motivo"));
                listcitas.add(objCita);

            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, "ERROR Search: " + error.getMessage());
        }
        ConfigDB.closeConnection();
        return listcitas;
    }
}
