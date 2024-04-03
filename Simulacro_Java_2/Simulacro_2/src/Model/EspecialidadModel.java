package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Especialidad;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        Connection ObjConection = ConfigDB.openConnection();
        Especialidad objEspecialidad= (Especialidad) obj;

        try{
            String sql = "INSERT INTO especialidad ( nombre, descripcion) VALUES (?,?);";
            PreparedStatement objPrepare = ObjConection.            prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objEspecialidad.getNombre());
            objPrepare.setString(2,objEspecialidad.getDescripcion());

            objPrepare.execute();

            ResultSet objRest = objPrepare.getGeneratedKeys();

            if (objRest.next()){
                objEspecialidad.setId(objRest.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Specialty Inserted");

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,"ERROR Insert: "+error.getMessage());
        }
        ConfigDB.closeConnection();
        return objEspecialidad;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listEspecialidad = new ArrayList<>();
        Connection objConnection= ConfigDB.openConnection();

        try{
            String sql ="SELECT * FROM especialidad;";
            PreparedStatement objPrepare =objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Especialidad objEspecialidad = new Especialidad();
                objEspecialidad.setId(objResult.getInt("id_especialidad"));
                objEspecialidad.setNombre(objResult.getNString("nombre"));
                objEspecialidad.setDescripcion(objResult.getNString("descripcion"));

                listEspecialidad.add(objEspecialidad);
            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,"ERROR List: "+error.getMessage());
        }
        ConfigDB.closeConnection();
        return listEspecialidad;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Especialidad objEspecialidad = (Especialidad) obj;

        boolean isUpdated= false;

        try{
            String slq = "UPDATE especialidad SET nombre = ?, descripcion = ? WHERE id_especialidad = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(slq);
            objPrepare.setString(1,objEspecialidad.getNombre());
            objPrepare.setString( 2, objEspecialidad.getDescripcion());

            objPrepare.setInt(3,objEspecialidad.getId());

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
        Especialidad objEspcialidad = (Especialidad) obj;

        Connection objConnection = ConfigDB.openConnection();
        boolean isDeleted = false;

        try{
            String sql ="DELETE  FROM especialidad WHERE id_especialidad =?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objEspcialidad.getId());

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

    public Especialidad SearchById(int id){
        Connection objConnection = ConfigDB.openConnection();
        Especialidad objEspecialidad = null;

        try{
          String sql ="SELECT * FROM especialidad WHERE id_especialidad=?;";

          PreparedStatement objPrepare = objConnection.prepareStatement(sql);
          objPrepare.setInt(1 ,id);

          ResultSet objResult = objPrepare.executeQuery();

          while (objResult.next()){
                objEspecialidad=new Especialidad();
              objEspecialidad.setId(objResult.getInt("id_especialidad"));
              objEspecialidad.setNombre(objResult.getNString("nombre"));
              objEspecialidad.setDescripcion(objResult.getNString("descripcion"));
          }

        }catch (Exception error) {
            JOptionPane.showMessageDialog(null, "ERROR Search: " + error.getMessage());
        }
        ConfigDB.closeConnection();
        return objEspecialidad;
    }

}
