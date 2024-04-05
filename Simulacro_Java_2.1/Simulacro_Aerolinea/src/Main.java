import Controller.AvionController;
import Controller.PasajeroController;
import Controller.ReservacionController;
import Controller.VueloController;
import Database.ConfigDB;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        AvionController objAvionController = new AvionController();
        PasajeroController objPasajeroController = new PasajeroController();
        VueloController objVueloController = new VueloController();
        ReservacionController objReservaController = new ReservacionController();
        String option ="";
        do{
           option= JOptionPane.showInputDialog("""
                    MAIN MENU:
                    1). Planes.
                    2). Passengers.
                    3). Flights.
                    4). Reservations.
                    5). Exit of menu.
                    
                    Choose a option:
                    """);
            switch (option) {
                case "1":
                    objAvionController.MenuAviones();
                    break;
                case "2":
                    objPasajeroController.MenuPasajeros();
                    break;
                case "3":
                    objVueloController.MenuVuelos();
                    break;
                case "4":
                    objReservaController.MenuReserva();
                    break;
                case "5":
                    JOptionPane.showMessageDialog(null, """
                            Menu Closed.
                            Good Bye...
                            """);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Option not valid...");
                    break;
            }
        }while (!option.equals("5"));

    }
}