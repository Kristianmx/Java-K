package entity;

public class Libro {
    private int id;
    private String titulo;
    private String anio_public;
    private double precio;
    private  int idAutor;
    private String autor;

    public Libro() {
    }

    public Libro(int id, String titulo, String anio_public, double precio,int idAutor, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.anio_public = anio_public;
        this.precio = precio;
        this.autor = autor;
        this.idAutor= idAutor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAnio_public() {
        return anio_public;
    }

    public void setAnio_public(String anio_public) {
        this.anio_public = anio_public;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", anio_public='" + anio_public + '\'' +
                ", precio=" + precio +
                ", idAutor=" + idAutor +
                '}';
    }

    public String toString(int u) {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", anio_public='" + anio_public + '\'' +
                ", precio=" + precio +
                ", idAutor=" + idAutor +
                ", autor=" + autor +
                '}';
    }
    public String toStringAdvance(){
        return
                "titulo=' " + titulo + '\'' +
                ",  anio_public= '" + anio_public + '\'' +
                ",  precio= " + precio +
                ",  autor= " + autor;

    }
}
