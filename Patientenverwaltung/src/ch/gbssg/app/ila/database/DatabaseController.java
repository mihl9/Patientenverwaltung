package ch.gbssg.app.ila.database;

import java.util.List;

import ch.gbssg.app.model.Code;
import ch.gbssg.app.model.CodeType;
import ch.gbssg.app.model.Faktura;
import ch.gbssg.app.model.MedicalHistory;
import ch.gbssg.app.model.Patient;
import ch.gbssg.app.model.User;
import ch.gbssg.app.util.command.CmdDeleteEntity;
import ch.gbssg.app.util.command.CmdFilterEntity;
import ch.gbssg.app.util.command.CmdInsertEntity;
import ch.gbssg.app.util.command.CmdUpdateEntity;
import ch.gbssg.core.pac.AgentCommand;
import ch.gbssg.core.pac.AgentController;
import ch.gbssg.core.pac.ICommand;

/**
 * Represents the Controller for the Intermediate Level Agent Database
 * It handles all Insert, Update, Select and Delete commands used in this application
 * It passes the commands to the right handler
 * @author Michael Huber, Sandro Pedrett
 * @version 1.0
 */
public class DatabaseController extends AgentController {
	/**
	 * Is called when the Agent is created
	 */
	@Override
	public boolean setupAgent() {
		model = new DatabaseModel();
		
		return true;
	}

	@Override
	protected void processMessage(AgentCommand messages) {
		ICommand cmd = messages.peek();
		
		if (cmd instanceof CmdFilterEntity) {
			CmdFilterEntity<?> filter = (CmdFilterEntity<?>) cmd;
			executeFilterEntity(filter);
		}else if(cmd instanceof CmdInsertEntity){
			CmdInsertEntity<?> insert = (CmdInsertEntity<?>) cmd;
			executeInsertEntity(insert);
		}else if(cmd instanceof CmdDeleteEntity){
			CmdDeleteEntity<?> delete = (CmdDeleteEntity<?>) cmd;
			executeDeleteEntity(delete);
		}else if(cmd instanceof CmdUpdateEntity){
			CmdUpdateEntity<?> update = (CmdUpdateEntity<?>) cmd;
			executeUpdateEntity(update);
		}

	}
	/**
	 * Check which type of model should be updated and then send it to the right handler
	 * @param update the update command
	 */
	private void executeUpdateEntity(CmdUpdateEntity<?> update){
		if (update.getType() == Code.class) {
			int affectedRow = model.updateCode((Code) update.getToUpdatingEntity());
			
			update.setAffectedRows(affectedRow);
		}else if(update.getType() == CodeType.class){
			int affectedRow = model.updateCodeType((CodeType) update.getToUpdatingEntity());
			
			update.setAffectedRows(affectedRow);
		}else if(update.getType() == MedicalHistory.class){
			int affectedRow = model.updateMedicalHistory((MedicalHistory) update.getToUpdatingEntity());
			
			update.setAffectedRows(affectedRow);
		}else if(update.getType() == Patient.class){
			int affectedRow = model.updatePatient((Patient) update.getToUpdatingEntity());
			
			update.setAffectedRows(affectedRow);
		}else if(update.getType() == User.class) {
			int affectedRow = model.updateUser((User) update.getToUpdatingEntity());
			
			update.setAffectedRows(affectedRow);
		}
	}
	/**
	 * Check which type of model should be deleted and then send it to the right handler
	 * @param update the delete command
	 */
	private void executeDeleteEntity(CmdDeleteEntity<?> delete){
		if (delete.getType() == Code.class) {
			int statusCode = model.removeCode((Code) delete.getToDeletingEntity());
			
			delete.setResult(statusCode);
		}else if(delete.getType() == CodeType.class){
			int statusCode = model.removeCodeType((CodeType) delete.getToDeletingEntity());
			
			delete.setResult(statusCode);
		}else if(delete.getType() == MedicalHistory.class){
			int statusCode = model.removeMedicalHistory((MedicalHistory) delete.getToDeletingEntity());
			
			delete.setResult(statusCode);
		}else if(delete.getType() == Patient.class){
			int statusCode = model.removePatient((Patient) delete.getToDeletingEntity());
			
			delete.setResult(statusCode);
		}else if(delete.getType() == User.class) {
			int statusCode = model.removeUser((User) delete.getToDeletingEntity());
			
			delete.setResult(statusCode);
		}
	}
	/**
	 * Check which type of model should be inserted and then send it to the right handler
	 * @param update the insert command
	 */
	private void executeInsertEntity(CmdInsertEntity<?> insert){
		if (insert.getType() == Code.class) {
			int insertedKey = model.addCode((Code) insert.getToAddingEntity());
			
			insert.setInsertedKey(insertedKey);
		}else if(insert.getType() == CodeType.class){
			int insertedKey = model.addCodeType((CodeType) insert.getToAddingEntity());
			
			insert.setInsertedKey(insertedKey);
		}else if(insert.getType() == MedicalHistory.class){
			int insertedKey = model.addMedicalHistory((MedicalHistory) insert.getToAddingEntity());
			
			insert.setInsertedKey(insertedKey);
		}else if(insert.getType() == Patient.class){
			int insertedKey = model.addPatient((Patient) insert.getToAddingEntity());
			
			insert.setInsertedKey(insertedKey);
		}else if(insert.getType() == User.class) {
			int insertedKey = model.addUser((User) insert.getToAddingEntity());
			
			insert.setInsertedKey(insertedKey);
		}
	}
	/**
	 * Check which type of model should be filtered and then send it to the right handler
	 * @param update the filter command
	 */
	private void executeFilterEntity(CmdFilterEntity<?> filter){
		if (filter.getType() == Code.class) {
			List<?> matches = model.getFilteredCode((Code) filter.getFilterEntity());
			
			filter.setEntities(matches);
		}else if(filter.getType() == CodeType.class){
			List<?> matches = model.getFilteredCodeType((CodeType) filter.getFilterEntity());
			
			filter.setEntities(matches);
		}else if (filter.getType() == Faktura.class) {
			List<?> matches = model.getFilteredFaktura((Faktura) filter.getFilterEntity());
			
			filter.setEntities(matches);
		}else if(filter.getType() == MedicalHistory.class){
			List<?> matches = model.getFilteredMedicalHistory((MedicalHistory) filter.getFilterEntity());
			
			filter.setEntities(matches);
		}else if(filter.getType() == Patient.class){
			List<?> matches = model.getFilteredPatient((Patient) filter.getFilterEntity());
			
			filter.setEntities(matches);
		}else if (filter.getType() == User.class) {
			List<?> matches = model.getFilteredUser((User) filter.getFilterEntity());
			
			filter.setEntities(matches);
		}
	}
	
	private DatabaseModel model;
}
