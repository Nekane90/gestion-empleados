package modeloDao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import conexion.Conexion;

import modeloDto.FichajeDto;

public class FichajeDao implements PatronDao<FichajeDto>{
	private static final String SQL_INSERT="INSERT INTO fichajes(idEmpleado,fecha,horaEntrada,horaSalida,tipoTrabajo) VALUES (?,?,?,?,?)";
	private static final String SQL_UPDATEHORASALIDA ="UPDATE fichajes SET horaSalida = ? WHERE idEmpleado = ? AND fecha = ? AND horaSalida = '00:00:00'";
	private Conexion con= Conexion.getInstancia();

	//////INSERTAR/////
	
	@Override
	public boolean insertar(FichajeDto fich) {
		PreparedStatement ps=null;
		try {
			ps=con.getCon().prepareStatement(SQL_INSERT);
		
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
	public boolean actualizarSalida(int idEmpleado, Date fecha,Time horaSalida) {
		PreparedStatement ps=null;
		try {
			ps=con.getCon().prepareStatement(SQL_UPDATEHORASALIDA);
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
}
