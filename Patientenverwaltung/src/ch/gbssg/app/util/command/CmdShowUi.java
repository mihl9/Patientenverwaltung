package ch.gbssg.app.util.command;

import ch.gbssg.core.pac.ICommand;
import javafx.scene.layout.Pane;
/**
 * General command to trigger an agent to show its content in an given pane
 * @author Michael Huber
 * @version 1.0
 */
public class CmdShowUi implements ICommand  {
	private Pane pane;

	public CmdShowUi(Pane pane) {
		setPane(pane);
	}

	public Pane getPane() {
		return pane;
	}

	public void setPane(Pane pane) {
		pane.getChildren().clear();
		this.pane = pane;
	}
}
