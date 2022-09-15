
import controlers.Paqueteria;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import models.Paquete;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Armando
 */
public class main {
    
    private static final byte MENU_PRINCIPAL = 0;
    private static final byte MENU_AGREGAR = 1;
    private static final byte MENU_ELIMINAR = 2;
    private static final byte MENU_MOSTRAR = 3;
    private static final byte MENU_GUARDAR = 4;
    private static final byte MENU_RECUPERAR = 5;
    private static final byte SALIR = 6;
    
    private static String menu( byte opcion ){
        switch( opcion ){
            case MENU_PRINCIPAL:{
                return  "============\n" +
                        "Paquetería\n" +
                        "============\n" +
                        "1) Agregar\n" +
                        "2) Eliminar primero\n" +
                        "3) Mostrar paquetes\n" +
                        "4) Guardar \n" +
                        "5) Recuperar (guardar al inicio de la lista)\n-";
            }
            case MENU_AGREGAR:{
                return  "=================\n" +
                        "Agregar paquete\n" +
                        "=================\n-";
            }
            case MENU_ELIMINAR:{
                return  "===========================\n" +
                        "Eliminar primer paquete\n" +
                        "==========================\n-";
            }
            case MENU_MOSTRAR:{
                return "===========================\n" +
                        "Desplegando paquetes\n" +
                        "==========================\n-";
            }
            case MENU_GUARDAR:{
                return "===========================\n" +
                        "Recuperar paquetes\n" +
                        "==========================\n"+
                        "Escriba la ruta para guardar\n-";
            }
            case MENU_RECUPERAR:{
                return "===========================\n" +
                        "Recuperar paquetes\n" +
                        "==========================\n"+
                        "Escriba la ruta para recuperar\n-";
            }
            default :{
                return "";
            }
        }
    }
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //Opcion que seleccionará el usuario en el menú
        byte opcion ;
        //Escaner para leer las salidas en consola
        Scanner scanner ;
        //Paquete auxilar para cada nuevo registro de paquete
        Paquete paquete ;
        //Nueva lista para gestionar los paquetes
        Paqueteria listaPaquetes = new Paqueteria();
        do{
            scanner = new Scanner(System.in);
            opcion = MENU_PRINCIPAL;
            //Imprimimos el menu segun el parametro recibido
            System.out.println(menu(opcion));
            opcion = scanner.nextByte();
            System.out.println(menu(opcion));
            switch( opcion ){
                case MENU_AGREGAR:{
                    paquete = new Paquete();
                    System.out.println("Ingrese el ID del paquete");
                    String a = scanner.nextLine();
                    paquete.setId(scanner.nextLine());
                    System.out.println("Ingrese el origen del paquete");
                    paquete.setOrigen(scanner.nextLine());
                    System.out.println("Ingrese el destino del paquete");
                    paquete.setDestino(scanner.nextLine());
                    System.out.println("Ingrese el peso del paquete");
                    paquete.setPeso(scanner.nextDouble());
                    //Finalmente guardamos el paquete al inicio de la lista
                    listaPaquetes.insertarInicio(paquete);
                    System.out.println("Paquete generado");
                    break;
                }
                case MENU_ELIMINAR:{
                    listaPaquetes.eliminarInicio();
                    break;
                }
                case MENU_MOSTRAR:{
                    listaPaquetes.mostrar();
                    break;
                }
                case MENU_GUARDAR:{
                    System.out.println("Seleccionando ruta de guardado");
                    JFileChooser f = new JFileChooser();
                    f.setDialogType(JFileChooser.OPEN_DIALOG);
                    f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    f.showDialog(null, "Seleccione la ruta de guardado");
                    String ruta = f.getSelectedFile().getAbsolutePath();
//                    System.out.println("Nombre del archivo");
//                    scanner.next();
//                    String name = scanner.nextLine();
                    listaPaquetes.guardar(ruta);
                    System.out.println("Paquetes guardados");
                    break;
                }
                case MENU_RECUPERAR:{
                    System.out.println("Seleccionando ruta de recuperación");
                    JFileChooser f = new JFileChooser();
                    f.setDialogType(JFileChooser.OPEN_DIALOG);
                    f.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    f.showDialog(null, "Seleccione la ruta de guardado");
                    String ruta = f.getSelectedFile().getAbsolutePath();
                    listaPaquetes.recuperar(new File (ruta));
                    System.out.println("Paquetes recuperado");
                    break;
                }
                default :{
                    break;
                }
            }
        }while( opcion != SALIR );
    }
}
