package conference.presentation;

import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.Messagebox;


import conference.gestion.GestionAuteur;
import conferenceJPA.Auteur;

public class AjouterAuteurServlet {
	protected boolean visible;
	GestionAuteur ga;
	Auteur aut;

	@Init
	public void init() {
		try {
			visible = true;
			aut = new Auteur();
			aut.setEmail("");
			aut.setNom("");
			aut.setPrenom("");
			ga = (GestionAuteur) new InitialContext().lookup("java:global/conference/conferenceEJB/GestionAuteur");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;

	}

	public boolean check() {
		if (aut.getNom().length() > 2 && aut.getPrenom().length() > 2 && aut.getEmail().length() > 2) {
			return true;
		}
		return false;
	}

	@NotifyChange("visible")
	@Command
	public void ajouter() {
		if (!check()) {
			Messagebox.show("Veuillez vérifier vos entrées","Erreur", null, Messagebox.EXCLAMATION, null);
		} else {
			ArrayList<Auteur> auts = new ArrayList<Auteur>();
			auts.add(aut);
			ga.ajouterAuteurs(auts);
			visible = false;
		}
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Auteur getAut() {
		return aut;
	}

	public void setAut(Auteur aut) {
		this.aut = aut;
	}

}
