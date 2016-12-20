package conference.gestion;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import conferenceJPA.Article;
import conferenceJPA.Auteur;

/**
 * Session Bean implementation class GestionArticle
 */
@Stateless
@LocalBean
public class GestionArticle {

	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public GestionArticle() {
        // TODO Auto-generated constructor stub
    }
    
    public void ajouterArticles(ArrayList<Article> articles){
    	for (Article article : articles) {
    		em.persist(article);
		}
    }
   // public void miseajoure(ArrayList<Article> articles){
    //	for (Article article : articles) {
    		//em.merge(article);
		//}
    //}
    
    public void associer(Article art, Auteur aut){
    	em.createNativeQuery("insert into auteur_article values(" + aut.getIdAut() + "," + art.getIdArt() +")").executeUpdate();
    }
    public Article getArticle(int id){
    	return em.find(Article.class, id);
    }
}
