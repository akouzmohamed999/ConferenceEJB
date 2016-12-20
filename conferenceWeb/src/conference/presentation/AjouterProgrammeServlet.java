package conference.presentation;

import java.util.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Messagebox;

import conference.gestion.GestionProgramme;
import conferenceJPA.Programme;

public class AjouterProgrammeServlet {

	@Wire("#datedebut")
	Datebox ddb;
	@Wire("#datefin")
	Datebox dfb;
	protected boolean visible;
	GestionProgramme gp;
	protected Programme prog = new Programme();

	@Init
	public void init() {
		try {
			visible = true;
			gp = (GestionProgramme) new InitialContext()
					.lookup("java:global/conference/conferenceEJB/GestionProgramme");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@NotifyChange("visible")
	@Command
	public void ajouter() {
		Date dd = prog.getDateDeb();
		Date df = prog.getDateFin();
		if(!dd.before(df)){
			Messagebox.show("Vérifier les dates !","Dates incorrectes",null,Messagebox.EXCLAMATION,null);
		}
		else{
			gp.ajouterProgramme(prog);
			visible = false;
		}
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Programme getProg() {
		return prog;
	}

	public void setProg(Programme prog) {
		this.prog = prog;
	}

}
