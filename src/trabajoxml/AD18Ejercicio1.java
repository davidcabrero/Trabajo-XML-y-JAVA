/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoxml;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author anaibislopezvara
 */
public class AD18Ejercicio1 {

    public static void main(String[] args) throws IOException {
        
    int n = 0; //Numero de veces que aparece una localidad   
    Document arbolXML;
    Node empleado, dato, datoValor;
    NodeList datosEmpleado;
   
    try {
            //Crear objeto DocumentBuilderFactory
            DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
            //Se eliminan los comentarios del XML en el DocumentBuilderFactory
            factory.setIgnoringComments(true);           
            //Se eliminan los espacios en blanco del XML en el DocumentBuilderFactory
            factory.setIgnoringElementContentWhitespace(true);           
            // se crea el objetyo builder donde se cargará el árbol delXML
            DocumentBuilder builder = factory.newDocumentBuilder();     
            File fichero = new File("empleados.xml");  
            arbolXML = builder.parse(fichero);           
            System.out.println("Árbol creado");
            System.out.println();
            
            //Imprimir el documento XML
            
            //Obtener el puntero al primer hijo que en este punto es la raíz del árbol
            Node raiz = arbolXML.getFirstChild();
            
            if (raiz != null) { //Si el árbol no está vacío
                
            Scanner sc = new Scanner(System.in); //Se pide el nombre de la localidad
            System.out.println("Introduce el nombre de una localidad, para ver la cantidad de empleados");
            String localidad = sc.nextLine();
                
                System.out.println("Archivo XML"); //Imprimir titulo y línea en blanco
                System.out.println();
                
                //Obtener la lista de todos los nodos hijos del nodo actual (el que apunta puntero)
                 NodeList empleados = raiz.getChildNodes();

                 // animales almacena punteros a todos los nodos hijos del nodo actual
                 for (int i=0; i<empleados.getLength(); i++) {
                     
                     empleado=empleados.item(i); //En empleado se almacena el valor del hijo de la posición i
                     
                     if (empleado.getNodeType()==Node.ELEMENT_NODE) {
                         
                         // Almaceno en datosEmpleados los hijos del segundo nivel
                         datosEmpleado=empleado.getChildNodes();
                         for (int j=0; j<datosEmpleado.getLength(); j++) {
                            
                            //Recupero un dato del empleado
                            dato=datosEmpleado.item(j);
                            if (dato.getNodeType()==Node.ELEMENT_NODE){
                                
                                // Imprimo el nombre del nodo
                                 System.out.print(dato.getNodeName()+": ");
                            
                                 datoValor=dato.getFirstChild();
                                 //Recupero el valor del primer hijo del nodo  y lo imprimo en caso de ser el valor marcado por #text                   
                                 if (datoValor!=null && datoValor.getNodeType()==Node.TEXT_NODE)  {  
                                      
                                     System.out.println(datoValor.getNodeValue());
                                     if (datoValor.getNodeValue().equals(localidad.toUpperCase())) { //Si coincide la localidad
                                         n++; //Se suma 1
                                     }
                                 }else
                                     System.out.println("Nulo");
                            }
                         }
                    System.out.println("-------------------------------");
                     }
                 }
                 System.out.println("En "+localidad+" hay "+n+" empleados");
            }
            

    } catch (ParserConfigurationException | SAXException ex) {
           Logger.getLogger(AD18Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
       }       
    }
    
}
