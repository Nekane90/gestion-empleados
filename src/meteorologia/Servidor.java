package meteorologia;

import java.io.BufferedReader;
import java.io.InputStream;
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
    public String obtenerInstrucciones(String urlPrevision) throws RemoteException {
        StringBuilder instrucciones = new StringBuilder();
        try {
        	InputStream url =getClass().getResourceAsStream("/meteorologia/" + urlPrevision);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(url));
            String linea;
            while ((linea = buffer.readLine()) != null) {
                linea = linea.toLowerCase();

                if (linea.contains("nublado")) {
                    instrucciones.append("- Mantener toldos cerrados\n");
                }
                if (linea.contains("lluvia") && !linea.contains("0 mm")) {
                    instrucciones.append("- Cerrar toldos\n");
                }
                if (linea.contains("viento")) {
                    instrucciones.append("- Cerrar cortavientos\n");
                }
            }
            buffer.close();
        } catch (Exception e) {
            e.printStackTrace();
            instrucciones.append("Error al leer la previsión: ").append(e.getMessage());
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
