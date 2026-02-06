package chat;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class Servidor {

    static ArrayList<PrintWriter> escritores = new ArrayList<>();
    static ArrayList<String> nombres = new ArrayList<>();

    public static void main(String[] args) throws Exception {
    	int puerto = 5000;

        System.out.println("Servidor iniciado...");

        System.setProperty("javax.net.ssl.keyStore", "certificados/cafeteria");
		System.setProperty("javax.net.ssl.keyStorePassword","000000");

		SSLServerSocketFactory sfact=(SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
		SSLServerSocket servidorSSL=(SSLServerSocket) sfact.createServerSocket(puerto);
		SSLSocket clienteConectado=null;


		while (true) {
		    // Aceptamos la conexión segura
		    clienteConectado = (SSLSocket) servidorSSL.accept();

		    PrintWriter out = new PrintWriter(clienteConectado.getOutputStream(), true);
		    final SSLSocket socketFinal = clienteConectado; // Para usarlo dentro del Thread

		    new Thread(() -> {
		        String miNombre = null;
		        try {
		            // Leemos del socket seguro
		            BufferedReader in = new BufferedReader(new InputStreamReader(socketFinal.getInputStream()));

		            miNombre = in.readLine();
		            if (miNombre != null) {
		                synchronized (escritores) { escritores.add(out); }
		                synchronized (nombres) { nombres.add(miNombre); }
		                actualizarLista();
		            }

		            String msg;
		            while ((msg = in.readLine()) != null) {
		                for (PrintWriter w : escritores) {
		                    w.println(miNombre + ": " + msg);
		                }
		            }
		        } catch (Exception e) {
		            System.out.println("Error: " + e.getMessage());
		        } finally {
		        	if (miNombre != null) {
                    	synchronized (escritores) {
                    	    escritores.remove(out);
                    	}
                    	synchronized (nombres) {
                    	    nombres.remove(miNombre);
                    	}
                    	actualizarLista();
                        System.out.println(miNombre + " se ha desconectado.");
                        actualizarLista();
                    }
		        }
		    }).start();
		}
    }

    static void actualizarLista() {
        String fullList = "LISTA_USUARIOS:" + String.join(",", nombres);

        // Sincronizamos el acceso a la lista de escritores
        synchronized (escritores) {
            for (PrintWriter w : escritores) {
                w.println(fullList);
            }
        }
    }



    //metodo para pasar los nombres de los que estan conectados
    public static void enviarListaATodos() {
    	String protocolo = "LISTA_USUARIOS:";
        for (int i = 0; i < nombres.size(); i++) {
            protocolo += nombres.get(i) + (i == nombres.size() - 1 ? "" : ",");
        }
        for (PrintWriter m : escritores) {
            m.println(protocolo);
        }
    }
}

