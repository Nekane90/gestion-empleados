package meteorologia;

import java.io.*;
import java.net.*;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

public class Servidor extends UnicastRemoteObject implements TerrazaInterfaz {

    protected Servidor() throws RemoteException {
        super();
    }

    @Override

    public String obtenerInstrucciones(String rutaArchivo) throws RemoteException {

        StringBuilder instrucciones = new StringBuilder();

        // Variables meteorológicas
        String fecha = "";
        String lugar = "";
        String estado = "";
        double temperatura = 0;
        int lluvia = 0;
        double viento = 0;

        try {
            
            //Leer archivo desde la URL
            URL url = new URL(rutaArchivo);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

            String linea;

            while ((linea = br.readLine()) != null) {
                linea = linea.trim().toLowerCase();

                // Comprobamos cada dato por separado
                if (linea.startsWith("fecha:")) {
                    fecha = linea.substring(6).trim(); //solo coge la fecha, sin la palabra 'fecha'

                } else if (linea.startsWith("lugar:")) {
                    lugar = linea.substring(6).trim();

                } else if (linea.startsWith("estado:")) {
                    estado = linea.substring(7).trim();

                } else if (linea.startsWith("temperatura:")) {
                    temperatura = obtenerDouble(linea);

                } else if (linea.startsWith("lluvia:")) {
                    lluvia = obtenerEntero(linea);

                } else if (linea.startsWith("viento:")) {
                    viento = obtenerDouble(linea);
                }
            }

            br.close();

            // INFORME QUE SE MANDA AL JDIALOG
            instrucciones.append("INFORME METEOROLÓGICO\n");
            instrucciones.append("FECHA:       ").append(fecha).append("\n");
            instrucciones.append("LUGAR:       ").append(lugar).append("\n");
            instrucciones.append("ESTADO:      ").append(estado.toUpperCase()).append("\n");
            instrucciones.append("Temperatura: ").append(temperatura).append(" °C\n");
            instrucciones.append("Lluvia:      ").append(lluvia).append(" mm\n");
            instrucciones.append("Viento:      ").append(viento).append(" km/h\n\n");

           

            // casos de tiempo
            if (estado.equals("soleado")) {

                // Toldos
                if (lluvia == 0 && viento <= 30) {
                    instrucciones.append("- Abrir toldos\n");
                }

                // Estufas
                if (temperatura < 15) {
                    instrucciones.append("- Encender estufas\n");
                } else if (temperatura >= 22) {
                    instrucciones.append("- Apagar estufas\n");
                }

                // Aspersores
                if (temperatura > 28) {
                    instrucciones.append("- Encender aspersores de agua\n");
                }

                // Cortavientos
                if (viento > 20) {
                    instrucciones.append("- Cerrar cortavientos\n");
                } else if (viento < 10) {
                    instrucciones.append("- Abrir cortavientos\n");
                }
            }else if (estado.equals("nublado")) {

                if (lluvia == 0 && viento <= 20) {
                    instrucciones.append("- Abrir parcialmente toldos\n");
                }

                if (temperatura < 15) {
                    instrucciones.append("- Encender estufas\n");
                }

                if (temperatura > 28) {
                    instrucciones.append("- Encender aspersores de agua\n");
                }

                if (viento > 20) {
                    instrucciones.append("- Cerrar cortavientos\n");
                } else if (viento < 10) {
                    instrucciones.append("- Abrir cortavientos\n");
                }
            }else if (estado.equals("lluvioso")) {

                instrucciones.append("- Cerrar toldos\n");
                instrucciones.append("- Retirar mesas exteriores\n");
                instrucciones.append("- Apagar aspersores de agua\n");

                if (temperatura < 15) {
                    instrucciones.append("- Encender estufas\n");
                }
            }else if (estado.equals("tormenta")) {

                instrucciones.append("- Cerrar toldos\n");
                instrucciones.append("- Retirar mesas exteriores\n");
                instrucciones.append("- Apagar aspersores de agua\n");
                instrucciones.append("- Cerrar cortavientos\n");

                if (temperatura < 15) {
                    instrucciones.append("- Encender estufas\n");
                }
            }else if (estado.equals("nevado")) {

                instrucciones.append("- Cerrar toldos\n");
                instrucciones.append("- Retirar mesas exteriores\n");
                instrucciones.append("- Cerrar cortavientos\n");

                if (temperatura < 15) {
                    instrucciones.append("- Encender estufas\n");
                }
            } else {
                instrucciones.append("- No se requieren acciones especiales\n");
            }


        } catch (Exception e) {
            e.printStackTrace();
            return "Error al leer el archivo meteorológico";
        }

        return instrucciones.toString();
    }
    
    private double obtenerDouble(String texto) {
        if (texto == null || texto.isEmpty()) return 0;
        // Quitar todo lo que no sea numero, punto o coma
        String valor = texto.replaceAll("[^0-9,.-]", "");
        // Reemplazar la coma por punto 
        valor = valor.replace(",", ".");
        // Si queda vacío devuelve 0
        if (valor.isEmpty()) return 0;
        // Convertir a double
        return Double.parseDouble(valor);
    }

    
    private int obtenerEntero(String texto) {
        if (texto == null || texto.isEmpty()) return 0;
        // Quitar todo lo que no sea numero
        String valor = texto.replaceAll("[^0-9]", "");
        // Si queda vacío devuelve 0
        if (valor.isEmpty()) return 0;
        // Convertir a entero
        return Integer.parseInt(valor);
    }


    // Main para arrancar el servidor
    public static void main(String[] args) {
        try {
            
            System.setProperty("java.rmi.server.hostname",InetAddress.getLocalHost().getHostAddress()); // InetAddress.getLocalHost().getHostAddress() obtiene la IP local de la máquina
            Servidor service = new Servidor();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("TerrazaInterfaz", service);
            System.out.println("Servidor RMI listo...");
        } catch (Exception e) {
            // Si ocurre cualquier error (IP, registro, servicio) se imprime la traza
            e.printStackTrace();
        }
    
    }

}

