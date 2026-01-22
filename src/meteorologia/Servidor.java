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
    public String obtenerInstrucciones(String urlPrevision) throws RemoteException {
        StringBuilder instrucciones = new StringBuilder();
        try {
            URL url = new URL(urlPrevision);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains("lluvia")) {
                    instrucciones.append("- Cerrar toldos\n");
                    instrucciones.append("- Apagar estufas\n");
                }
                if (linea.contains("sol")) {
                    instrucciones.append("- Abrir toldos\n");
                    instrucciones.append("- Encender estufas\n");
                }
                if (linea.contains("viento")) {
                    instrucciones.append("- Cerrar cortavientos\n");
                }
            }
            br.close();
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
            registry.rebind("TerrazaService", service);
            System.out.println("Servidor RMI listo...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
