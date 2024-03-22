package model;

import database.CRUD;
import database.ConfigLibreria;
import entity.Autor;
import entity.Libro;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigLibreria.openConnection();

        Libro objLibro =(Libro) obj;
        try {
            String sql ="INSERT INTO libros (titulo, anio_public,precio,id_autor) VALUES(?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objLibro.getTitulo());
            objPrepare.setString(2,objLibro.getAnio_public());
            objPrepare.setInt(4,objLibro.getIdAutor());
            objPrepare.setDouble(3,objLibro.getPrecio());
            objPrepare.execute();

            ResultSet objRest = objPrepare.getGeneratedKeys();

            while (objRest.next()){
                objLibro.setId(objRest.getInt(1));
            }
            JOptionPane.showMessageDialog(null,"Book Inserted");
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,"ERROR: " + error.getMessage());
        }
        ConfigLibreria.closeConnection();
        return objLibro;
    }
    public List<Object> findAll() {
        List<Object> listBooks = new ArrayList<>();

        Connection objConnection = ConfigLibreria.openConnection();
        try{
            String sql="SELECT * FROM libros;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Libro objLibro = new Libro();

                objLibro.setId(objResult.getInt("id"));
                objLibro.setTitulo(objResult.getNString("titulo"));
                objLibro.setAnio_public( objResult.getNString("anio_public"));
                objLibro.setPrecio(objResult.getDouble("precio"));

                objLibro.setIdAutor(objResult.getInt("id_autor"));
                listBooks.add(objLibro);
            }
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,"ERROR: " + error.getMessage());
        }
        ConfigLibreria.closeConnection();
        return listBooks;
    }
    @Override
    public boolean update(Object obj) {
        Connection objConnection= ConfigLibreria.openConnection();

          Libro objLibro = (Libro) obj;

        boolean isUpdated= false;

        try {
            String sql ="UPDATE libros SET titulo= ?, anio_public = ?, precio= ? ,id_autor= ? WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objLibro.getTitulo());
            objPrepare.setString(2,objLibro.getAnio_public());
            objPrepare.setDouble(3,objLibro.getPrecio());
            objPrepare.setInt(4,objLibro.getIdAutor());
            objPrepare.setInt(5,objLibro.getId());

            int totalRowAffec = objPrepare.executeUpdate();
            if (totalRowAffec>0){
                isUpdated=true;
                JOptionPane.showMessageDialog(null,"the update was successful");
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, "ERROR: "+ error.getMessage());
        }
        ConfigLibreria.closeConnection();
        return isUpdated;
    }
    @Override
    public boolean delete(Object obj) {

        Libro objLibro= (Libro) obj;

        Connection objConnection = ConfigLibreria.openConnection();

        boolean isDeleted = false;

        try{
            String sql = "DELETE FROM libros WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objLibro.getId());

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
    public Libro SearchById(int id){
        Connection objConnection = ConfigLibreria.openConnection();

        Libro objLibro = null;

        try{
            String slq ="SELECT * FROM libros WHERE id =?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(slq);

            objPrepare.setInt(1,id);

            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()){
                objLibro=new Libro();
                objLibro.setId(objResult.getInt("id"));
                objLibro.setTitulo(objResult.getNString("titulo"));
                objLibro.setAnio_public(objResult.getNString("anio_public"));
                objLibro.setPrecio((objResult.getDouble("precio")));
                objLibro.setIdAutor(objResult.getInt("id_autor"));

            }



        }catch (Exception error){
            JOptionPane.showMessageDialog(null,"ERROR:" + error.getMessage());

        }
        ConfigLibreria.closeConnection();
        return objLibro;
    }
    public List<Libro> SearchByIdLIst(int id){
        Connection objConnection = ConfigLibreria.openConnection();

        Libro objLibro = null;
        List<Libro>  listLibro = new ArrayList<>();
        try{
            String slq ="SELECT * FROM libros WHERE id =?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(slq);

            objPrepare.setInt(1,id);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objLibro=new Libro();
                objLibro.setId(objResult.getInt("id"));
                objLibro.setTitulo(objResult.getNString("titulo"));
                objLibro.setAnio_public(objResult.getNString("anio_public"));
                objLibro.setPrecio((objResult.getDouble("precio")));
                objLibro.setIdAutor(objResult.getInt("id_autor"));
                listLibro.add(objLibro);
            }



        }catch (Exception error){
            JOptionPane.showMessageDialog(null,"ERROR:" + error.getMessage());

        }
        ConfigLibreria.closeConnection();
        return listLibro;
    }
    public List<Libro> SearchByTitu(String titulo){
        Connection objConnection = ConfigLibreria.openConnection();

        Libro objLibro = null;
        List<Libro>  listLibro = new ArrayList<>();
        try{
            String slq ="SELECT * FROM libros WHERE titulo like ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(slq);

            objPrepare.setString(1,"%"+titulo+"%");

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objLibro=new Libro();
                objLibro.setId(objResult.getInt("id"));
                objLibro.setTitulo(objResult.getNString("titulo"));
                objLibro.setAnio_public(objResult.getNString("anio_public"));
                objLibro.setPrecio((objResult.getDouble("precio")));
                objLibro.setIdAutor(objResult.getInt("id_autor"));
                listLibro.add(objLibro);

            }



        }catch (Exception error){
            JOptionPane.showMessageDialog(null,"ERROR:" + error.getMessage());

        }
        ConfigLibreria.closeConnection();
        return listLibro;
    }
    public List<Libro> SearchByIdAutor(int id){
        Connection objConnection = ConfigLibreria.openConnection();

        Libro objLibro = null;
        List<Libro>  listLibro = new ArrayList<>();
        try{
            String slq ="SELECT * FROM libros WHERE id_autor =?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(slq);

            objPrepare.setInt(1,id);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objLibro=new Libro();
                objLibro.setId(objResult.getInt("id"));
                objLibro.setTitulo(objResult.getNString("titulo"));
                objLibro.setAnio_public(objResult.getNString("anio_public"));
                objLibro.setPrecio((objResult.getDouble("precio")));
                objLibro.setIdAutor(objResult.getInt("id_autor"));
                listLibro.add(objLibro);

            }



        }catch (Exception error){
            JOptionPane.showMessageDialog(null,"ERROR:" + error.getMessage());

        }
        ConfigLibreria.closeConnection();
        return listLibro;
    }

}
