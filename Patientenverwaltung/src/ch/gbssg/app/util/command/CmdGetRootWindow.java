package ch.gbssg.app.util.command;

import ch.gbssg.core.pac.ICommand;
import javafx.stage.Stage;

public class CmdGetRootWindow implements ICommand  {
	private Stage window;

	public CmdGetRootWindow() {

	}

	public Stage getWindow() {
		return window;
	}

	public void setWindow(Stage window) {
		this.window = window;
	}
}
