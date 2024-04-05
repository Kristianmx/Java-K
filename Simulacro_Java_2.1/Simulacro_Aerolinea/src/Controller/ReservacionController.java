package Controller;


import Entity.Avion;
import Entity.Pasajero;
import Entity.Reservacion;
import Entity.Vuelo;
import Model.AvionModel;
import Model.PasajeroModel;
import Model.ReservacionModel;
import Model.VueloModel;

import javax.swing.*;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class ReservacionController {

    public void MenuReserva() {
        ReservacionModel objModel = new ReservacionModel();
        String option;
        do {
            option = JOptionPane.showInputDialog(null, """
                    MENU OF RESERVATION:
                    1). Insert reservation.
                    2). List reservations.
                    3). Update reservation.
                    4). Delete reservation.
                    5). Search reservation for a flight.
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
                    getAll(JOptionPane.showInputDialog("Enter the name of destiny to search."));
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
    public static void Create() {
        ReservacionModel objModel = new ReservacionModel();
        Reservacion objReserva = new Reservacion();

        PasajeroModel objPasajeroModal = new PasajeroModel();
        List<Object> listPasajeros = objPasajeroModal.findAll();
        String[] choicesPas = new String[listPasajeros.size()];

        int indexPas = 0;
        for (Object i : listPasajeros) {
            Pasajero pasajero = (Pasajero) i;
            choicesPas[indexPas] = pasajero.getNombre() + " " + pasajero.getApellido();
            indexPas++;
        }
        String inputPas = (String) JOptionPane.showInputDialog(null, "\n Select a passenger...", "Passengers available:", JOptionPane.QUESTION_MESSAGE, null, choicesPas, choicesPas[0]);
        int Id_pasajero = 0;
        for (Object i : listPasajeros) {
            Pasajero pasajero = (Pasajero) i;
            String nombreCompleto = pasajero.getNombre() + " " + pasajero.getApellido();
            if (nombreCompleto.equals(inputPas)) Id_pasajero = pasajero.getId_pasajero();
        }


        String fecha = JOptionPane.showInputDialog("Insert  the date");
        int option = 0;
        String asiento = "";
        int Id_Vuelo = 0;


        while (option != 1) {
            VueloModel objVueloModal = new VueloModel();
            List<Object> listVuelo = objVueloModal.findAll();
            String[] choicesVuelo = new String[listVuelo.size()];

            int indexVuelo = 0;
            for (Object i : listVuelo) {
                Vuelo vuelo = (Vuelo) i;
                choicesVuelo[indexVuelo] = "( " + vuelo.getDestino() + " / " + vuelo.getFechaSalida() + " / " + vuelo.getHoraSalida() + " ).";
                indexVuelo++;
            }
            String inputVuelo = (String) JOptionPane.showInputDialog(null, "\n Select a flight...", "Flights available:", JOptionPane.QUESTION_MESSAGE, null, choicesVuelo, choicesVuelo[0]);

            for (Object i : listVuelo) {
                Vuelo vuelo = (Vuelo) i;
                String reserva = "( " + vuelo.getDestino() + " / " + vuelo.getFechaSalida() + " / " + vuelo.getHoraSalida() + " ).";
                if (reserva.equals(inputVuelo)) Id_Vuelo = vuelo.getIdVuelo();
            }

            List<Object> listAsiesto = objModel.findAll();
            AvionModel objAvionModel = new AvionModel();
            VueloModel objVueloModel = new VueloModel();
            Vuelo objVuelo = (Vuelo) objVueloModel.SearchById(Id_Vuelo);
            Avion objavion = (Avion) objAvionModel.SearchById(objVuelo.getIdAvion());
            int capacidad = 0;

            for (Object i : listAsiesto) {
                Reservacion reserva = (Reservacion) i;
                if (Id_Vuelo == reserva.getIdVuelo()) {
                    capacidad++;
                }
            }
            if (capacidad >= objavion.getCapacidad()) {
                JOptionPane.showMessageDialog(null, """
                        this flight is a ready full.
                        Please select another flight.
                        """);
            } else {
                int optionA = 0;
                while (optionA != 1) {
                    String asientoEle = JOptionPane.showInputDialog("Insert the location of seat:");
                    boolean isAvilable = true;

                    for (Object i : listAsiesto) {
                        Reservacion reserva = (Reservacion) i;
                        if (asientoEle.equals(reserva.getAsiento()) ) isAvilable = false;
                    }

                    if (isAvilable == true) {
                        asiento = asientoEle;
                        optionA = 1;
                    } else {
                        JOptionPane.showMessageDialog(null, """
                                this seat is not vacant.
                                Please select another seat.
                                """);
                    }
                }
                option = 1;
            }


        }
            objReserva.setIdPasajero(Id_pasajero);
            objReserva.setIdVuelo(Id_Vuelo);
            objReserva.setFechaReserva(fecha);
            objReserva.setAsiento(asiento);
            objReserva= (Reservacion) objModel.insert(objReserva);
            JOptionPane.showMessageDialog(null,objReserva.toString());

    }
    public static  void getAll(){
        ReservacionModel objModel =new ReservacionModel();
        String listReserva = "📅 Reservation List: 📅\n";
        for (Object i : objModel.findAll()){
            Reservacion reserva = (Reservacion) i;
            listReserva+=reserva.toString() +"\n";
        }
        JOptionPane.showMessageDialog(null,listReserva);
    }
    public static  void getAll(String Destino){
        ReservacionModel objModel =new ReservacionModel();
        String listReserva = "📅 Reservation List: 📅\n";
        for (Object i : objModel.SearchByFlight(Destino)){
            Reservacion reserva = (Reservacion) i;
            listReserva+=reserva.toString(1) +"\n";
        }
        JOptionPane.showMessageDialog(null,listReserva);
    }
    public static String getAllString(){
        ReservacionModel objModel =new ReservacionModel();
        String listReserva = "📅 Reservation List: 📅\n";
        for (Object i : objModel.findAll()){
            Reservacion reserva = (Reservacion) i;
            listReserva+=reserva.toString() +"\n";
        }
        return listReserva;
    }
    public static  void  update() {
        ReservacionModel objModel =new ReservacionModel();
        String list = getAllString();
        int id = Integer.parseInt(JOptionPane.showInputDialog(list + "\n Enter the Id of the reservation."));
        Reservacion objReserva = (Reservacion) objModel.SearchById(id);
        if (objReserva==null){
            JOptionPane.showMessageDialog(null,"reservation not found");
        }else {
            PasajeroModel objPasajeroModal = new PasajeroModel();
            List<Object> listPasajeros = objPasajeroModal.findAll();
            String[] choicesPas = new String[listPasajeros.size()];

            int indexPas = 0;
            for (Object i : listPasajeros) {
                Pasajero pasajero = (Pasajero) i;
                choicesPas[indexPas] = pasajero.getNombre() + " " + pasajero.getApellido();
                indexPas++;
            }
            String inputPas = (String) JOptionPane.showInputDialog(null, "\n Select a passenger...", "Passengers available:", JOptionPane.QUESTION_MESSAGE, null, choicesPas, choicesPas[0]);
            int Id_pasajero = 0;
            for (Object i : listPasajeros) {
                Pasajero pasajero = (Pasajero) i;
                String nombreCompleto = pasajero.getNombre() + " " + pasajero.getApellido();
                if (nombreCompleto.equals(inputPas)) Id_pasajero = pasajero.getId_pasajero();
            }


            String fecha = JOptionPane.showInputDialog("Insert  the date",objReserva.getFechaReserva());
            int option = 0;
            String asiento = "";
            int Id_Vuelo = 0;


            while (option != 1) {
                VueloModel objVueloModal = new VueloModel();
                List<Object> listVuelo = objVueloModal.findAll();
                String[] choicesVuelo = new String[listVuelo.size()];

                int indexVuelo = 0;
                for (Object i : listVuelo) {
                    Vuelo vuelo = (Vuelo) i;
                    choicesVuelo[indexVuelo] = "( " + vuelo.getDestino() + " / " + vuelo.getFechaSalida() + " / " + vuelo.getHoraSalida() + " ).";
                    indexVuelo++;
                }
                String inputVuelo = (String) JOptionPane.showInputDialog(null, "\n Select a flight...", "Flights available:", JOptionPane.QUESTION_MESSAGE, null, choicesVuelo, choicesVuelo[0]);

                for (Object i : listVuelo) {
                    Vuelo vuelo = (Vuelo) i;
                    String reserva = "( " + vuelo.getDestino() + " / " + vuelo.getFechaSalida() + " / " + vuelo.getHoraSalida() + " ).";
                    if (reserva.equals(inputVuelo)) Id_Vuelo = vuelo.getIdVuelo();
                }

                List<Object> listAsiesto = objModel.findAll();
                AvionModel objAvionModel = new AvionModel();
                VueloModel objVueloModel = new VueloModel();
                Vuelo objVuelo = (Vuelo) objVueloModel.SearchById(Id_Vuelo);
                Avion objavion = (Avion) objAvionModel.SearchById(objVuelo.getIdAvion());
                int capacidad = 0;

                    if (Id_Vuelo!=objReserva.getIdVuelo() ) {
                        for (Object i : listAsiesto) {
                            Reservacion reserva = (Reservacion) i;
                            System.out.println(Id_Vuelo + "-----" + reserva.getIdVuelo());
                            if (Id_Vuelo == reserva.getIdVuelo()) {
                                capacidad++;
                            }
                        }
                    }
                System.out.println(capacidad +"  --- "+ objavion.getCapacidad());
                if (capacidad >= objavion.getCapacidad()) {
                    JOptionPane.showMessageDialog(null, """
                        this flight is a ready full.
                        Please select another flight.
                        """);
                } else {
                    int optionA = 0;
                    while (optionA != 1) {
                        String asientoEle = JOptionPane.showInputDialog("Insert the location of seat:");
                        boolean isAvilable = true;

                        for (Object i : listAsiesto) {
                            Reservacion reserva = (Reservacion) i;
                            System.out.println(asientoEle +"--"+ reserva.getAsiento());
                            if (asientoEle.equals(reserva.getAsiento()) ) isAvilable = false;
                        }

                        if (isAvilable == true) {
                            asiento = asientoEle;
                            optionA = 1;
                        } else {
                            JOptionPane.showMessageDialog(null, """
                                this seat is not vacant.
                                Please select another seat.
                                """);
                        }
                    }
                    option = 1;
                }

            }
            objReserva.setIdPasajero(Id_pasajero);
            objReserva.setIdVuelo(Id_Vuelo);
            objReserva.setFechaReserva(fecha);
            objReserva.setAsiento(asiento);
            objModel.update(objReserva);
        }


    }
    public static void delete() {
        ReservacionModel objModel =new ReservacionModel();

        String list = getAllString();
        int idDeleted = Integer.parseInt(JOptionPane.showInputDialog(list+ "\n Enter the Id the reservation to delete: "));
        Reservacion objReserva =(Reservacion) objModel.SearchById(idDeleted);
        int Confirm=1;
        if (objReserva==null){
            JOptionPane.showMessageDialog(null, "Reservation not found");
        }else {
            Confirm=JOptionPane.showConfirmDialog(null, "Are you suer want to delete the flight?\n" + objReserva.toString());
            if (Confirm==0) objModel.delete(objReserva);
        }

    }
}
