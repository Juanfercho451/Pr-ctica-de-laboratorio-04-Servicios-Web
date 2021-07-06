package ec.edu.ups.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entidades.FacturaCabecera;
import ec.edu.ups.entidades.PedidoCabecera;

@Stateless
public class PedidoCabeceraFacade extends AbstractFacade<PedidoCabecera> {
	@PersistenceContext(unitName = "Practica-de-laboratorio-03-EJB-JSF-y-JPA")
	private EntityManager em;

	public PedidoCabeceraFacade() {
		super(PedidoCabecera.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public List<PedidoCabecera> pedidosCabeceraReves() {
		List<PedidoCabecera> cabeceras = new ArrayList<PedidoCabecera>();
		String consulta = "Select pC From PedidoCabecera pC order by pc.id desc";
		try {
			cabeceras = em.createQuery(consulta).getResultList();
		} catch (Exception e) {
			System.out.println(" (PersonaFacade:buscarPorCedula: )" + e.getMessage());
		}
		return cabeceras;
	}

	public List<PedidoCabecera> pedidosCabeceraFiltrada(String cedula) {
		List<PedidoCabecera> cabeceras = new ArrayList<PedidoCabecera>();
		String consulta = "Select pc From PedidoCabecera pc where pc.persona.cedula='" + cedula
				+ "' order by pc.id desc";
		try {
			cabeceras = em.createQuery(consulta).getResultList();
		} catch (Exception e) {
			System.out.println(">>>Warning (FacturaCabeceraFacade:facturasCabeceraFiltrada: )" + e.getMessage());
		}
		return cabeceras;
	}

	public List<PedidoCabecera> buscarPorClienteCedula(String cedula) {
		try {
			String jpql = "SELECT pc FROM PedidoCabecera pc WHERE pc.persona.cedula='" + cedula + "'";
			List<PedidoCabecera> pedidoCabecera = em.createQuery(jpql).getResultList();
			return pedidoCabecera;
		} catch (Exception e) {
			return null;
		}

	}
}
