package conference.presentation;

import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.Messagebox;

import conference.gestion.GestionPresident;
import conferenceJPA.President;

public class AjouterPresidentServlet {
	protected boolean visible;
	protected ArrayList<String> depts = new ArrayList<String>();
	GestionPresident gp;
	protected President presid = new President();

	@Init
	public void init() {
		try {
			visible = true;
			depts.add("Informatique");
			depts.add("Physique");
			depts.add("Chimie");
			depts.add("Electronique");
			presid.setDepartement(depts.get(0));
			presid.setNom("");
			presid.setPrenom("");
			presid.setEmail("");
			gp = (GestionPresident) new InitialContext()
					.lookup("java:global/conference/conferenceEJB/GestionPresident");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean check() {
		if (presid.getNom().length() > 2 && presid.getPrenom().length() > 2 && presid.getEmail().length() > 10
				&& presid.getEmail().contains("@")) {
			return true;
		}
		return false;
	}

	@NotifyChange("visible")
	@Command
	public void ajouter() {
		if (!check()) {
			Messagebox.show("Vérifier vos entrées !", "Erreur", null, Messagebox.EXCLAMATION, null);
			return;
		}
		gp.ajouterPresident(presid);
		visible = false;

	}

	public President getPresid() {
		return presid;
	}

	public void setPresid(President presid) {
		this.presid = presid;
	}

	public ArrayList<String> getDepts() {
		return depts;
	}

	public void setDepts(ArrayList<String> depts) {
		this.depts = depts;
	}

	public boolean getVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
