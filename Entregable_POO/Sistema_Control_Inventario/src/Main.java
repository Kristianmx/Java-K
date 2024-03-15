
import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String  option="";
        Inventario objInven = new Inventario();
        do {
            option= JOptionPane.showInputDialog(null,"Menu del Inventario \n" +
                    "1). Agregar Producto.\n" +
                    "2). Eliminar Producto.\n" +
                    "3). Buscar por Nombre.\n" +
                    "4). Buscar por Categoria.\n" +
                    "5). Listar Inventario.\n" +
                    "6). Salir.");

            switch (option){
                case "1":
                    String idIngre = JOptionPane.showInputDialog(null, "Ingrese el id del producto: ");
                    String nombreIngre = JOptionPane.showInputDialog(null, "Ingrese el nombre del producto: ");
                    String precioIngre = JOptionPane.showInputDialog(null, "Ingrese el precio del producto: ");
                    String categoriaIngre = JOptionPane.showInputDialog(null, "Ingrese la categoría del producto: ");
                    String marcaIngre = JOptionPane.showInputDialog(null, "Ingrese la marca del producto: ");
                    try {
                        int id = Integer.parseInt(idIngre);
                        double precio = Double.parseDouble(precioIngre);
                        ProductoEspecifico objProducto =new ProductoEspecifico(id,nombreIngre,precio,categoriaIngre,marcaIngre);
                        objInven.agregarProducto(objProducto);
                        objInven.listarProductos();
                        JOptionPane.showMessageDialog(null,"Producto Agregado Correctamente");

                    }catch (Exception error){
                        JOptionPane.showMessageDialog(null,"Valor o carácter ingresado no valido...");
                        break;
                    }

                    break;
                case "2":
                    idIngre = JOptionPane.showInputDialog(null, "Ingrese el id del producto a eliminar: ");
                    try {
                        int id= Integer.parseInt(idIngre);
                        objInven.eliminarProducto(id);
                        JOptionPane.showMessageDialog(null,"Producto Eliminado Correctamente");
                    }catch (Exception error){
                        JOptionPane.showMessageDialog(null,"Valor ingresado no valido...");
                        break;
                    }
                    break;
                case "3":
                    nombreIngre = JOptionPane.showInputDialog(null,"Ingrese el nombre del producto a buscar: ");
                    JOptionPane.showMessageDialog(null,"Resultado de la búsqueda...");
                    if (objInven.buscarPorNombre(nombreIngre) != null){
                        objInven.buscarPorNombre(nombreIngre);
                    }else {
                        JOptionPane.showMessageDialog(null,"Producto no encontrado: ");
                    }


                    break;
                case "4":
                    categoriaIngre = JOptionPane.showInputDialog(null,"Ingrese el nombre del producto a buscar: ");
                    JOptionPane.showMessageDialog(null,"Resultado de la búsqueda...");
                    if (objInven.buscarPorCategoria(categoriaIngre) != null){
                        objInven.buscarPorCategoria(categoriaIngre);
                    }else {
                        JOptionPane.showMessageDialog(null,"Categoría no encontrada: ");
                    }
                    break;
                case "5":
                    JOptionPane.showMessageDialog(null,"Listado del inventario: \n");
                    objInven.listarProductos();
                    break;
                case "6":
                    JOptionPane.showMessageDialog(null,"Saliendo del inventario...\n");
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"Opción no valida...");
                    break;
            }
        }while (!option.equals("6"));
    }
}