//Bryan Alberto Martínez Orellana
//Carnét 23542
//Ingeniería en Ciencias de la Computación
//Programación Orientada a Objetos
//Creación: 29/10/2023
//Última modificación: 29/10/2023

public class Computadora implements Electronico {
    //Atributos de una computadora
    private String estado;
    private int codigo;
    private String marca;

    //Constructor de computadora
    public Computadora(String estado, int codigo, String marca) {
        this.estado = estado;
        this.codigo = codigo;
        this.marca = marca;
    }

    //Encender computadora
    public void Encender(){
        this.estado = "Encendido";
    }

    //Apagar computadora
    public void Apagar(){
        this.estado = "Apagado";
    }

    //Conocer si la computadora está encendida o apagada
    public String Validar(){
        return this.estado;
    }

    //Devolver información de la computadora
    public String toString(){
        return "--- " + "La computadora: con código " + codigo + " y de marca " + marca + " está en estado " + estado + " ---";
    }

    //Devolver código de la computadora
    public int getCodigo() {
        return this.codigo;
    }
    
}
