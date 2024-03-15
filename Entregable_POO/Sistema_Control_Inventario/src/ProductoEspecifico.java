public class ProductoEspecifico extends Producto {
    private String categotia;
    private String marca;

    public ProductoEspecifico(int id, String nombre, double precio, String categotia, String marca) {
        super(id, nombre, precio);
        this.categotia = categotia;
        this.marca = marca;
    }

    public String getCategotia() {
        return categotia;
    }

    public String getMarca() {
        return marca;
    }

    public void setCategotia(String categotia) {
        this.categotia = categotia;
    }

    @Override
    public String toString() {
        return super.toString()  +
                ", categotia='" + categotia + '\'' +
                ", marca='" + marca + '\'' ;
    }
}
