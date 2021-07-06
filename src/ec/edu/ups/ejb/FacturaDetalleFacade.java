package ec.edu.ups.ejb;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entidades.FacturaDetalle;

@Stateless
public class FacturaDetalleFacade extends AbstractFacade<FacturaDetalle> {

    @PersistenceContext(unitName = "Practica-de-laboratorio-03-EJB-JSF-y-JPA")
    private EntityManager em;

    public FacturaDetalleFacade() {
        super(FacturaDetalle.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}

