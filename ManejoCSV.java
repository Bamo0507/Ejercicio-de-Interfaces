//Bryan Alberto Martínez Orellana
//Carnét 23542
//Ingeniería en Ciencias de la Computación
//Programación Orientada a Objetos
//Creación: 29/10/2023
//Última modificación: 29/10/2023

//Librerías a utilizar
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import java.util.Random;


public class ManejoCSV {
    //Método para recopilar información
    public ArrayList<Electronico> leerCSV(String archivo){
        ArrayList<Electronico> electronicos = new ArrayList<>();
        //Intentamos leer el archivo
        try(BufferedReader br = new BufferedReader(new FileReader(archivo))){
            br.readLine();
            String linea;
            //Comenzamos a leer todas las líneas del CSV
            while((linea = br.readLine()) != null){
                String[] datos = linea.split(",");
                if(datos.length >= 3){
                    //Recopilar informacion
                    int codigo = Integer.parseInt(datos[0].trim());
                    String estado = datos[1].trim();
                    //Dependiendo del código se agrega el tipo de objeto correspondiente
                    if(codigo >= 10000 && codigo <= 99999){
                        String modelo = datos[3].trim();
                        electronicos.add(new Telefono(estado, codigo, modelo));
                    } else{
                        String marca = datos[2].trim();
                        electronicos.add(new Computadora(estado, codigo, marca));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("No se ha encontraodo el archivo que proporcionaste :(");
            e.printStackTrace();
        }
        return electronicos;
    }

    //Método para agregar dispositivos
    public void agregarElectronicos(String archivo, ArrayList<Electronico> electronicos){
        String estado = "";
        boolean valid = true;
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int codigo = 0;
        int selecciontipo = 0;
        StringBuilder sb = new StringBuilder();
        //Solicitamos al usuario cómo está el dispositivo
        while(!(selecciontipo >= 1 && selecciontipo <= 2)){
            System.out.println("¿En qué estado está el dispositivo que se está agregando?");
            System.out.println(definirEstado());
            switch(selecciontipo = obtenerEnteroValido(sc)){
                case 1:
                    estado = "Encendido";
                    break;
                case 2:
                    estado = "Apagado";
                    break;
                default:
                    System.out.println("Por favor ingresa una opción válida...");
                    break;
            }
        }
        selecciontipo = 0;
        //Agregar código aleatorio acorde al tipo de producto
        while(!(selecciontipo >= 1 && selecciontipo <= 2)){
            System.out.println("¿De qué tipo de producto se trata?");
            System.out.println(tipoElectronico());
            switch(selecciontipo = obtenerEnteroValido(sc)){
                case 1:
                    //Se agrega un número aleatorio para el ID y se verificará que no se repita con el ID de algún otro producto
                    //Será de 5 dígitos si es un teléfono
                    do {
                        valid = true;
                        codigo = rand.nextInt(90000) + 10000;
                        for (Electronico electronico : electronicos) {
                            if (electronico.getCodigo() == codigo) {
                                valid = false;
                                break;
                            }
                        }
                    } while (!valid);
                    valid = true;
                    System.out.println("¿Cuál es el modelo del teléfono?");
                    String modelo = sc.nextLine();
                    //Se crea la fila para un teléfono
                    sb.append(codigo).append(",").append(estado).append(",").append(" ").append(",").append(modelo);
                    break;
                case 2:
                    //Se agrega un número aleatorio para el ID y se verificará que no se repita con el ID de algún otro producto
                    //Será de 6 dígitos si es una computadora
                    do {
                        valid = true;
                        codigo = rand.nextInt(900000) + 100000;
                        for (Electronico electronico : electronicos) {
                            if (electronico.getCodigo() == codigo) {
                                valid = false;
                                break;
                            }
                        }
                    } while (!valid);
                    valid = true;
                    System.out.println("¿Cuál es la marca de la computadora?");
                    String marca = sc.nextLine();
                    //Se crea la fila para una computadora
                    sb.append(codigo).append(",").append(estado).append(",").append(marca).append(",").append("");
                    break;

                default:
                    //Mensaje a mostrar si no se elige algo adecuado
                    System.out.println("Seleccione un tipo válido.");
                    break;
            }  
        }
        //Se trata de escribir en el archivo
        try(BufferedWriter w = new BufferedWriter(new FileWriter(archivo, true))){
            w.write(sb.toString());
            w.newLine();
        } catch(IOException e){
            System.out.println("No se ha logrado agregar un electrónico.");
            e.printStackTrace();
        }
    }

    //Método para modificar el estado de un electronico
    public static void modificarEstado(String archivo, int codigo, String estado) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            String linea;
            StringBuilder contenido = new StringBuilder();
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                // Convierte el código a String para compararlo con el código
                String codigoString = String.valueOf(codigo);
                if (partes.length > 0 && partes[0].equals(codigoString)) {
                    // Modifica el valor en la segunda columna (Estado)
                    if (partes.length > 1) {
                        partes[1] = estado;
                    }
                }
                contenido.append(String.join(",", partes)).append("\n");
            }
            reader.close();
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
            //Se escribe sobre la celda adecuada
            writer.write(contenido.toString());
            writer.close();
            System.out.println("Modificación realizada con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    

    //Menú de tipo de dispositivo
    public String tipoElectronico(){
        return "1. Telefono\n" + "2. Computadora\n";
    }

    //Menú de estado
    public String definirEstado(){
        return "1. Encendido\n" + "2. Apagado\n";
    }

    //Método para asegurarse que se ingrese un entero en los campos necesarios
    public static int obtenerEnteroValido(Scanner scanner) {
        int numero = 0;
        boolean entradaValida = false;
        System.out.println("------------------------");
        do {
            try {
                System.out.print("Por favor, ingresa un número entero: ");
                String entrada = scanner.nextLine();
                numero = Integer.parseInt(entrada);
                entradaValida = true;
                System.out.println("");
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Debes ingresar un número entero.");
            }
        } while (!entradaValida);

        return numero;
    }

}
