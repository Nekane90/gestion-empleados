package chat;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Servidor {
    
    static ArrayList<PrintWriter> escritores = new ArrayList<>();
    static ArrayList<String> nombres = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        ServerSocket servidor = new ServerSocket(5000);
        System.out.println("Servidor iniciado...");

        while (true) {
            Socket s = servidor.accept();
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            
            new Thread(() -> { 
                String miNombre = null; 
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

                    miNombre = in.readLine(); 
                    if (miNombre != null) {
                    	synchronized (escritores) {
                    	    escritores.add(out);
                    	}
                    	synchronized (nombres) {
                    	    nombres.add(miNombre);
                    	}
                    	actualizarLista();
                        //nombres.add(miNombre);
                        
                        //escritores.add(out); 
                        //actualizarLista();
                    }

                    String msg;
                    while ((msg = in.readLine()) != null) {
                        for (PrintWriter w : escritores) {
                            w.println(miNombre + ": " + msg);
                        }
                    }
                } catch (Exception e) {

                } finally {

                    if (miNombre != null) {
                    	synchronized (escritores) {
                    	    escritores.remove(out);
                    	}
                    	synchronized (nombres) {
                    	    nombres.remove(miNombre);
                    	}
                    	actualizarLista();
                        //nombres.remove(miNombre);
                        //escritores.remove(out);
                        System.out.println(miNombre + " se ha desconectado.");
                        actualizarLista(); // Avisamos a los que quedan para que borren el nombre
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
	    


