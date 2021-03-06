package ch.gbssg.app.ila.export;

import java.io.InputStream;

import ch.gbssg.app.bla.pdfexport.PdfExportController;
import ch.gbssg.app.bla.wordexport.WordExportController;
import ch.gbssg.app.util.command.CmdDoExport;
import ch.gbssg.app.util.command.CmdDoExport.ExportType;
import ch.gbssg.app.util.command.CmdGetRootWindow;
import ch.gbssg.core.pac.AgentCommand;
import ch.gbssg.core.pac.AgentController;
import ch.gbssg.core.pac.AgentFactory;
import ch.gbssg.core.pac.ICommand;

/**
 * Represents the Controller for the Intermediate Level Agent Admin
 * This Agent handles all Exporting tasks, and validate the commands sended by the user
 * @author Michael Huber
 * @version 1.0
 */
public class ExportController extends AgentController {
	private WordExportController wordAgent;
	private PdfExportController pdfAgent;
	
	private ExportModel model;
	private ExportView view;
	/**
	 * Constructor of the Controller
	 */
	public ExportController() {
		// create agent hierarchy
		wordAgent = AgentFactory.getInstance().requestAgent(WordExportController.class);
		wordAgent.setParent(this);
		addChild(wordAgent);
		
		pdfAgent = AgentFactory.getInstance().requestAgent(PdfExportController.class);
		pdfAgent.setParent(this);
		addChild(pdfAgent);
		
	}
	/**
	 * Called when the agent is initializing
	 */
	@Override
	public boolean setupAgent() {
		model = new ExportModel();
		view = new ExportView(this);
		
		return true;
	}

	@Override
	protected void processMessage(AgentCommand messages) {
		// TODO Auto-generated method stub
		ICommand cmd = messages.peek();
		
		if (cmd instanceof CmdDoExport) {
			CmdDoExport export = validateExportCmd((CmdDoExport) cmd);
			
			if(export.isPromptUser()){
				getRootWindow();
				view.showDialog(model.getRootWindow(), export);
			}else{
				doExport(export);
			}
		}
		
	}
	/**
	 * Load the Template and send the data to the sub Agents based on the Doc Type for processing.
	 * @param export the Validated Export command
	 */
	public void doExport(CmdDoExport export){
		//load the Template
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream in = classLoader.getResourceAsStream("ch/gbssg/core/templates/"+export.getTemplateName());
		if(export.getDataModels().size()>0){
			if(export.getExportType()==ExportType.PDF){
				pdfAgent.generateVelocityPDF(export.getOutputFile(), export.getDataModels() ,in);
			}else if(export.getExportType()==ExportType.Word){			
				wordAgent.generateVelocityDocx(export.getOutputFile(),export.getDataModels() ,in);
			}else if(export.getExportType()==ExportType.Printer){
				//TODO Printer Bottom level Agent for printing
				
			}
		}
	}
	
	private void getRootWindow(){
		CmdGetRootWindow getRootWindow = new CmdGetRootWindow();
		sendAgentMessage(new AgentCommand(getRootWindow));
		
		model.setRootWindow(getRootWindow.getWindow());
	}
	
	private CmdDoExport validateExportCmd(CmdDoExport export){
		
		if(export.getExportType()==null){
			export.setPromptUser(true);
		}else{
			if(export.getExportType()==ExportType.Printer){
				
			}else{
				if(export.getOutputFile()==null){
					export.setPromptUser(true);
				}
			}
		}
		
		return export;
	}

}
