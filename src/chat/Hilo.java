package chat;

import java.io.*;
import java.net.*;

public class Hilo implements Runnable {
	
	private Socket socket;
    private BufferedReader entrada;
    private String nombre;

    public Hilo(Socket socket) {
        this.socket = socket;
    }

 
    public void run() {
        try {
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            nombre = entrada.readLine();
            //registrar usuario
         
            synchronized (Servidor.nombres) {
                Servidor.nombres.add(nombre);
            }
            System.out.println(nombre + " se ha unido.");
            
            //enviamos la lista actualizada
            Servidor.enviarListaATodos();

            String mensaje;
            // Este bucle escucha los mensajes de este cliente específico
            while ((mensaje = entrada.readLine()) != null) {
                System.out.println("Mensaje recibido: " + mensaje);
                
                // Reenviar el mensaje a TODOS los empleados conectados
                for (PrintWriter escritor : Servidor.escritores) {
                    escritor.println(mensaje);
                }
            }
        } catch (IOException e) {
            System.out.println("Un empleado se ha desconectado.");
        } finally {
        	//quitar de la lista y avisar a los demás
            if (nombre != null) {
                Servidor.nombres.remove(nombre);
                Servidor.enviarListaATodos();
            }
          
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

	
	    
