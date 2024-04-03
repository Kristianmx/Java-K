package Model;

import Database.CRUD;
import Database.ConfigDB;
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
import java.util.SimpleTimeZone;

public class PacienteModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Paciente objPaciente = (Paciente) obj;

        try {
            String sql = "INSERT INTO paciente (nombre,apellidos,fecha_nacimiento,documento_identidad) VALUES (?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objPaciente.getNombre());
            objPrepare.setString(2, objPaciente.getApellido());
            objPrepare.setString(3, objPaciente.getFechaNacimiento());
            objPrepare.setString(4, objPaciente.getDocumento());

            objPrepare.execute();

            ResultSet objRest = objPrepare.getGeneratedKeys();

            if (objRest.next()){
                objPaciente.setId(objRest.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "patient Inserted");
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,"ERROR Insert: "+error.getMessage());
        }
        ConfigDB.closeConnection();
        return objPaciente;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listPaciente = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try{
            String sql ="SELECT * FROM paciente;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()){
                Paciente objPaciente = new Paciente();
                objPaciente.setId(objResult.getInt("id_paciente"));
                objPaciente.setNombre(objResult.getNString("nombre"));
                objPaciente.setApellido(objResult.getNString("apellidos"));

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                objPaciente.setFechaNacimiento(dateFormat.format((objResult.getDate("fecha_nacimiento"))));
                objPaciente.setDocumento(objResult.getNString("documento_identidad"));

                listPaciente.add(objPaciente);
            }
        }catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "ERROR List: " + error.getMessage());
        }
        ConfigDB.closeConnection();
        return listPaciente;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Paciente objPanciente= (Paciente) obj;

        boolean isUpdated = false;

        try{
            String sql="UPDATE paciente SET nombre=?,apellidos=?,fecha_nacimiento=?,documento_identidad=? WHERE id_paciente=?;";
            PreparedStatement objPrepare= objConnection.prepareStatement(sql);
            objPrepare.setString(1,objPanciente.getNombre());
            objPrepare.setString(2,objPanciente.getApellido());
            objPrepare.setString(3,objPanciente.getFechaNacimiento());
            objPrepare.setString(4,objPanciente.getDocumento());
            objPrepare.setInt(5,objPanciente.getId());
            int totalRowaffec = objPrepare.executeUpdate();

            if (totalRowaffec>0){
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
        Paciente objPaciente = (Paciente) obj;

        Connection objConnection = ConfigDB.openConnection();
        boolean isDeleted = false;

        try{
            String sql = "DELETE FROM paciente WHERE id_paciente = ?;";

            PreparedStatement objprepare = objConnection.prepareStatement(sql);

            objprepare.setInt(1,objPaciente.getId());

            int totalAffete = objprepare.executeUpdate();

            if (totalAffete>0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The delete was successfull...");
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, "ERROR Delete: " + error.getMessage());
        }
        ConfigDB.closeConnection();
        return isDeleted;
    }
    public Paciente SearchById(int id){
        Connection objConnection = ConfigDB.openConnection();
        Paciente objPaciente = null;

        try{
            String sql ="SELECT * FROM paciente WHERE id_paciente=?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1 ,id);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objPaciente=new Paciente();
                objPaciente.setId(objResult.getInt("id_paciente"));
                objPaciente.setNombre(objResult.getNString("nombre"));
                objPaciente.setApellido(objResult.getNString("apellidos"));
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                objPaciente.setFechaNacimiento(dateFormat.format((objResult.getDate("fecha_nacimiento"))));
                objPaciente.setDocumento(objResult.getNString("documento_identidad"));

            }

        }catch (Exception error) {
            JOptionPane.showMessageDialog(null, "ERROR Search: " + error.getMessage());
        }
        ConfigDB.closeConnection();
        return objPaciente;
    }
    public Paciente SearchByDocument(String Doc){
        Connection objConnection = ConfigDB.openConnection();
        Paciente objPaciente = null;

        try{
            String sql ="SELECT * FROM paciente WHERE documento_identidad LIKE ? ;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1 ,"%"+Doc+"%");

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objPaciente=new Paciente();
                objPaciente.setId(objResult.getInt("id_paciente"));
                objPaciente.setNombre(objResult.getNString("nombre"));
                objPaciente.setApellido(objResult.getNString("apellidos"));

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                objPaciente.setFechaNacimiento(dateFormat.format((objResult.getDate("fecha_nacimiento"))));

                objPaciente.setDocumento(objResult.getNString("documento_identidad"));

            }

        }catch (Exception error) {
            JOptionPane.showMessageDialog(null, "ERROR Search: " + error.getMessage());
        }
        ConfigDB.closeConnection();
        return objPaciente;
    }

}
