package ch.gbssg.app.util.command;

import ch.gbssg.core.pac.ICommand;

public class CmdDoExport<T> implements ICommand  {
	public enum ExportType{
		PDF,
		Word,
		Printer
	}
	
	private ExportType exportType;
	private T dataModel;
	private String templateName;
	private String outputPath;
	
	public CmdDoExport(ExportType exportType, T model, String template) {
		this.exportType = exportType;
		this.dataModel = model;
		this.templateName = template;
	}

	public ExportType getExportType() {
		return exportType;
	}

	public void setExportType(ExportType exportType) {
		this.exportType = exportType;
	}

	public T getDataModel() {
		return dataModel;
	}

	public void setDataModel(T dataModel) {
		this.dataModel = dataModel;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getOutputPath() {
		return outputPath;
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	

}
