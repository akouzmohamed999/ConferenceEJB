package conferenceJPA;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * The persistent class for the session database table.
 * 
 */
@Entity
@NamedQuery(name = "Session.findAll", query = "SELECT s FROM Session s")
public class Session implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_sess")
	private int idSess;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "heure_deb")
	private Date heureDeb;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "heure_fin")
	private Date heureFin;

	@Temporal(TemporalType.DATE)
	private Date jour;

	// bi-directional many-to-one association to Article
	@OneToMany(mappedBy = "session", fetch = FetchType.EAGER)
	private Set<Article> articles = new TreeSet<Article>();
	// bi-directional many-to-one association to President
	
	@ManyToOne
	@JoinColumn(name = "id_pres")
	private President president;

	// bi-directional many-to-one association to Programme
	@ManyToOne
	@JoinColumn(name = "id_prog")
	private Programme programme;

	public Session() {
	}

	public int getIdSess() {
		return this.idSess;
	}

	public void setIdSess(int idSess) {
		this.idSess = idSess;
	}

	public Date getHeureDeb() {
		return this.heureDeb;
	}

	public void setHeureDeb(Date heureDeb) {
		this.heureDeb = heureDeb;
	}

	public Date getHeureFin() {
		return this.heureFin;
	}

	public void setHeureFin(Date heureFin) {
		this.heureFin = heureFin;
	}

	public Date getJour() {
		return this.jour;
	}

	public void setJour(Date jour) {
		this.jour = jour;
	}

	public Set<Article> getArticles() {
		return this.articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

	public Article addArticle(Article article) {
		getArticles().add(article);
		article.setSession(this);

		return article;
	}

	public Article removeArticle(Article article) {
		getArticles().remove(article);
		article.setSession(null);

		return article;
	}

	public President getPresident() {
		return this.president;
	}

	public void setPresident(President president) {
		this.president = president;
	}

	public Programme getProgramme() {
		return this.programme;
	}

	public void setProgramme(Programme programme) {
		this.programme = programme;
	}

}