package modeloDao;

import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

import conexion.Conexion;
import modeloDto.EmpleadoDto;


public class EmpleadoDao  implements PatronDao<EmpleadoDto> {
	
	
	private static final String SQL_INSERT="INSERT INTO empleados(nombre,apellido,dni,salario,fechaAlta,idCategoria,contraseña) VALUES (?,?,?,?,?,?,?)";
	private static final String SQL_DELETE="DELETE FROM empleados WHERE idEmpleado = ?";
	private static final String SQL_UPDATE="UPDATE empleados SET nombre= ?, apellido= ?, dni = ?, salario = ?, fechaAlta = ?, idCategoria = ? WHERE idEmpleado = ?";
	private static final String SQL_FIND="SELECT * FROM empleados WHERE idEmpleado = ?";
	private static final String SQL_FINDALL="SELECT * FROM empleados";
	private static final String SQL_FINDEMPLEPORCATEGORIA="SELECT * FROM empleados WHERE idCategoria = ?";
	private static final String SQL_FIND2 = "SELECT nombre, contraseña, idCategoria FROM empleados WHERE idEmpleado = ?";
	
	//private Conexion con= Conexion.getInstancia();

	/**
	 * Inserta un nuevo registro de empleado en la base de datos.
	 * * Este método utiliza un PreparedStatement para ejecutar una sentencia SQL 
	 * de inserción, 
	 * * @param emp Objeto {@link EmpleadoDto} que contiene todos los datos del empleado a registrar.
	 * @return {@code true} si la inserción fue exitosa (se insertó al menos una fila); 
	 * {@code false} en caso de error o si no se pudo realizar la operación.
	 */
	
	//////INSERTAR/////
	public boolean insertar(EmpleadoDto emp) {
		
		PreparedStatement ps=null;
		try {
		
			ps = obtenerCon().prepareStatement(SQL_INSERT);
		
			ps.setString(1, emp.getNombreEmple());
			ps.setString(2, emp.getApellidoEmple());
			ps.setString(3, emp.getDni());
			ps.setDouble(4,emp.getSalario());
			ps.setDate(5, emp.getFechaAlta());
			ps.setInt(6,emp.getIdcategoria());
			ps.setString(7,emp.getContrasenia());

			
			if(ps.executeUpdate()>0) return true;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(ps!=null) {
					ps.close();
				}
			}catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return false;
		
	}

	/**
	 * Elimina un registro de empleado de la base de datos utilizando su clave primaria.
	 * * Este método ejecuta una sentencia SQL de borrado (DELETE) basada en el ID 
	 * proporcionado. Realiza un cast del objeto recibido a entero para aplicarlo 
	 * al filtro de la consulta.
	 * * @param pk Clave primaria (ID) del registro a eliminar. Se espera un objeto 
	 * de tipo {@code Integer}.
	 * @return {@code true} si el registro se eliminó correctamente (al menos una fila afectada); 
	 * {@code false} si el ID no existe o si ocurrió un error en la base de datos.
	 */
	
	////BORRAR/
	public boolean borrar(Object pk) {
		PreparedStatement ps=null;
		try {
			ps=obtenerCon().prepareStatement(SQL_DELETE);
			ps.setInt(1, (int)pk);
			int filas=ps.executeUpdate();
			if(filas>0) return true;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(ps!=null) {
					ps.close();
				}
			}catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return false;
		
	}

	/**
	 * Modifica un empleado de la base de datos
	 * Este método utiliza un PreparedStatement para ejecutar una sentencia SQL 
	 * @param emp Objeto {@link EmpleadoDto} que contiene todos los datos del empleado a registrar.
	 * @return {@code true} si la modificación fue exitosa
	 * {@code false} en caso de error o si no se pudo realizar la operación.
	 */
	
	/////////////MODIFICAR//////////////
	
	public boolean actualizar(EmpleadoDto emp) {
		PreparedStatement ps=null;
		try {
			ps=obtenerCon().prepareStatement(SQL_UPDATE);
			ps.setString(1,emp.getNombreEmple());
			ps.setString(2, emp.getApellidoEmple());
			ps.setString(3, emp.getDni());
			ps.setDouble(4, emp.getSalario());
			ps.setDate(5, emp.getFechaAlta());
			ps.setInt(6, emp.getIdcategoria());
			ps.setInt(7,emp.getIdEmpleado());
					
			if(ps.executeUpdate()>0) return true;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(ps!=null) {
					ps.close();
				}
			}catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * Recupera la información detallada de un empleado desde la base de datos 
	 * utilizando su identificador único (ID).
	 * * El método ejecuta una consulta SQL y mapea el resultado (ResultSet) a un 
	 * objeto de tipo {@link EmpleadoDto}.
	 * * @param pk Clave primaria del empleado que se desea buscar. Se espera un 
	 * objeto de tipo {@code Integer}.
	 * @return Un objeto {@link EmpleadoDto} con los datos del empleado si se 
	 * encuentra en la base de datos; {@code null} si no existe ningún 
	 * registro con esa clave o si ocurre una excepción.
	 */
	
	//////////BUSCAR//////////////////
	
	public EmpleadoDto buscar(Object pk) {
		EmpleadoDto empe = null;
		try {
			PreparedStatement ps=obtenerCon().prepareStatement(SQL_FIND);
			ps.setInt(1, (int)pk);
			
			ResultSet rs = ps.executeQuery();
			if ( rs.next() == true) {
				empe = new EmpleadoDto(rs.getInt("idEmpleado"), rs.getString("nombre"), rs.getString("apellido"),rs.getString("dni"),rs.getDouble("salario"),
						rs.getDate("fechaAlta"),rs.getInt("idCategoria"), rs.getString("contraseña"));
			}
			rs.close();
		}catch (SQLException e) {
			e.printStackTrace();
			
		}
		return empe;
	}

	/**
	 * Obtiene la lista completa de empleados registrados en la base de datos.
	 * * Este método ejecuta una consulta global (SELECT ALL) y recorre el {@link ResultSet} 
	 * para transformar cada fila en un objeto {@link EmpleadoDto}, almacenándolos 
	 * en un {@link ArrayList}.
	 * * @return Un {@link ArrayList} que contiene todos los objetos {@link EmpleadoDto} 
	 * encontrados. Si no hay registros en la tabla, se devolverá una lista vacía 
	 * (pero no {@code null}).
	 */
	
	@Override
	public ArrayList<EmpleadoDto> listarTodos() {
		ArrayList<EmpleadoDto> listaEmp = new ArrayList<EmpleadoDto>();
		try {
			PreparedStatement ps = obtenerCon().prepareStatement(SQL_FINDALL);
			ResultSet rs = ps.executeQuery();
			while ( rs.next()) {
				EmpleadoDto empe = new EmpleadoDto(rs.getInt("idEmpleado"), rs.getString("nombre"), rs.getString("apellido"),rs.getString("dni"),rs.getDouble("salario"),
						rs.getDate("fechaAlta"),rs.getInt("idCategoria"),rs.getString("contraseña"));
				listaEmp.add(empe);
				
			}
			rs.close();
			
		} catch (Exception e) {
			
		}
		return listaEmp;
	}

	
	/**
	 * Recupera un listado de empleados pertenecientes a una categoría específica.
	 * Realiza una consulta filtrada en la base de datos utilizando el identificador 
	 * de la categoría. Cada registro encontrado se transforma en un objeto {@link EmpleadoDto} 
	 * y se añade a la colección resultante.
	 * * @param idCategoria Identificador numérico de la categoría por la cual se desea filtrar.
	 * @return Un {@link ArrayList} con los empleados de dicha categoría. Si no existen 
	 * empleados asociados o el ID no es válido, devuelve una lista vacía.
	 */
	
	/////LISTAR EMPLEADOS POR CATEGORIA -/////
	
	public ArrayList<EmpleadoDto> listarEmpleadosPorCategoria(int idCategoria) {
		ArrayList<EmpleadoDto> listaEmp= new ArrayList<EmpleadoDto>();
		PreparedStatement ps=null;
		ResultSet rs = null;
		try {
			ps=obtenerCon().prepareStatement(SQL_FINDEMPLEPORCATEGORIA);
			ps.setInt(1, idCategoria);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				listaEmp.add(new EmpleadoDto(rs.getInt("idEmpleado"), rs.getString("nombre"), rs.getString("apellido"),rs.getString("dni"),rs.getDouble("salario"),
						rs.getDate("fechaAlta"),rs.getInt("idCategoria"),rs.getString("contraseña")));
				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return listaEmp;
	}
	
	
	/**
	 * Realiza la autenticación de un empleado en el sistema verificando sus credenciales.
	 * El proceso busca al empleado por su ID, recupera la contraseña encriptada almacenada 
	 * en la base de datos y la compara con el hash de la contraseña introducida por el usuario.
	 * * @param idEmpleado Código identificador único del empleado.
	 * @param passwordIntroducida Contraseña en texto plano introducida en el formulario de login.
	 * @return Un objeto {@link EmpleadoDto} con la información del perfil si la autenticación es exitosa; 
	 * {@code null} si el usuario no existe o la contraseña es incorrecta.
	 */
	
	
	////Validar el login

	public EmpleadoDto validarLogin(int idEmpleado, String passwordIntroducida) {
	    
	    PreparedStatement ps=null;
	    try {
	    	ps=obtenerCon().prepareStatement(SQL_FIND);
	        ps.setInt(1, idEmpleado);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            String passGuardada = rs.getString("contraseña"); 
	            
	            // Encriptamos la que acaba de escribir para comparar
	            String passEncriptadaAhora = encriptarLocal(passwordIntroducida);
	            if (passEncriptadaAhora.equals(passGuardada)) {
	                // Si coinciden, creamos el objeto con sus datos y rol
	                return new EmpleadoDto(
	                    rs.getString("nombre"),
	                    rs.getString("apellido"),
	                    rs.getString("dni"),
	                    rs.getDouble("salario"),
	                    rs.getDate("fechaAlta"),
	                    rs.getInt("idCategoria"),
	                    rs.getString("contraseña")
	                    
	                );
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null; 
	}
	/**
	 * Este metodo sirve para encriptar la contraseña y guardarla en la base de datos
	 * @param recibe la contraseña
	 * @return devuelve la contraseña encriptada
	 */
	
	///Metodo para encriptar la contraseña
		public String encriptarLocal(String password) {
		    try {
		        
		        MessageDigest digest = MessageDigest.getInstance("SHA-256");
		        byte[] hash = digest.digest(password.getBytes("UTF-8"));
		        
		        return Base64.getEncoder().encodeToString(hash);
		    } catch (Exception ex) {
		        throw new RuntimeException(ex);
		    }
		}
		//Metodo para obtener la conexion
		
		private Connection obtenerCon() {
		    return (Connection) Conexion.getInstancia().getCon();
		}

}
