package ch.gbssg.app.util.command;

import ch.gbssg.core.pac.ICommand;
import javafx.stage.Stage;
/**
 * Command for requesting the root Window of this application
 * @author Michael Huber
 * @version 1.0
 */
public class CmdGetRootWindow implements ICommand  {
	private Stage window;

	public Stage getWindow() {
		return window;
	}

	public void setWindow(Stage window) {
		this.window = window;
	}
}
