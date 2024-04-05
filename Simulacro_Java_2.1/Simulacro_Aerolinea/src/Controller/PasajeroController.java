package Controller;

import Entity.Pasajero;
import Model.PasajeroModel;

import javax.swing.*;

public class PasajeroController {
    public void MenuPasajeros() {
        PasajeroModel objModel = new PasajeroModel();
        String option;
        do {
            option = JOptionPane.showInputDialog(null, """
                    MENU OF PASSENGERS:
                    1). Insert passenger.
                    2). List passengers.
                    3). Update passenger.
                    4). Delete passenger.
                    5). Search passenger.
                    6). Return the Menu Principal.
                                    
                    Choose a option:
                    """);
            switch (option) {
                case "1":
                    Create();
                    break;
                case "2":
                    getAll();
                    break;
                case "3":
                    update();
                    break;
                case "4":
                    delete();
                    break;
                case "5":
                    String name = JOptionPane.showInputDialog(null, " Enter the name the plane to Search");
                    Pasajero objPasajero = (Pasajero) objModel.SearchByName(name);
                    JOptionPane.showMessageDialog(null, objPasajero.toString());

                    break;
                case "6":
                    JOptionPane.showMessageDialog(null, "Return the principal menu");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Option not valid...");
                    break;
            }
        } while (!option.equals("6"));
    }



    public static void Create(){
        PasajeroModel objModel = new PasajeroModel();
        String nombre = JOptionPane.showInputDialog("Insert the name of the passenger");
        String apellido = JOptionPane.showInputDialog("Insert the surnames of the passenger");
        String documento = JOptionPane.showInputDialog("Insert the  document of the passenger");

        Pasajero objPasajero = new Pasajero();
        objPasajero.setNombre(nombre);
        objPasajero.setApellido(apellido);
        objPasajero.setDocumento(documento);
        objPasajero= (Pasajero) objModel.insert(objPasajero);
        JOptionPane.showMessageDialog(null,objPasajero.toString());
    }
    public static  void getAll(){
    PasajeroModel objModel = new PasajeroModel();
    String listPasajeros = "🙍‍♂️ Passenger list \n";
    for (Object i: objModel.findAll()){
        Pasajero objPasajero = (Pasajero) i;
        listPasajeros+=objPasajero.toString()+"\n";
    }
    JOptionPane.showMessageDialog(null,listPasajeros);
    }
    public static String getAllString(){
        PasajeroModel objModel = new PasajeroModel();
        String listPasajeros = "🙍‍♂️ Passenger list \n";
        for (Object i: objModel.findAll()){
            Pasajero objPasajero = (Pasajero) i;
            listPasajeros+=objPasajero.toString()+"\n";
        }
        return listPasajeros;
    }
    public static  void  update(){
    PasajeroModel objModel = new PasajeroModel();
    String listPasajero = getAllString();
    int id = Integer.parseInt(JOptionPane.showInputDialog(listPasajero+ "\n Enter the Id of the passenger to update."));
    Pasajero objPasajero =(Pasajero) objModel.SearchById(id);
    if(objPasajero==null){
        JOptionPane.showMessageDialog(null, "Passenger not found");
    }else {
        String nombre = JOptionPane.showInputDialog("Insert the name of the passenger",objPasajero.getNombre());
        String apellido = JOptionPane.showInputDialog("Insert the surnames of the passenger",objPasajero.getApellido());
        String documento = JOptionPane.showInputDialog("Insert the  document of the passenger",objPasajero.getDocumento());
        objPasajero.setNombre(nombre);
        objPasajero.setApellido(apellido);
        objPasajero.setDocumento(documento);
        objModel.update(objPasajero);
    }
    }
    public static void delete() {
    PasajeroModel objModel = new PasajeroModel();
    String listPasajero = getAllString();

    int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listPasajero +"\n Enter the Id the passenger to delete "));
    Pasajero objPasajero = (Pasajero) objModel.SearchById(idDelete);
    int confirm = 1;
    if (objPasajero==null){
        JOptionPane.showMessageDialog(null,"Passenger not found");
    }else{
        confirm = JOptionPane.showConfirmDialog(null,"Are you suer want to delete the specialty?\n" + objPasajero.toString());
        if (confirm==0)objModel.delete(objPasajero);
    }

    }
}
