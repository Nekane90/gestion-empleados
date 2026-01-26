package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

	private static Conexion instancia = null;
	private static Connection con;
	public static String ipServer;

	/**
	 * Este método realiza las siguientes tareas:
	 * Configura los parámetros de red (host), credenciales (usuario y contraseña)
	 * y el nombre de la base de datos ({@code cafeteria gaupasa}).
	 * Carga dinámicamente el driver JDBC de MySQL mediante {@link Class#forName(String)}.
	 * Construye la URL de conexión y establece la comunicación a través de {@link DriverManager}.
	 * Nota: El constructor es privado para dar soporte al patrón de diseño Singleton
	 * asegurando que solo exista una instancia de la conexión en toda la aplicación.
	 */


	private Conexion () {
		 String host = ipServer; //127.0.0.1 aqui tengo puesta la ip de mi ordenador para que se conecten sin abrir el xamp
		 String user = "compañeros"; // root
		 String pass = "";
		 String dtbs = "cafeteria gaupasa";
		 try{
			 Class.forName("com.mysql.jdbc.Driver"); //Inicializar el driver
			 String newConnectionURL = "jdbc:mysql://" + host + "/" + dtbs + "?" + "user=" + user + "&password=" + pass;
			 con= DriverManager.getConnection(newConnectionURL);
		 }catch (Exception e) {
			 System.out.println("Error al abrir la conexión.");
			 e.printStackTrace();
		 }
	 }


	public static Conexion getInstancia(String ipRecibida){
	    // Si la instancia no existe, la creamos pasando la IP
	    if (instancia == null) {
	        ipServer = ipRecibida;
	        instancia = new Conexion();
	    }
	    return instancia;
	}

	public static Conexion getInstancia() {
		if (instancia == null) {
			instancia = new Conexion();
		}
	    return instancia;
	}
	/*public static Conexion getInstancia(){
		if (instancia == null) instancia = new Conexion();
			return instancia;
	 	}*/

	public Connection getCon (){
		return con;
	}

	public void cerrarConexion() {
		try {
			con.close();
		}catch (Exception e) {
			System.out.println("Error al cerrar la conexión.");
		}
	}

}
