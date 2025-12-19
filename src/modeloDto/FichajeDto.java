package modeloDto;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

import javax.swing.JOptionPane;

public class FichajeDto {
	
	private int idFichaje;
	private int idEmpleado;
	private Date fecha;
	private Time horaEntrada;
	private Time horaSalida;
	private String tipoTrabajo;
	
	//Constructores
	
	public FichajeDto(int idFichaje, int idEmpleado, Date fecha, Time horaEntrada, Time horaSalida,
			String tipoTrabajo) {
		super();
		
		//Aqui validamos que solo se pueda poner presencial o teletrabajo /// equalsIgnoreCase no tiene en cuenta si se escribe mayusculas o minusculas, es como el equals
		if ("presencial".equalsIgnoreCase(tipoTrabajo) || "teletrabajo".equalsIgnoreCase(tipoTrabajo)) {
		    this.tipoTrabajo = tipoTrabajo;
		} else {
		    throw new IllegalArgumentException("Tipo de trabajo no válido: " + tipoTrabajo);
		}
		this.idFichaje = idFichaje;
		this.idEmpleado = idEmpleado;
		this.fecha = fecha;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
		
	}

	
	///Hashcode y equals
	///
	@Override
	public int hashCode() {
		return Objects.hash(fecha, horaEntrada, horaSalida, idEmpleado, idFichaje, tipoTrabajo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FichajeDto other = (FichajeDto) obj;
		return Objects.equals(fecha, other.fecha) && Objects.equals(horaEntrada, other.horaEntrada)
				&& Objects.equals(horaSalida, other.horaSalida) && idEmpleado == other.idEmpleado
				&& idFichaje == other.idFichaje && Objects.equals(tipoTrabajo, other.tipoTrabajo);
	}
	
	
	
	
	
	

}
