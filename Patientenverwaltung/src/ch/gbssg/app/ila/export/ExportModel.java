package ch.gbssg.app.ila.export;

import ch.gbssg.app.util.command.CmdDoExport;
import javafx.stage.Window;
/**
 * Represents the Model for the Intermediate Level agent Ex
 * In this class all necessary data is saved
 * @author Michael Huber
 * @version 1.0
 */
public class ExportModel {
	private Window rootWindow;
	private CmdDoExport currExport;
	/**
	 * get the Current Export Command
	 * @return
	 */
	public CmdDoExport getCurrExport() {
		return currExport;
	}
	/**
	 * set the Current Export Command
	 */
	public void setCurrExport(CmdDoExport currExport) {
		this.currExport = currExport;
	}
	/**
	 * Get the Current Parent Window
	 * @return
	 */
	public Window getRootWindow() {
		return rootWindow;
	}
	/**
	 * Set the Parent window of the Modal Window
	 * @param rootWindow
	 */
	public void setRootWindow(Window rootWindow) {
		this.rootWindow = rootWindow;
	}
}
