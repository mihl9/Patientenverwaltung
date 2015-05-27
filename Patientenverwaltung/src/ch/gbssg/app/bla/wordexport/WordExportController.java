package ch.gbssg.app.bla.wordexport;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import ch.gbssg.core.pac.AgentCommand;
import ch.gbssg.core.pac.AgentController;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;

/**
 * TODO
 * @author pedrett.sandro
 *
 */
public class WordExportController extends AgentController {

	@Override
	public boolean setupAgent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void processMessage(AgentCommand messages) {
		// TODO Auto-generated method stub
		
	}
	
	public void generateVelocityDocx(File outputFile, Map<String, Object> models, InputStream template){
		try {
			//read this site
			//https://code.google.com/p/xdocreport/wiki/ODTReportingJavaMain
			//https://code.google.com/p/xdocreport/wiki/DocxReportingJavaMainNotes
			//load the docx file, load template engine and cahce it to the registry
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(template,TemplateEngineKind.Velocity);
			
			//Create context for the model
			IContext context = report.createContext();
			context.putMap(models);
			
			//generate report by merging the model with the tempalte
			OutputStream out = new FileOutputStream(outputFile);
			report.process(context, out);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XDocReportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
