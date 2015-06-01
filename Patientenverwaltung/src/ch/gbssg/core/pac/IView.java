package ch.gbssg.core.pac;

import javafx.scene.layout.Pane;
/**
 * Interface with basic methods for all Agent Views 
 * @author Michael Huber
 * @version 1.0
 */
public interface IView {
	/**
	 * Returns the Container which inherits all GUI Elements loaded from the FXML file
	 * @return Pane
	 */
	public Pane getContent();
	
}
