package meteorologia;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {
	public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            TerrazaInterfaz service = (TerrazaInterfaz) registry.lookup("TerrazaInterfaz");

            // URL del archivo de previsión
            String rutaArchivo =
            		"https://drive.google.com/uc?export=download&id=1R-XDcmZhXN1SNKUOtjR3Wc2Df9f-FsMZ";


            // Llamada remota
            String instrucciones = service.obtenerInstrucciones(rutaArchivo);

            // Mostrar en pantalla
            PantallaMeoterologia pantalla = new PantallaMeoterologia();
            //pantalla.mostrarInstrucciones(instrucciones);
            pantalla.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
