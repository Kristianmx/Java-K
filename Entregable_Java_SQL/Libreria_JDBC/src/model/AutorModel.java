package model;

import database.CRUD;
import database.ConfigLibreria;
import entity.Autor;
import entity.Libro;


import javax.swing.*;
import java.nio.file.WatchEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigLibreria.openConnection();

        Autor objAutor =(Autor) obj;
        try {
            String sql ="INSERT INTO autor (nombre,nacionalidad) VALUES(?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objAutor.getNombre());
            objPrepare.setString(2,objAutor.getNacionalidad());

            objPrepare.execute();

            ResultSet objRest = objPrepare.getGeneratedKeys();

            while (objRest.next()){
                objAutor.setId(objRest.getInt(1));
            }
            JOptionPane.showMessageDialog(null,"Author Inserted");
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,"ERROR: " + error.getMessage());
        }
        ConfigLibreria.closeConnection();
        return objAutor;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listAutor = new ArrayList<>();

        Connection objConnection = ConfigLibreria.openConnection();
        try{
            String sql="SELECT * FROM autor;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Autor objAutor = new Autor();

                objAutor.setId(objResult.getInt("id"));
                objAutor.setNombre(objResult.getNString("nombre"));
                objAutor.setNacionalidad(objResult.getNString("nacionalidad"));

                listAutor.add(objAutor);
            }
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,"ERROR: " + error.getMessage());
        }
        ConfigLibreria.closeConnection();
        return listAutor;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection= ConfigLibreria.openConnection();

        Autor objAutor = (Autor) obj;

        boolean isUpdated= false;

        try {
            String sql ="UPDATE autor SET nombre= ?, nacionalidad = ? WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objAutor.getNombre());
            objPrepare.setString(2,objAutor.getNacionalidad());
            objPrepare.setInt(3,objAutor.getId());

            int totalRowAffec = objPrepare.executeUpdate();
            if (totalRowAffec>0){
                isUpdated=true;
                JOptionPane.showMessageDialog(null,"the update was successful");
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, "ERROR: "+ error.getMessage());
        }
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {

        Autor objAutor = (Autor) obj;

        Connection objConnection = ConfigLibreria.openConnection();

        boolean isDeleted = false;

        try{
            String sql = "DELETE FROM autor WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objAutor.getId());

            int totalAffete = objPrepare.executeUpdate();

            if (totalAffete >0){
                isDeleted= true;
                JOptionPane.showMessageDialog(null, "The update was successful...");
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null,"Error Delete: " + error.getMessage());
        }
        ConfigLibreria.closeConnection();
        return isDeleted;
    }
    public Autor SearchById(int id){
        Connection objConnection = ConfigLibreria.openConnection();

        Autor objAutor = null;
        try{
            String slq ="SELECT * FROM autor WHERE id =?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(slq);

            objPrepare.setInt(1,id);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objAutor=new Autor();
                objAutor.setId(objResult.getInt("id"));
                objAutor.setNombre(objResult.getNString("nombre"));
                objAutor.setNacionalidad(objResult.getNString("nacionalidad"));
            }



        }catch (Exception error){
            JOptionPane.showMessageDialog(null,"ERROR:" + error.getMessage());

        }
        ConfigLibreria.closeConnection();
        return objAutor;
    }

    public List<Libro> SearchAdvance(String name){
        Connection objLibreria = ConfigLibreria.openConnection();
        List<Libro> listResul = new ArrayList<>();
            try {
                String sql ="SELECT libros.titulo, libros.anio_public, libros.precio, autor.nombre FROM libros INNER JOIN autor\n" +
                        " WHERE autor.nombre LIKE ? AND libros.id_autor = autor.id;";
                PreparedStatement objPrepared = objLibreria.prepareStatement(sql);

                objPrepared.setString(1,"%"+name+"%");
                ResultSet objResul = objPrepared.executeQuery();
                while (objResul.next()) {
                    Libro ObjLibro = new Libro();

                    ObjLibro.setTitulo(objResul.getNString("titulo"));
                    ObjLibro.setAnio_public(objResul.getNString("anio_public"));
                    ObjLibro.setPrecio(objResul.getDouble("precio"));
                    ObjLibro.setAutor(objResul.getNString("nombre"));
                    listResul.add(ObjLibro);

                }
            }catch (Exception error){
                JOptionPane.showMessageDialog(null,"ERROR: "+error.getMessage());
            }

        ConfigLibreria.closeConnection();
            return listResul;
    }
}
