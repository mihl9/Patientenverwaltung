package ch.gbssg.app.util.command;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import ch.gbssg.core.AbsModel;
import ch.gbssg.core.pac.ICommand;

public class CmdDoExport implements ICommand  {
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

	public CmdDoExport(ExportType exportType, String template) {
		this.dataModel = new HashMap<String, Object>();
		this.exportType = exportType;
		this.templateName = template;
	}

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

	public boolean isPromptUser() {
		return promptUser;
	}

	public void setPromptUser(boolean promptUser) {
		this.promptUser = promptUser;
	}

}
