package meteorologia;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Servidor extends UnicastRemoteObject implements TerrazaInterfaz {

    protected Servidor() throws RemoteException {
        super();
    }

    @Override
    public String obtenerInstrucciones(String rutaArchivo) throws RemoteException {
        StringBuilder instrucciones = new StringBuilder();

        try {
            URL url = new URL(rutaArchivo);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(url.openStream()));
            instrucciones.append("BIENVENIDO A LA METEOROLOGIA DE CAFETERÍA GAUPASA\n");
            instrucciones.append("\n");
            instrucciones.append("TAREAS DEL CAMARERO:\n");
            instrucciones.append("\n");

            // Variables para almacenar los datos
            double temperatura = 0;
            int lluvia = 0;
            int viento = 0;
            String estado = "";

            
            String linea;
            while ((linea = buffer.readLine()) != null) {//Lee línea por línea
                linea = linea.trim().toLowerCase();
                /*Detecta cada campo con `startsWith("estado:")`, `startsWith("temperatura:")`, etc.
 				Extrae solo números** con `.replaceAll("[^0-9,]", "")` para temperatura y lluvia
                Convierte coma a punto** para temperatura: `10,8` → `10.8`*/
                
                // Extraer ESTADO
                if (linea.startsWith("estado:")) {
                    estado = linea.replace("estado:", "").trim();
                }

                // Extraer TEMPERATURA
                if (linea.startsWith("temperatura:")) {
                    String tempStr = linea.replaceAll("[^0-9,]", "").replace(",", ".");
                    if (!tempStr.isEmpty()) {
                        temperatura = Double.parseDouble(tempStr);
                    }
                }

                // Extraer LLUVIA
                if (linea.startsWith("lluvia:")) {
                    String lluviaStr = linea.replaceAll("[^0-9]", "");
                    if (!lluviaStr.isEmpty()) {
                        lluvia = Integer.parseInt(lluviaStr);
                    }
                }

                // Extraer VIENTO
                if (linea.startsWith("viento:")) {
                    String vientoStr = linea.replaceAll("[^0-9]", "");
                    if (!vientoStr.isEmpty()) {
                        viento = Integer.parseInt(vientoStr);
                    }
                }
            }
            buffer.close();

           

            //TOLDOS
            if (estado.equals("soleado") && lluvia == 0 && viento <= 30) {
                instrucciones.append("- Abrir toldos\n");
            } else if (lluvia > 0 || viento > 30) {
                instrucciones.append("- Cerrar toldos\n");
            }

            // ESTUFAS
            if (temperatura < 15) {
                instrucciones.append("- Encender estufas\n");
            } else if (temperatura >= 22) {
                instrucciones.append("- Apagar estufas\n");
            }

            // ASPERSORES
            if (temperatura > 28 && estado.equals("soleado") && lluvia == 0) {
                instrucciones.append("- Encender aspersores de agua\n");
            } else if (lluvia > 0 || temperatura <= 25) {
                instrucciones.append("- Apagar aspersores de agua\n");
            }

            // ORTAVIENTOS
            if (viento > 20) {
                instrucciones.append("- Cerrar cortavientos\n");
            } else if (viento < 10) {
                instrucciones.append("- Abrir cortavientos\n");
            }

            // MESAS EXTERIORES
            if (lluvia > 0) {
                instrucciones.append("- Retirar mesas exteriores\n");
            }

            //SI NO HAY ACCIONES
            if (instrucciones.length() == 0) {
                instrucciones.append("- No se requieren acciones especiales\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
            instrucciones.append("Error al leer el archivo");
        }

        return instrucciones.toString();
    }

    // Main para arrancar el servidor
    public static void main(String[] args) {
        try {
            Servidor service = new Servidor();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("TerrazaInterfaz", service);
            System.out.println("Servidor RMI listo...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
