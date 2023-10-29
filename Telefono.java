//Bryan Alberto Martínez Orellana
//Carnét 23542
//Ingeniería en Ciencias de la Computación
//Programación Orientada a Objetos
//Creación: 29/10/2023
//Última modificación: 29/10/2023

public class Telefono implements Electronico{
    //Atributos de teléfono
    private String estado;
    private int codigo;
    private String modelo;

    //Constructor de teléfono
    public Telefono(String estado, int codigo, String modelo) {
        this.estado = estado;
        this.codigo = codigo;
        this.modelo = modelo;
    }

    //Encender teléfono
    public void Encender(){
        this.estado = "Encendido";
    }

    //Apagar teléfono
    public void Apagar(){
        this.estado = "Apagado";
    }

    //Conocer el estado del teléfono
    public String Validar(){
        return this.estado;
    }

    //Devolver información del teléfono
    public String toString(){
        return "--- " + "El teléfono: con código " + codigo + " y modelo " + modelo + " está en estado " + estado + " ---";
    }

    //Devolver código del teléfono
    public int getCodigo() {
        return this.codigo;
    }

}
