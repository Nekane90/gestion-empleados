package modeloDao;

import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import javax.swing.JOptionPane;

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
	
	private Conexion con= Conexion.getInstancia();

	//////INSERTAR/////
	public boolean insertar(EmpleadoDto emp) {
		
		PreparedStatement ps=null;
		try {
			ps=con.getCon().prepareStatement(SQL_INSERT);
		
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

	
	////BORRAR/
	public boolean borrar(Object pk) {
		PreparedStatement ps=null;
		try {
			ps=con.getCon().prepareStatement(SQL_DELETE);
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


	
	/////////////MODIFICAR//////////////
	
	public boolean actualizar(EmpleadoDto emp) {
		PreparedStatement ps=null;
		try {
			ps=con.getCon().prepareStatement(SQL_UPDATE);
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

	//////////BUSCAR//////////////////
	
	public EmpleadoDto buscar(Object pk) {
		EmpleadoDto empe = null;
		try {
			PreparedStatement ps=con.getCon().prepareStatement(SQL_FIND);
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

	@Override
	public ArrayList<EmpleadoDto> listarTodos() {
		ArrayList<EmpleadoDto> listaEmp = new ArrayList<EmpleadoDto>();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FINDALL);
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

	
	/////LISTAR EMPLEADOS POR CATEGORIA -/////
	
	public ArrayList<EmpleadoDto> listarEmpleadosPorCategoria(int idCategoria) {
		ArrayList<EmpleadoDto> listaEmp= new ArrayList<EmpleadoDto>();
		PreparedStatement ps=null;
		ResultSet rs = null;
		try {
			ps=con.getCon().prepareStatement(SQL_FINDEMPLEPORCATEGORIA);
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
	
	////Validar el login

	public EmpleadoDto validarLogin(int idEmpleado, String passwordIntroducida) {
	    
	    PreparedStatement ps=null;
	    try {
	    	ps=con.getCon().prepareStatement(SQL_FIND);
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
	
	

}
