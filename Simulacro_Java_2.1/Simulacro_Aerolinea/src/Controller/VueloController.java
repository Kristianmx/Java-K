package Controller;


import Entity.Avion;
import Entity.Vuelo;
import Model.AvionModel;
import Model.VueloModel;

import javax.swing.*;
import java.util.List;

public class VueloController {
    public void MenuVuelos(){
        VueloModel objmodel= new VueloModel();
        String option;
        do {
            option = JOptionPane.showInputDialog(null, """
                   MENU OF FLIGHT:
                   1). Insert flight.
                   2). List flights.
                   3). Update flight.
                   4). Delete flight.
                   5). Search flight for destiny.
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
                   getAllDestiny();
                    break;
                case "6":
                    JOptionPane.showMessageDialog(null, "Return the principal menu");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Option not valid...");
                    break;
            }
        }while (!option.equals("6"));
    }
    public static void Create(){
       VueloModel objModel =new VueloModel();

       String destino = JOptionPane.showInputDialog("Insert the destiny of flight");
        String fecha = JOptionPane.showInputDialog("Insert the date of flight (YYYY-MM-DD)");
        String hora = JOptionPane.showInputDialog("Insert the time of flight (HH:mm)" );

        Vuelo objVuelo = new Vuelo();

        AvionModel objAvion = new AvionModel();
        List<Object> listAviones = objAvion.findAll();
        String[] choices = new String[listAviones.size()];

        int index=0;
        for (Object i: listAviones){
            Avion avion = (Avion) i;
            choices[index]= avion.getModelo();
            index++;
        }
        String input = (String) JOptionPane.showInputDialog(null,"\n Select a plane...","Planes available:",JOptionPane.QUESTION_MESSAGE,null,choices,choices[0]);

        int Id_avion=0;
        for (Object i: listAviones){
            Avion avion = (Avion) i;
            String modelo = avion.getModelo();
            if (modelo.equals(input))Id_avion=avion.getId();
        }
        objVuelo.setDestino(destino);
        objVuelo.setFechaSalida(fecha);
        objVuelo.setHoraSalida(hora);
        objVuelo.setIdAvion(Id_avion);
        objVuelo=(Vuelo) objModel.insert(objVuelo);
        JOptionPane.showMessageDialog(null,objVuelo.toString());

    }
    public static  void getAll() {
        VueloModel objModel =new VueloModel();
        String listVuelos ="🛫 Flight List: 🛫\n";
        for (Object i: objModel.findAll()){
            Vuelo objVuelo = (Vuelo) i;
            listVuelos+=objVuelo.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listVuelos);
    }
    public static String getAllString(){
        VueloModel objModel =new VueloModel();
        String listVuelos ="🛫 Flight List: 🛫\n";
        for (Object i: objModel.findAll()){
            Vuelo objVuelo = (Vuelo) i;
            listVuelos+=objVuelo.toString()+"\n";
        }
        return listVuelos;
    }
    public static void getAllDestiny(){
        String destino = JOptionPane.showInputDialog("Insert the destiny: ");
        VueloModel objModel =new VueloModel();
        String listVuelos ="🛫 Flight List for Destiny: 🛫\n";
        for (Object i: objModel.SearchByDestiny(destino)){
            Vuelo objVuelo = (Vuelo) i;
            listVuelos+=objVuelo.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null, listVuelos);
    }

    public static  void  update() {
        VueloModel objModal = new VueloModel();
        String list = getAllString();
        int id = Integer.parseInt(JOptionPane.showInputDialog(list + " \nEnter the Id of the flight."));
        Vuelo objVuelo =(Vuelo) objModal.SearchById(id);
        if (objVuelo == null) {
            JOptionPane.showMessageDialog(null, "Flight not found");
        }else {

            String destino = JOptionPane.showInputDialog("Insert the destiny of flight",objVuelo.getDestino());
            String fecha = JOptionPane.showInputDialog("Insert the date of flight (YYYY-MM-DD)",objVuelo.getFechaSalida());
            String hora = JOptionPane.showInputDialog("Insert the time of flight (HH:mm)",objVuelo.getHoraSalida() );

            AvionModel objAvion = new AvionModel();
            List<Object> listAviones = objAvion.findAll();
            String[] choices = new String[listAviones.size()];

            int index=0;
            for (Object i: listAviones){
                Avion avion = (Avion) i;
                choices[index]= avion.getModelo();
                index++;
            }

            Avion modeloAvion=  (Avion) objAvion.SearchById(objVuelo.getIdAvion())  ;
            String input = (String) JOptionPane.showInputDialog(null,"\n Select a plane...\n" +"registered plane:  "+modeloAvion.getModelo() ,"PLanes available:",JOptionPane.QUESTION_MESSAGE,null,choices,choices[0]);

            int Id_avion=0;
            for (Object i: listAviones){
                Avion avion = (Avion) i;
                String modelo = avion.getModelo();
                if (modelo.equals(input))Id_avion=avion.getId();
            }
            objVuelo.setDestino(destino);
            objVuelo.setFechaSalida(fecha);
            objVuelo.setHoraSalida(hora);
            objVuelo.setIdAvion(Id_avion);
            objModal.update(objVuelo);
        }
    }
    public static void delete() {
    VueloModel objModel = new VueloModel();
    String list = getAllString();
    int idDeleted = Integer.parseInt(JOptionPane.showInputDialog(list +"\n Enter the Id the flight to delete"));
    Vuelo objVuelo =(Vuelo) objModel.SearchById(idDeleted);
        int Confirm=1;
        if (objVuelo==null){
            JOptionPane.showMessageDialog(null,"flight not found");
        }else {
            Confirm=JOptionPane.showConfirmDialog(null, "Are you suer want to delete the flight?\n" + objVuelo.toString());
            if (Confirm==0)objModel.delete(objVuelo);
        }
    }
}
