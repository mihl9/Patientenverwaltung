package ch.gbssg.app.ila.export;

import java.io.InputStream;

import ch.gbssg.app.bla.pdfexport.PdfExportController;
import ch.gbssg.app.bla.wordexport.WordExportController;
import ch.gbssg.app.util.command.CmdDoExport;
import ch.gbssg.app.util.command.CmdDoExport.ExportType;
import ch.gbssg.core.AbsModel;
import ch.gbssg.core.pac.AgentCommand;
import ch.gbssg.core.pac.AgentController;
import ch.gbssg.core.pac.AgentFactory;
import ch.gbssg.core.pac.ICommand;

/**
 * TODO 
 * @author pedrett.sandro
 *
 */
public class ExportController extends AgentController {
	private WordExportController wordAgent;
	private PdfExportController pdfAgent;
	
	public ExportController() {
		// create agent hierarchy
		wordAgent = AgentFactory.getInstance().requestAgent(WordExportController.class);
		wordAgent.setParent(this);
		addChild(wordAgent);
		
		pdfAgent = AgentFactory.getInstance().requestAgent(PdfExportController.class);
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
		ICommand cmd = messages.peek();
		
		if (cmd instanceof CmdDoExport<?>) {
			CmdDoExport<?> export = (CmdDoExport<?>) cmd;
			if(export.getExportType()==ExportType.PDF){
				
			}else if(export.getExportType()==ExportType.Word){
				/*test*/
				ClassLoader classLoader = getClass().getClassLoader();
				InputStream in = classLoader.getResourceAsStream("ch/gbssg/core/templates/"+export.getTemplateName());
				wordAgent.generateVelocityDocx("C:\temp\test.docx",(AbsModel)export.getDataModel() ,in);
			}else if(export.getExportType()==ExportType.Printer){
				
			}
		}
		
	}

}
