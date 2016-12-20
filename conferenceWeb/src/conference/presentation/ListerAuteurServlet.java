package conference.presentation;

import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

import conference.gestion.GestionAuteur;
import conferenceJPA.Auteur;

public class ListerAuteurServlet {

	ArrayList<Auteur> auteurs = new ArrayList<Auteur>();
	GestionAuteur ga;

	@Init
	public void init() {
		try {
			ga = (GestionAuteur) new InitialContext().lookup("java:global/conference/conferenceEJB/GestionAuteur");
			auteurs.addAll(ga.tousLesAuteurs());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;

	}

	@NotifyChange("auteurs")
	@Command
	public void afficher() {
		Window window = (Window) Executions.createComponents("/widgets/ajouter_auteur.zul", null, null);
		window.doModal();
		window.detach();
		actualiser();
	}

	@NotifyChange("auteurs")
	@Command
	public void supprimer(@BindingParam("auteur") Auteur auteur) {
		
		int confirmation = Messagebox.show("Êtes-vous sûr  ?", "confirmation", Messagebox.OK | Messagebox.CANCEL, Messagebox.INFORMATION, null);
		if(confirmation == Messagebox.OK){
			ga.supprimerAuteur(auteur.getIdAut()); //supprimer l'auteur de la bdd
			auteurs.remove(auteur);
			Messagebox.show("Auteur supprimé", "", Messagebox.OK, Messagebox.INFORMATION, null);
		}
		
	}

	@NotifyChange("auteurs")
	public void actualiser() {
		auteurs.clear();
		auteurs.addAll(ga.tousLesAuteurs());
	}

	public ArrayList<Auteur> getAuteurs() {
		return auteurs;
	}

	public void setAuteurs(ArrayList<Auteur> auteurs) {
		this.auteurs = auteurs;
	}

}
