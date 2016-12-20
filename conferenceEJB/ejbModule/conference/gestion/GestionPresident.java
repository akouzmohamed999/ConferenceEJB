package conference.gestion;

import java.util.Collection;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import conferenceJPA.President;

/**
 * Session Bean implementation class GestionPresident
 */
@Stateless
@LocalBean
public class GestionPresident {

	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public GestionPresident() {
        // TODO Auto-generated constructor stub
    }

    public void ajouterPresident(President president){
    	em.persist(president);
    }
    
    public void supprimerPresident(President president){
    	em.remove(president);
    }
    
    public Collection<President> tousLesPresidents(){
    	return (Collection<President>) em.createQuery("SELECT p from President p").getResultList();
    }
}
