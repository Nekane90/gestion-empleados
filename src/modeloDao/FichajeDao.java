package modeloDao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import conexion.Conexion;

import modeloDto.FichajeDto;
/**
 * Clase DAO que gestiona las operaciones sobre la tabla
 * fichajes de la base de datos.
 * <p>
 * Implementa la interfaz {@link PatronDao} para el objeto
 * </p>
 * Permite insertar fichajes y actualizar la hora de salida
 * de un empleado para una fecha concreta.
 * @author Maialen y Nekane
 * @version 1.0
 */
public class FichajeDao implements PatronDao<FichajeDto>{
	/** Sentencia SQL para insertar un nuevo fichaje */
	private static final String SQL_INSERT="INSERT INTO fichajes(idEmpleado,fecha,horaEntrada,horaSalida,tipoTrabajo) VALUES (?,?,?,?,?)";
	/** 
     * Sentencia SQL para actualizar la hora de salida de un fichaje
     * que todavía no tiene hora de salida registrada
     */
	private static final String SQL_UPDATEHORASALIDA ="UPDATE fichajes SET horaSalida = ? WHERE idEmpleado = ? AND fecha = ? AND horaSalida = '00:00:00'";
	 /** Instancia de la conexión a la base de datos */
	//private Conexion con= Conexion.getInstancia();

	//////INSERTAR/////
	/**
     * Inserta un nuevo fichaje en la base de datos.
     *
     * @param fich objeto {@link FichajeDto} con los datos del fichaje
     * @return {@code true} si el fichaje se inserta correctamente,
     *         {@code false} en caso contrario
     */
	@Override
	public boolean insertar(FichajeDto fich) {
		PreparedStatement ps=null;
		try {
			ps=obtenerCon().prepareStatement(SQL_INSERT);
		
			ps.setInt(1, fich.getIdEmpleado());
			ps.setDate(2, fich.getFecha());
			ps.setTime(3, fich.getHoraEntrada());
			ps.setTime(4,fich.getHoraSalida());
			ps.setString(5, fich.getTipoTrabajo());
			

			
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
     * Actualiza la hora de salida de un empleado para una fecha concreta.
     * <p>
     * Solo se actualiza si la hora de salida actual es
     * <code>'00:00:00'</code>.
     * </p>
     *
     * @param idEmpleado id del empleado
     * @param fecha fecha del fichaje
     * @param horaSalida hora de salida 
     * @return {@code true} si la actualización se realiza correctamente,
     *         {@code false} en caso contrario
     */
	public boolean actualizarSalida(int idEmpleado, Date fecha,Time horaSalida) {
		PreparedStatement ps=null;
		try {
			ps=obtenerCon().prepareStatement(SQL_UPDATEHORASALIDA);
			ps.setTime(1, horaSalida);
	        ps.setInt(2, idEmpleado);
	        ps.setDate(3, fecha);
			
					
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

	@Override
	public boolean borrar(Object pk) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean actualizar(FichajeDto t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FichajeDto buscar(Object pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<FichajeDto> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//Metodo para obtener la conexion
	private Connection obtenerCon() {
	    return (Connection) Conexion.getInstancia().getCon();
	}
}
