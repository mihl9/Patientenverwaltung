package ch.gbssg.app.util.command;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import ch.gbssg.core.AbsModel;
import ch.gbssg.core.pac.ICommand;
/**
 * Command for Exporting the given Models with the Template
 * @author Michael Huber
 * @version 1.0
 */
public class CmdDoExport implements ICommand  {
	/**
	 * All export Types which can be used
	 * @author Michael Huber
	 */
	public enum ExportType{
		PDF,
		Word,
		Printer
	}
	
	private ExportType exportType;
	private Map<String, Object> dataModel;
	private String templateName;
	private File outputFile;
	private boolean promptUser;
	/**
	 * Constructor of the Command
	 * Needs at least the Export type, and the path to the template
	 * @param exportType
	 * @param template the name of the Template Saved in /core/templates
	 */
	public CmdDoExport(ExportType exportType, String template) {
		this.dataModel = new HashMap<String, Object>();
		this.exportType = exportType;
		this.templateName = template;
	}
	/**
	 * Constructor
	 * @param template the name of the Template Saved in /core/templates
	 */
	public CmdDoExport(String template) {
		this(null,template);
		this.setPromptUser(true);
	}
	
	public ExportType getExportType() {
		return exportType;
	}

	public void setExportType(ExportType exportType) {
		this.exportType = exportType;
	}

	public Map<String, Object> getDataModels() {
		return dataModel;
	}
	/**
	 * Adds a data model to merge with the Document
	 * @param key the model name
	 * @param dataModel the data
	 */
	public void addDataModel(String key, AbsModel dataModel) {
		this.dataModel.put(key, dataModel);
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public File getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}
	/**
	 * set if the user should see a prompt message
	 * @return
	 */
	public boolean isPromptUser() {
		return promptUser;
	}

	public void setPromptUser(boolean promptUser) {
		this.promptUser = promptUser;
	}

}
