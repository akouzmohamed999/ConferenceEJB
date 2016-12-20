package conference.presentation;

import java.util.ArrayList;
import java.util.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

import conference.gestion.GestionProgramme;
import conferenceJPA.Programme;

public class ListerProgrammeServlet {
	protected ArrayList<Programme> programmes = new ArrayList<Programme>();;

	GestionProgramme gp;

	@Init
	public void init() {
		try {
			gp = (GestionProgramme) new InitialContext()
					.lookup("java:global/conference/conferenceEJB/GestionProgramme");
			programmes.addAll(gp.tousLesProgrammes());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;

	}

	@NotifyChange("programmes")
	public void actualiser() {
		programmes.clear();
		programmes.addAll(gp.tousLesProgrammes());
	}

	@NotifyChange("programmes")
	@Command
	public void afficher() {
		Window window = (Window) Executions.createComponents("/widgets/ajouter_programme.zul", null, null);
		window.doModal();
		window.detach();
		actualiser();
	}

	public ArrayList<Programme> getProgrammes() {
		return programmes;
	}

	public void setProgrammes(ArrayList<Programme> programmes) {
		this.programmes = programmes;
	}

	public int nombrejour(Date d2, Date d1) {
		return (int) ( 1 + (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}
}
