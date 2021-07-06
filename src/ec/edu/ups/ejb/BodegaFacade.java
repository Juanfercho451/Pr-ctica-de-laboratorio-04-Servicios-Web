package ec.edu.ups.ejb;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entidades.Bodega;

@Stateless
public class BodegaFacade extends AbstractFacade<Bodega> {

    @PersistenceContext(unitName = "Practica-de-laboratorio-03-EJB-JSF-y-JPA")
    private EntityManager em;

    public BodegaFacade() {
        super(Bodega.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
