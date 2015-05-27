package ch.gbssg.app.ila.export;

import ch.gbssg.app.util.command.CmdDoExport;
import javafx.stage.Window;
/**
 * 
 * @author Michael Huber
 *
 */
public class ExportModel {
	private Window rootWindow;
	private CmdDoExport currExport;
	
	public CmdDoExport getCurrExport() {
		return currExport;
	}

	public void setCurrExport(CmdDoExport currExport) {
		this.currExport = currExport;
	}

	public Window getRootWindow() {
		return rootWindow;
	}

	public void setRootWindow(Window rootWindow) {
		this.rootWindow = rootWindow;
	}
}
