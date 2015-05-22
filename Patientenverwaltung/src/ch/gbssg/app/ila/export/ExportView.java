package ch.gbssg.app.ila.export;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import ch.gbssg.app.util.command.CmdDoExport;
import ch.gbssg.app.util.command.CmdDoExport.ExportType;
import ch.gbssg.core.pac.IView;

public class ExportView implements IView, Initializable{
	
	private ExportController controller;
	private CmdDoExport export;
	
	private Stage window;
	private Scene form;
	
	public ExportView(ExportController controller){
		this.controller = controller;
		
		// get root content
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ExportSettings.fxml"));
		loader.setController(this);
		
		try {
			root = (AnchorPane)loader.load();
			
			// bind new pane to parent pane
			AnchorPane.setTopAnchor(root, 0.0);
			AnchorPane.setBottomAnchor(root, 0.0);
			AnchorPane.setLeftAnchor(root, 0.0);
			AnchorPane.setRightAnchor(root, 0.0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showDialog(Window owner, CmdDoExport export){
		Stage dialog = new Stage();

		dialog.setTitle("Exportieren");
		dialog.initOwner(owner);
		dialog.initModality(Modality.WINDOW_MODAL);
		//dialog.setX(owner.getX() + owner.getWidth());
	    //dialog.setY(owner.getY());
	    dialog.centerOnScreen();
	    if(form==null){
	    	form = new Scene(root);
	    	//form.setr
	    }else{
	    	//form.setRoot(null);
	    }
	    dialog.setScene(form);
	    
	    dialog.show();
	    dialog.toFront();
	    this.window = dialog;
	    loadData(export);
	}
	
	public void loadData(CmdDoExport export){
		this.export = export;
		cboExportType.setValue(this.export.getExportType());
		if(this.export.getOutputFile()!=null) {
			txtSavePath.setText(this.export.getOutputFile().getPath());
		}else{
			txtSavePath.setText("");
		}
		changedExportType();
	}
	
	@Override
	public Pane getContent() {
		// TODO Auto-generated method stub
		
		return root;
	}
	/*
	 * FXML Events
	 */
	@FXML
	private void changedExportType(){
		if(cboExportType.getValue()==ExportType.PDF){
			//txtSavePath.setEditable(true);
			btnSelectPath.setDisable(false);
		}else if(cboExportType.getValue()==ExportType.Word){
			//txtSavePath.setEditable(true);
			btnSelectPath.setDisable(false);
		}else if(cboExportType.getValue()==ExportType.Printer){
			//txtSavePath.setEditable(false);
			btnSelectPath.setDisable(true);
		}
	}
	@FXML
	private void saveIn(){
		//TODO Show File Save Dialog
		FileChooser fileChooser = new FileChooser();
		
		//extension Filter
		FileChooser.ExtensionFilter extFilter;
		if(cboExportType.getValue()==ExportType.PDF){
			extFilter = new ExtensionFilter("PDF File (*.pdf)","*.pdf");
		}else if(cboExportType.getValue()==ExportType.Word){
			extFilter = new ExtensionFilter("Word File (*.docx)","*.docx");
		}else{
			extFilter = null;
		}
		fileChooser.getExtensionFilters().add(extFilter);
		
		//show save file dialog
		File file = fileChooser.showSaveDialog(this.window);
		
		//save the choosen file
		this.export.setOutputFile(file);
		txtSavePath.setText(file.getPath());
		
	}
	
	@FXML
	private void cancel(){
		//TODO set the Export object to null
		this.export = null;
		this.window.close();
		
	}
	
	@FXML void submit(){
		//TODO Check if everything is correct
		this.export.setExportType(cboExportType.getValue());
		//this.export.setOutputPath(txtSavePath.getText());
		this.window.close();
		this.controller.doExport(this.export);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		cboExportType.getItems().setAll(ExportType.values());
	}
	
	/*
	 * FXML Elements
	 */
	@FXML
	private AnchorPane root;
	
	@FXML
	private ComboBox<ExportType> cboExportType;
	
	@FXML
	private Button btnSelectPath;
	@FXML
	private TextField txtSavePath;

	
}
