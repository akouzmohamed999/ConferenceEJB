package conference.presentation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.zkoss.zkmax.zul.Chosenbox;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Window;

import conference.gestion.GestionArticle;
import conference.gestion.GestionAuteur;
import conference.gestion.GestionPresident;
import conference.gestion.GestionProgramme;
import conference.gestion.GestionSession;
import conferenceJPA.Article;
import conferenceJPA.Auteur;
import conferenceJPA.President;
import conferenceJPA.Programme;
import conferenceJPA.Session;

public class AjouterSessionServlet {

	GestionPresident gpre;
	GestionSession gses;
	GestionProgramme gpro;
	GestionAuteur gaut;
	GestionArticle gart;
	
	Chosenbox[] chosens = new Chosenbox[4];
	
	ArrayList<President> presidents = new ArrayList<President>();
	ArrayList<Programme> programmes = new ArrayList<Programme>();
	ArrayList<Article> articles = new ArrayList<Article>();
	ArrayList<Auteur> auteurs = new ArrayList<Auteur>();
	Programme prog;
	President pres;
	Date jour;
	Date temps_debut = new Date();
	Date temps_fin = new Date();
	int nbr_articles = 1;

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}
	
	@Init
	public void init() {
		try {
			gpre = (GestionPresident) new InitialContext()
					.lookup("java:global/conference/conferenceEJB/GestionPresident");
			gses = (GestionSession) new InitialContext().lookup("java:global/conference/conferenceEJB/GestionSession");
			gpro = (GestionProgramme) new InitialContext()
					.lookup("java:global/conference/conferenceEJB/GestionProgramme");
			gart = (GestionArticle) new InitialContext().lookup("java:global/conference/conferenceEJB/GestionArticle");
			gaut = (GestionAuteur) new InitialContext().lookup("java:global/conference/conferenceEJB/GestionAuteur");
			presidents.addAll(gpre.tousLesPresidents());
			programmes.addAll(gpro.tousLesProgrammes());
			auteurs.addAll(gaut.tousLesAuteurs());
			prog = programmes.get(0);
			pres = presidents.get(0);
			jour = prog.dates().get(0);
			articles.add(new Article());

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@NotifyChange("articles")
	@Command
	public void miseajoursaisiearticles(@ContextParam(ContextType.TRIGGER_EVENT) InputEvent change) {
		articles.clear();
		for (int i = 0; i < Integer.parseInt(change.getValue()); i++) {
			articles.add(new Article());
		}
	}

	@NotifyChange("jour")
	@Command
	public void miseajourdate() {
		jour = prog.dates().get(0);
		System.out.println(jour);
	}

	@Command
	public void nouvelauteur(@BindingParam("v") Chosenbox v,@BindingParam("i") int i){
		chosens[i] = v;
	}
	
	public boolean checktemps(){
		if(temps_debut.before(temps_fin)){
			return true;
		}
		return false;
	}
	@Command
	public void ajouter(){
		if(!checktemps()){
			Messagebox.show("Vérifiez l'heure de début !", null, null);
			return;
		}
		if(checkarticles() == 1){
			Messagebox.show("Vérifiez les titres des articles!", null, null);
			return;
		}
		if(checkarticles() == 2){
			Messagebox.show("Vérifiez les auteurs des articles!", null, null);
			return;
		}
		Session ses = new Session();
		ses.setPresident(pres);
		ses.setHeureDeb(temps_debut);
		ses.setHeureFin(temps_fin);
		ses.setJour(jour);
		ses.setPresident(pres);
		ses.setProgramme(prog);
		gses.ajouterSession(ses);
		for (int i = 0; i < articles.size();i++) {
			articles.get(i).setSession(ses);
		}
		gart.ajouterArticles(articles);
		
		for(int i =0 ; i < articles.size() ; i++){
			ArrayList<Auteur> lst = new ArrayList<Auteur>();
			
			for(Object o : chosens[i].getSelectedObjects()){
				Auteur aut = (Auteur) o;
				//associer
				gart.associer(articles.get(i), aut);
				
			}
			
		}
		Messagebox.show("La session a été créée avec succès", null, null);
	}
	private int checkarticles() {
		for(int i= 0; i< nbr_articles ; i++){
			if(articles.get(i).getTitre() == null){
				return 1;
			}
			if(articles.get(i).getTitre().length() < 2){
				return 1;
			}
			if(chosens[i].getSelectedObjects().size() ==0 ){
				return 2;
			}
		}
		return 0;
	}

	public ArrayList<President> getPresidents() {
		return presidents;
	}

	public void setPresidents(ArrayList<President> presidents) {
		this.presidents = presidents;
	}

	public ArrayList<Programme> getProgrammes() {
		return programmes;
	}

	public void setProgrammes(ArrayList<Programme> programmes) {
		this.programmes = programmes;
	}

	public ArrayList<Article> getArticles() {
		return articles;
	}

	public void setArticles(ArrayList<Article> articles) {
		this.articles = articles;
	}

	public Programme getProg() {
		return prog;
	}

	public void setProg(Programme prog) {
		this.prog = prog;
	}

	public President getPres() {
		return pres;
	}

	public void setPres(President pres) {
		this.pres = pres;
	}

	public Date getTemps_debut() {
		return temps_debut;
	}

	public void setTemps_debut(Date temps_debut) {
		this.temps_debut = temps_debut;
	}

	public Date getTemps_fin() {
		return temps_fin;
	}

	public void setTemps_fin(Date temps_fin) {
		this.temps_fin = temps_fin;
	}

	public int getNbr_articles() {
		return nbr_articles;
	}

	public void setNbr_articles(int nbr_articles) {
		this.nbr_articles = nbr_articles;
	}

	public Date getJour() {
		return jour;
	}

	public void setJour(Date jour) {
		this.jour = jour;
	}

	public ArrayList<Auteur> getAuteurs() {
		return auteurs;
	}

	public void setAuteurs(ArrayList<Auteur> auteurs) {
		this.auteurs = auteurs;
	}

}
