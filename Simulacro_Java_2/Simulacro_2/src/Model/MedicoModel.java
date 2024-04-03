package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Medico;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MedicoModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Medico objMedico = (Medico) obj;
        try {
            String sql = "INSERT INTO medico (nombre,apellidos, id_especialidad_medico) VALUES (?,?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1,objMedico.getNombre());
            objPrepare.setString(2,objMedico.getApellidos());
            objPrepare.setInt(3,objMedico.getIdEspecialidad());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            if (objResult.next()){
                objMedico.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null,"Doctor inserted");

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,"ERROR Insert: "+error.getMessage());
        }
        ConfigDB.closeConnection();
        return objMedico;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listMedico = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql ="SELECT * FROM medico;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()){
                Medico objMedico = new Medico();
                objMedico.setId(objResult.getInt("id_medico"));
                objMedico.setNombre(objResult.getNString("nombre"));
                objMedico.setApellidos(objResult.getNString("apellidos"));
                objMedico.setIdEspecialidad(objResult.getInt("id_especialidad_medico"));

                listMedico.add(objMedico);
            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,"ERROR List: "+error.getMessage());
        }
        ConfigDB.closeConnection();
        return listMedico;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnectio = ConfigDB.openConnection();
        Medico objMedico =(Medico)  obj;
        boolean isUpdated=false;
        try{
            String sql ="UPDATE medico SET nombre=?,apellidos =?, id_especialidad_medico=? WHERE id_medico=?;";
            PreparedStatement objPrepare = objConnectio.prepareStatement(sql);

            objPrepare.setString(1,objMedico.getNombre());
            objPrepare.setString(2,objMedico.getApellidos());
            objPrepare.setInt(3,objMedico.getIdEspecialidad());
            objPrepare.setInt(4,objMedico.getId());

            int totalRowAffec=objPrepare.executeUpdate();
            if (totalRowAffec>0){
                isUpdated = true;
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
        Medico objMedico = (Medico)obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isDeleted= false;

        try {
            String sql = "DELETE FROM medico WHERE id_medico =?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objMedico.getId());
            int totalAffect =objPrepare.executeUpdate();

            if (totalAffect>0){
                isDeleted=true;
                JOptionPane.showMessageDialog(null, "The delete was successfull...");
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null,"ERROR Delete"+error.getMessage());
        }
        ConfigDB.closeConnection();
        return isDeleted;

    }
    public Medico SearchById(int id) {
        Connection objConnection = ConfigDB.openConnection();
        Medico objMedico = null;
        try{
            String sql = "SELECT * FROM medico WHERE Id_medico=?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,id);
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                objMedico=new Medico();
                objMedico.setId(objResult.getInt("id_medico"));
                objMedico.setNombre(objResult.getNString("nombre"));
                objMedico.setApellidos(objResult.getNString("apellidos"));
                objMedico.setIdEspecialidad(objResult.getInt("id_especialidad_medico"));
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, "ERROR Search: " + error.getMessage());
        }
        ConfigDB.closeConnection();
        return objMedico;
    }


    public Medico SearchByEspecialidad(String id) {
        Connection objConnection = ConfigDB.openConnection();
        Medico objMedico = null;
        try{
            String sql = "SELECT medico.id_medico, medico.nombre, medico.apellidos, especialidad.nombre as Especialidad FROM medico INNER JOIN especialidad where especialidad.nombre LIKE ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1,"%"+id+"%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                objMedico=new Medico();
                objMedico.setId(objResult.getInt("id_medico"));
                objMedico.setNombre(objResult.getNString("nombre"));
                objMedico.setApellidos(objResult.getNString("apellidos"));
                objMedico.setNombreEps(objResult.getNString("Especialidad"));
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, "ERROR Search: " + error.getMessage());
        }
        ConfigDB.closeConnection();
        return objMedico;
    }

}
