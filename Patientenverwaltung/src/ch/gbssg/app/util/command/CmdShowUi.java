package ch.gbssg.app.util.command;

import ch.gbssg.core.pac.ICommand;
import javafx.scene.layout.Pane;

public class CmdShowUi implements ICommand  {
	private Pane pane;

	public CmdShowUi(Pane stage) {
		setPane(stage);
	}

	public Pane getPane() {
		return pane;
	}

	public void setPane(Pane pane) {
		this.pane = pane;
	}
}
