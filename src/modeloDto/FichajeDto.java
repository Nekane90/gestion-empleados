package modeloDto;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;
/**
 * DTO que representa un fichaje de un empleado.
 * <p>
 * Contiene la información relativa a la fecha, hora de entrada,
 * hora de salida y tipo de trabajo realizado.
 * </p>
 *
 * @author Nekane y Maialen
 * @version 1.0
 */
public class FichajeDto {


	private int idEmpleado;
	private Date fecha;
	private Time horaEntrada;
	private Time horaSalida;
	private String tipoTrabajo;

	///Constructores
	public FichajeDto( int idEmpleado, Date fecha, Time horaEntrada, Time horaSalida,
			String tipoTrabajo) {
		super();

		//Aqui validamos que solo se pueda poner presencial o teletrabajo /// equalsIgnoreCase no tiene en cuenta si se escribe mayusculas o minusculas, es como el equals
		if ("presencial".equalsIgnoreCase(tipoTrabajo) || "teletrabajo".equalsIgnoreCase(tipoTrabajo)) {
		    this.tipoTrabajo = tipoTrabajo;
		} else {
		    throw new IllegalArgumentException("Tipo de trabajo no válido: " + tipoTrabajo);
		}

		this.idEmpleado = idEmpleado;
		this.fecha = fecha;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;

	}

	public FichajeDto() {

	}




	public int getIdEmpleado() {
		return idEmpleado;
	}





	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public Time getHoraEntrada() {
		return horaEntrada;
	}


	public void setHoraEntrada(Time horaEntrada) {
		this.horaEntrada = horaEntrada;
	}


	public Time getHoraSalida() {
		return horaSalida;
	}


	public void setHoraSalida(Time horaSalida) {
		this.horaSalida = horaSalida;
	}


	public String getTipoTrabajo() {
		return tipoTrabajo;
	}


	public void setTipoTrabajo(String tipoTrabajo) {
		this.tipoTrabajo = tipoTrabajo;
	}





	///Hashcode y equals
	/**
     * Genera el código hash del fichaje.
     *
     * @return valor hash
     */
	@Override
	public int hashCode() {
		return Objects.hash(fecha, horaEntrada, horaSalida, idEmpleado, tipoTrabajo);
	}
	 /**
     * Compara dos objetos {@code FichajeDto}.
     *
     * @param obj objeto a comparar
     * @return {@code true} si ambos fichajes son iguales,
     *         {@code false} en caso contrario
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		FichajeDto other = (FichajeDto) obj;
		return Objects.equals(fecha, other.fecha) && Objects.equals(horaEntrada, other.horaEntrada)
				&& Objects.equals(horaSalida, other.horaSalida) && idEmpleado == other.idEmpleado;
	}


	 @Override
	 public String toString() {
		return "año" +fecha ;
	 }







}
