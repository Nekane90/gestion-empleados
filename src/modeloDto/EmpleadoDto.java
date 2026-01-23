package modeloDto;

import java.sql.Date;
import java.util.Objects;
/**
 * DTO que representa a un empleado.
 *
 * Contiene información personal, laboral y de acceso del empleado.
 * @author Nekane y Maialen
 * @version 1.0
 */
public class EmpleadoDto {

	private int idEmpleado;
	private String nombre;
	private String apellido;
	private String dni;
	private double salario;
	private Date fechaAlta;
	private int idcategoria;
	private String contrasenia;

	//Constructor
	public EmpleadoDto(int idEmpleado, String nombre, String apellido, String dni, Double salario, Date fechaAlta,
			int idcategoria,String contrasenia) {
		super();
		this.idEmpleado = idEmpleado;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.salario = salario;
		this.fechaAlta = fechaAlta;
		this.idcategoria = idcategoria;
		this.contrasenia = contrasenia;
	}

	public EmpleadoDto( String nombre, String apellido, String dni, double salario, Date fechaAlta,int idCategoria,String contrasenia) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.salario = salario;
		this.fechaAlta = fechaAlta;
		this.idcategoria = idCategoria;
		this.contrasenia = contrasenia;

	}
	public EmpleadoDto(String nombre, int idCategoria) {
		super();
		this.nombre = nombre;
		this.idcategoria = idCategoria;
	}


	//Constructor vacio
	public EmpleadoDto() {
		super();
	}

	//Getters y setters



	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombreEmple() {
		return nombre;
	}

	public void setNombreEmple(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoEmple() {
		return apellido;
	}

	public void setApellidoEmple(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public int getIdcategoria() {
		return idcategoria;
	}

	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
	}

	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}


	//Hashcode y equals
	 /**
     * Genera el código hash del empleado.
     *
     * @return valor hash
     */
	@Override
	public int hashCode() {
		return Objects.hash(apellido, dni, fechaAlta, idEmpleado, idcategoria, nombre, salario);
	}
	/**
     * Compara dos objetos.
     *
     * @param obj objeto a comparar
     * @return {@code true} si son iguales, {@code false} en caso contrario
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		EmpleadoDto other = (EmpleadoDto) obj;
		return Objects.equals(apellido, other.apellido) && Objects.equals(dni, other.dni)
				&& Objects.equals(fechaAlta, other.fechaAlta) && idEmpleado == other.idEmpleado
				&& idcategoria == other.idcategoria && Objects.equals(nombre, other.nombre)
				&& Objects.equals(salario, other.salario);
	}

	
	
	public String toString() {
		return nombre +"-ID-"+idEmpleado;
	
	}

	





















}
