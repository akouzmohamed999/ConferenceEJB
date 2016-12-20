package conference.gestion;

import java.util.Collection;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import conferenceJPA.Programme;

/**
 * Session Bean implementation class GestionProgramme
 */
@Stateless
@LocalBean
public class GestionProgramme {

	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public GestionProgramme() {
        // TODO Auto-generated constructor stub
    }
    
    public void ajouterProgramme(Programme programme){
    	em.persist(programme);
    }
    public void supprimerProgramme(Programme programme){
    	em.persist(programme);
    }
    public Collection<Programme> tousLesProgrammes(){
    	return (Collection<Programme>) em.createQuery("SELECT p from Programme p").getResultList();
    }
    public Programme findprogramme(String id){
    	return em.find(Programme.class, Integer.parseInt(id));
    }
}
