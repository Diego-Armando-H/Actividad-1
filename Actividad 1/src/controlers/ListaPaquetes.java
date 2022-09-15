/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import models.Paquete;

/**
 *
 * @author Armando
 */
public class ListaPaquetes implements Serializable{
    private final long serialVersionUID = 1L;
    private ArrayList<Paquete> paquetes = new ArrayList();
    
    public void insertarInicio( Paquete p){
        paquetes.add(0,p);
    }
    public void eliminarInicio( ){
        if ( paquetes.isEmpty() ){
            System.out.println("No hay paquetes para eliminar");
            return ;
        }
        paquetes.remove(0);
    }
    
    public void mostrar(){
        System.out.println(this.toString());
    }
    
    public void guardar( String ruta ) throws FileNotFoundException, IOException{
        
        try (FileOutputStream rutaSalida = new FileOutputStream(
                ruta +"/lista.txt"
        )) {
            rutaSalida.write( this.toString().getBytes() );
        }
        
    }
    
    public void recuperar( File archivo) throws IOException, ClassNotFoundException{
        try (FileInputStream rutaSalida = new FileInputStream(
                archivo
        )) {
            archivo.createNewFile();
            String cadenaFinal = "";
            int caracterPrevio;
            do{
                caracterPrevio = rutaSalida.read();
                if( caracterPrevio > 0){
                    char caracter = (char)caracterPrevio;
                    cadenaFinal += caracter;
                }
            } while( caracterPrevio > 0 );
            //Limpiamos todos los paquetes encontrados
            paquetes.removeAll(paquetes);
            Paquete paquete = null;
            for( int i = 0 ; i < cadenaFinal.length(); i++){
                //Saber si termino un nuevo objeto para no asignar nada
                if( cadenaFinal.charAt( i ) == '}' ) {
                    //Agregamos el paquete a la lista
                    paquetes.add(paquete);
                    paquete = null;
                }
                //Saber si empieza un nuevo objeto
                if( paquete == null ){
                    //Se inicio un nuevo objeto
                    if( cadenaFinal.charAt( i ) == '{' ) {
                        paquete = new Paquete();
                        i++;
                    }
                } else if( paquete != null){
                    String identificador ="";
                    String valorEncontrado =""; 
                    //Intentamos obtener los parametros
                    //Hasta que encontremos un "=" detenemos la obtencion del identificador
                    while( cadenaFinal.charAt( i ) != '='){
                        identificador += cadenaFinal.charAt( i ) == ' '? 
                                            "":cadenaFinal.charAt( i );
                        i++;
                        //Si llegamos al limite de la cadena cortamos el proceso
                        if( i >= cadenaFinal.length() ){
                            break;
                        }
                    }
                    i++;
                    //Intentamos obtener el valor
                    while( cadenaFinal.charAt( i ) != '\n'  ){
                        valorEncontrado += cadenaFinal.charAt( i ) == ','?
                                                "":cadenaFinal.charAt( i ); 
                        i++;
                        //Si llegamos al limite de la cadena cortamos el proceso
                        if( i >= cadenaFinal.length() ){
                            break;
                        }
                    }
                    //Asignación de las valores por identificador
                    if( "id".equals(identificador)){
                        paquete.setId(valorEncontrado);
                    }
                    if( "origen".equals(identificador)){
                        paquete.setOrigen(valorEncontrado);
                    }
                    if( "destino".equals(identificador)){
                        paquete.setDestino(valorEncontrado);
                    }
                    if( "peso".equals(identificador)){
                        paquete.setPeso(Double.parseDouble(valorEncontrado));
                    }
                    
                }
            };
        }
    }

    @Override
    public String toString() {
        String paquetesString = "";
        for (Paquete paquete : paquetes) {
            paquetesString += "\n\t" + paquete.toString() +"\n";
        }
        return paquetesString;
    }
   
    
    
}
