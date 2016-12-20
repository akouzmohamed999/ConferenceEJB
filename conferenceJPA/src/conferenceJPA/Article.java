package conferenceJPA;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/**
 * The persistent class for the article database table.
 * 
 */
@Entity
@NamedQuery(name="Article.findAll", query="SELECT a FROM Article a")
public class Article implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_art")
	private int idArt;

	@Lob
	private String titre;

	//bi-directional many-to-one association to Session
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name="id_sess")
	private Session session;

	//bi-directional many-to-many association to Auteur
	@ManyToMany(mappedBy="articles", fetch=FetchType.EAGER, cascade = {CascadeType.MERGE})
	private Set<Auteur> auteurs = new TreeSet<Auteur>();
	public Article() {
	}

	public int getIdArt() {
		return this.idArt;
	}

	public void setIdArt(int idArt) {
		this.idArt = idArt;
	}

	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Session getSession() {
		return this.session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Set<Auteur> getAuteurs() {
		return this.auteurs;
	}

	public void setAuteurs(Set<Auteur> auteurs) {
		this.auteurs = auteurs;
	}

}