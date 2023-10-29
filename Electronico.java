//Bryan Alberto Martínez Orellana
//Carnét 23542
//Ingeniería en Ciencias de la Computación
//Programación Orientada a Objetos
//Creación: 29/10/2023
//Última modificación: 29/10/2023

public interface Electronico {
    //Métodos que deben ser empleados por todos los objetos que implementen Electrónico
    public void Encender();
    public void Apagar();
    public String Validar();
    public String toString();
    public int getCodigo();
}