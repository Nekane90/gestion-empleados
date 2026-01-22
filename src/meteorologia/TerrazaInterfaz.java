package meteorologia;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TerrazaInterfaz extends Remote {
    // Método remoto que recibe la URL del archivo de previsión y devuelve instrucciones
    String obtenerInstrucciones(String urlPrevision) throws RemoteException;
}
