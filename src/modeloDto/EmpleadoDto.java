package modeloDto;

import java.sql.Date;
import java.util.Objects;

public class EmpleadoDto {
	
	private int idEmpleado;
	private String nombre;
	private String apellido;
	private String dni;
	private double salario;
	private Date fechaAlta;
	private int idcategoria;
	
	//Constructor
	public EmpleadoDto(int idEmpleado, String nombre, String apellido, String dni, Double salario, Date fechaAlta,
			int idcategoria) {
		super();
		this.idEmpleado = idEmpleado;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.salario = salario;
		this.fechaAlta = fechaAlta;
		this.idcategoria = idcategoria;
	}
	
	public EmpleadoDto( String nombre, String apellido, String dni, double salario, Date fechaAlta,int idCategoria) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.salario = salario;
		this.fechaAlta = fechaAlta;
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

	
	//Hashcode y equals
	
	@Override
	public int hashCode() {
		return Objects.hash(apellido, dni, fechaAlta, idEmpleado, idcategoria, nombre, salario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpleadoDto other = (EmpleadoDto) obj;
		return Objects.equals(apellido, other.apellido) && Objects.equals(dni, other.dni)
				&& Objects.equals(fechaAlta, other.fechaAlta) && idEmpleado == other.idEmpleado
				&& idcategoria == other.idcategoria && Objects.equals(nombre, other.nombre)
				&& Objects.equals(salario, other.salario);
	}
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
