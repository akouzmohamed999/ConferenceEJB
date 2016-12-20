package conference.gestion;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import conferenceJPA.Auteur;
import conferenceJPA.President;

/**
 * Session Bean implementation class GestionAuteur
 */
@Stateless
@LocalBean
public class GestionAuteur {

	@PersistenceContext
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public GestionAuteur() {
		// TODO Auto-generated constructor stub
	}

	public void ajouterAuteurs(ArrayList<Auteur> auteurs) {
		for (Auteur auteur : auteurs) {
			em.persist(auteur);
		}
	}
	public void supprimerAuteur(int auteurid){
		Auteur asupprimer = em.find(Auteur.class, auteurid);
		em.remove(asupprimer);
	}
	public Collection<Auteur> tousLesAuteurs() {
		return (Collection<Auteur>) em.createQuery("SELECT a from Auteur a").getResultList();
	}
}
