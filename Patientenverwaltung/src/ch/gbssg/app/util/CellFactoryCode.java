package ch.gbssg.app.util;

import ch.gbssg.app.model.Code;
import javafx.scene.control.ListCell;
/**
 * Defines the Factory for the Codes.
 * This is used for all cases a Code model is used in an table or Combobox and the Desciption is needed to display
 * @author Michael Huber
 * @version 1.0
 */
public class CellFactoryCode extends ListCell<Code> {
	/*
	 * (non-Javadoc)
	 * @see javafx.scene.control.Cell#updateItem(java.lang.Object, boolean)
	 */
	@Override
	 protected void updateItem(Code item, boolean empty) {
        super.updateItem(item, empty);

        if (item == null || empty) {
            setGraphic(null);
        } else {
            setText(item.getDescription());
        }
        
        
    }
	
}
