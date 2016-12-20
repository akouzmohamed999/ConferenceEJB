package conference.gestion;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import conferenceJPA.Session;

/**
 * Session Bean implementation class GestionSession
 */
@Stateless
@LocalBean
public class GestionSession {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	EntityManager em;
    public GestionSession() {
        // TODO Auto-generated constructor stub
    }

    public void ajouterSession(Session s){
    	em.persist(s);
    }
}
