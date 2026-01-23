package meteorologia;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {
	public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            TerrazaInterfaz service = (TerrazaInterfaz) registry.lookup("TerrazaInterfaz");

            // URL del archivo de previsión
            String urlPrevision = "Informe_Muskiz.txt";

            // Llamada remota
            String instrucciones = service.obtenerInstrucciones(urlPrevision);

            // Mostrar instrucciones
            System.out.println("Instrucciones para el camarero:\n" + instrucciones);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
