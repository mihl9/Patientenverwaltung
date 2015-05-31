package ch.gbssg.app.bla.pdfexport;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import ch.gbssg.core.pac.AgentCommand;
import ch.gbssg.core.pac.AgentController;
import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;

/**
 * Represents the Controller for the Bottom Level Agent PDFExport
 * This agent is for exporting/Merging the model into the Template and saving it into a pdf file
 * @author Michael Huber
 * @version 1.0
 */
public class PdfExportController extends AgentController {

	@Override
	public boolean setupAgent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void processMessage(AgentCommand messages) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Generate a PDF File on the given location with the given model and template
	 * @param outputFile The File object where it should be saved
	 * @param models the data Models as a map
	 * @param template the Template which should be used
	 */
	public void generateVelocityPDF(File outputFile, Map<String, Object> models, InputStream template){
		try {
			//read this site
			//https://code.google.com/p/xdocreport/wiki/ODTReportingJavaMain
			//https://code.google.com/p/xdocreport/wiki/DocxReportingJavaMainNotes
			//load the docx file, load template engine and cache it to the registry
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(template,TemplateEngineKind.Velocity);
			
			//Create context for the model
			IContext context = report.createContext();
			context.putMap(models);
			
			//generate report by merging the model with the template
			OutputStream out = new FileOutputStream(outputFile);
			//report.process(context, out);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XDocReportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
