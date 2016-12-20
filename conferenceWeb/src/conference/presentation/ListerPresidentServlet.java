package conference.presentation;

import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.Window;
import org.zkoss.zk.ui.Executions;


import conference.gestion.GestionPresident;
import conferenceJPA.President;

public class ListerPresidentServlet {

	protected ArrayList<President> presidents = new ArrayList<President>();;
	
	GestionPresident gp;

	@Init
	public void init() {
		try {
			gp = (GestionPresident) new InitialContext()
					.lookup("java:global/conference/conferenceEJB/GestionPresident");
			presidents.addAll(gp.tousLesPresidents());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;

	}

	public ArrayList<President> getPresidents() {
		return presidents;
	}

	public void setPresidents(ArrayList<President> presidents) {
		this.presidents = presidents;
	}

	@NotifyChange("presidents")
	public void actualiser(){
		presidents.clear();
		presidents.addAll(gp.tousLesPresidents());
	}
	
	@NotifyChange("presidents")
	@Command
	public void afficher() {
		Window window = (Window) Executions.createComponents("/widgets/ajouter_president.zul", null, null);
		window.doModal();
		window.detach();
		actualiser();
	}
}
