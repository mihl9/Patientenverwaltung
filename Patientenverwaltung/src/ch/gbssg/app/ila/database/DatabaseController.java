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
 * TODO
 * @author pedrett.sandro
 *
 */
public class DatabaseController extends AgentController {

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
