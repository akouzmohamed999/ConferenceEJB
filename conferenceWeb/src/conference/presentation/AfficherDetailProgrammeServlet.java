package conference.presentation;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;

import conference.gestion.GestionProgramme;
import conferenceJPA.Programme;
import conferenceJPA.Session;

public class AfficherDetailProgrammeServlet {
	GestionProgramme gp;
	Programme prog;

	@Init
	public void init() {
		try {
			gp = (GestionProgramme) new InitialContext()
					.lookup("java:global/conference/conferenceEJB/GestionProgramme");
			HttpServletRequest request = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
			String id_prog = request.getParameter("id");
			prog = gp.findprogramme(id_prog);
			System.out.println(prog.getSessions().size());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int duree(int nbrart, Session ses) {

		long diff = ses.getHeureFin().getTime() - ses.getHeureDeb().getTime();
		int mins = (int) (diff / (60 * 1000));
		int cren = (int) (mins / nbrart);
		return cren;
	}

	public Programme getProg() {
		return prog;
	}

	public void setProg(Programme prog) {
		this.prog = prog;
	}

}
