package ec.edu.ups.entidades;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: PedidoCabecera
 *
 */
@Entity

public class PedidoCabecera implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@JsonbDateFormat(value = "dd-MM-YYYY hh:mm:ss")
	private Date fecha;
	private float subtotal;
	private float total;
	private float iva;
	private String estado;

	@JsonbProperty(nillable = true)
	private String estadoActual;
	@JsonbTransient
	private String estadoSiguiente;

	@Transient
	@JsonbTransient
	private Map<String, Integer> productos;

	@Transient
	@JsonbProperty(nillable = true)
	private String cedula;

	@ManyToOne
	@JoinColumn
	@JsonbTransient
	private Persona persona;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidoCabecera")
	private List<PedidoDetalle> pedidosDetale;

	@Override
	public String toString() {
		return "PedidoCabecera [id=" + id + ", fecha=" + fecha + ", subtotal=" + subtotal + ", total=" + total
				+ ", iva=" + iva + ", estado=" + estado + ", pedidosDetale=" + pedidosDetale
				+ "]";
	}
	
	public PedidoCabecera(int id, String estadoActual, String estadoSiguiente   ) {
		this.setId(id);
		this.setEstadoActual(estadoActual);
		this.setEstadoSiguiente(estadoSiguiente);
		
	}

	public PedidoCabecera() {
     fecha= Date.from(Instant.now());
	}

	public PedidoCabecera(int id, Date fecha, float subtotal, float total, float iva, String estado , Persona persona) {
		this.setId(id);
		this.setFecha(fecha);
		this.setSubtotal(subtotal);
		this.setTotal(total);
		this.setSubtotal(subtotal);
		this.setTotal(total);
		this.setIva(iva);
		this.setEstado(estado);
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public float getIva() {
		return iva;
	}

	public void setIva(float iva) {
		this.iva = iva;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}



	public List<PedidoDetalle> getPedidosDetale() {
		return pedidosDetale;
	}

	public void setPedidosDetale(List<PedidoDetalle> pedidosDetale) {
		this.pedidosDetale = pedidosDetale;
	}

	public void addPedidosDetale(PedidoDetalle pedidosDetale) {
		this.pedidosDetale.add(pedidosDetale);
	}

	public String getEstadoActual() {
		return estadoActual;
	}

	public void setEstadoActual(String estadoActual) {
		this.estadoActual = estadoActual;
	}

	public String getEstadoSiguiente() {
		return estadoSiguiente;
	}

	public void setEstadoSiguiente(String estadoSiguiente) {
		this.estadoSiguiente = estadoSiguiente;
	}

	public Map<String, Integer> getProductos() {
		return productos;
	}

	public void setProductos(Map<String, Integer> productos) {
		this.productos = productos;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	

}
