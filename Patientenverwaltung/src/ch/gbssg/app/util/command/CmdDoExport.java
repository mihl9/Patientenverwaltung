package ch.gbssg.app.util.command;

import java.io.File;

import ch.gbssg.core.AbsModel;
import ch.gbssg.core.pac.ICommand;

public class CmdDoExport implements ICommand  {
	public enum ExportType{
		PDF,
		Word,
		Printer
	}
	
	private ExportType exportType;
	private AbsModel dataModel;
	private String templateName;
	private File outputFile;
	private boolean promptUser;

	public CmdDoExport(ExportType exportType, AbsModel model, String template) {
		this.exportType = exportType;
		this.dataModel = model;
		this.templateName = template;
	}

	public CmdDoExport(AbsModel model, String template) {
		this(null,model,template);
		this.setPromptUser(true);
	}
	
	public ExportType getExportType() {
		return exportType;
	}

	public void setExportType(ExportType exportType) {
		this.exportType = exportType;
	}

	public AbsModel getDataModel() {
		return dataModel;
	}

	public void setDataModel(AbsModel dataModel) {
		this.dataModel = dataModel;
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
