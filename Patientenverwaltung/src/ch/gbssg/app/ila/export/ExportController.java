package ch.gbssg.app.ila.export;

import ch.gbssg.app.bla.pdfexport.PdfExportController;
import ch.gbssg.app.bla.wordexport.WordExportController;
import ch.gbssg.core.pac.AgentCommand;
import ch.gbssg.core.pac.AgentController;
import ch.gbssg.core.pac.AgentFactory;

/**
 * TODO 
 * @author pedrett.sandro
 *
 */
public class ExportController extends AgentController {

	public ExportController() {
		// create agent hierarchy
		WordExportController wordAgent = AgentFactory.getInstance().requestAgent(WordExportController.class);
		wordAgent.setParent(this);
		addChild(wordAgent);
		
		PdfExportController pdfAgent = AgentFactory.getInstance().requestAgent(PdfExportController.class);
		pdfAgent.setParent(this);
		addChild(pdfAgent);
	}
	
	@Override
	public boolean setupAgent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void processMessage(AgentCommand messages) {
		// TODO Auto-generated method stub
	}

}
