//Bryan Alberto Martínez Orellana
//Carnét 23542
//Ingeniería en Ciencias de la Computación
//Programación Orientada a Objetos
//Creación: 29/10/2023
//Última modificación: 29/10/2023

//Librerías a utilizar
import java.util.ArrayList;
import java.util.Scanner;

public class ElectroTech {
    private static ManejoCSV manejoCSV = new ManejoCSV();
    private static ArrayList<Electronico> electronicos = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static boolean systemON = true;
    private static boolean systemON2 = true;
    private static String seleccion;
    private static boolean valid = false;
    private static String estado = "";
    private static int selecciontipo = 0;
    public static void main(String[] args){
        //Se carga una vez los electrónicos
        electronicos = manejoCSV.leerCSV("Electronicos.csv");
        //Mensaje de bienvenida
        System.out.println("***********************************************************");
        System.out.println("*  __        _______ _     ____ ___  __  __ _____ _ _ _   *");
        System.out.println("*  \\ \\      / / ____| |   / ___/ _ \\|  \\/  | ____| | | |  *");
        System.out.println("*   \\ \\ /\\ / /|  _| | |  | |  | | | | |\\/| |  _| | | | |  *");
        System.out.println("*    \\ V  V / | |___| |__| |__| |_| | |  | | |___|_|_|_|  *");
        System.out.println("*     \\_/\\_/  |_____|_____\\____\\___/|_|  |_|_____(_|_|_)  *");
        System.out.println("***********************************************************");
        System.out.println("\nBuenos días querido usuario ;)\n" + "¿Qué deseas hacer el día de hoy?\n");

        while(systemON){
            System.out.println();
            System.out.println(menuPrincipal());
            switch(seleccion = sc.nextLine()){
                //Agregar nuevo electrónico
                case "1":
                    System.out.println();
                    manejoCSV.agregarElectronicos("Electronicos.csv", electronicos);
                    electronicos = manejoCSV.leerCSV("Electronicos.csv");
                    System.out.println();
                    break;
                //Ver y modificar estados
                case "2":
                    System.out.println();
                    systemON2 = true;
                    while(systemON2){
                        System.out.println(menuSecundario());
                        switch(seleccion = sc.nextLine()){
                            //Listar todos los dispositivos
                            case "1":
                                System.out.println();
                                for(Electronico e: electronicos){
                                    System.out.println(e);
                                }
                                System.out.println();
                                break;
                            
                            //Mostrar dispositivos encendidos
                            case "2":
                                System.out.println();
                                System.out.println("Estos son los electrónicos actualmente encendidos:");
                                for(Electronico e: electronicos){
                                    if(e.Validar().equals("Encendido")){
                                        System.out.println(e);
                                    }
                                }
                                System.out.println();
                                break;

                            //Mostrar dispositivos apagados
                            case "3":
                                System.out.println();
                                System.out.println("Estos son los dispositivos que están apagados: ");
                                for(Electronico e: electronicos){
                                    if(e.Validar().equals("Apagado")){
                                        System.out.println(e);
                                    }
                                }
                                System.out.println();
                                break;
                            
                            //Modificar estado de un dispositivo
                            case "4":
                                valid = false;
                                selecciontipo = 0;
                                System.out.println();
                                System.out.println("Por favor, ingrese el código del electrónico que quiera modificar su estado\n");
                                int codigo = manejoCSV.obtenerEnteroValido(sc);
                                //Verificamos que el código dado exista para poder cambiar el estado
                                for(Electronico e: electronicos){
                                    if(e.getCodigo() == codigo){
                                        valid = true;
                                        if(valid == true){
                                            while(!(selecciontipo >= 1 && selecciontipo <= 2)){
                                                System.out.println("¿En qué estado está el dispositivo que se está agregando?");
                                                System.out.println(manejoCSV.definirEstado());
                                                switch(selecciontipo = manejoCSV.obtenerEnteroValido(sc)){
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
                                            if(estado.equals("Encendido")){
                                                e.Encender();
                                            } else {
                                                e.Apagar();
                                            }
                                            manejoCSV.modificarEstado("Electronicos.csv", codigo, estado);
                                        }
                                    }
                                } 
                                //Mensaje a mostrar dependiendo de la acción llevada a cabo
                                if(valid == true){
                                    System.out.println("Se ha encontrado y modificado el estado de tu electrónico.");
                                } else{
                                    System.out.println("No se ha encontrado el código que proporcionaste...");
                                }  
                                System.out.println();
                                break;

                            //Conocer si un dispositivo está prendido o apagado
                            case "5":
                                valid = false;
                                System.out.println();
                                System.out.println("Por favor, ingrese el código del electrónico que quiera conocer su estado\n");
                                codigo = manejoCSV.obtenerEnteroValido(sc);
                                //Verificamos la existencia de un código dado
                                for(Electronico e: electronicos){
                                    if(e.getCodigo() == codigo){
                                        System.out.println(e);
                                        valid = true;
                                        System.out.println();
                                    }
                                }
                                //Mensaje a mostrar si no existe el código dado
                                if(!valid){
                                    System.out.println("No se ha encontrado tu dispositivo en la base de datos...");
                                    System.out.println();
                                }
                                break;   
                            //Regresar al menú principal
                            case "6":
                                System.out.println("Regresando al menú principal...");
                                systemON2 = false;
                                break;
                            //Mostrar si no se selecciona algo adecuado
                            default:
                                System.out.println("Selecciona algo válido.\n");
                                break;
                        }
                    }
                    break;
                
                //Mostrar mensaje de despedida y salir
                case "3":
                    System.out.println("Que tengas un lindo día, adiós :)");
                    systemON = false;
                    break;
                //Mostrar si no se selecciona algo adecuado
                default:
                    System.out.println("Selecciona algo válido\n");
                
            }
                
        }
    }
    //Despliegue de menú principal
    public static String menuPrincipal(){
        return "1. Agregar un nuevo electrónico\n" + "2. Manejar estados de dispositivos\n" + "3. Salir";
    }

    //Despliegue de mneú secundario, el que permitirá ver y modificar estados
    public static String menuSecundario(){
        return "1. Listar todos los electrónicos\n" + "2. Listar electrónicos encendidos\n" + "3. Listar electrónicos apagados\n" + "4. Modificar el estado de un dispositivo\n" + "5. Conocer el estado de un electrónico en especifico\n" + "6. Salir";
    }

}
