package ch.gbssg.app.ila.database;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ch.gbssg.app.ila.database.dao.CodeJDBCTemplate;
import ch.gbssg.app.ila.database.dao.CodeTypeJDBCTemplate;
import ch.gbssg.app.ila.database.dao.FakturaJDBCTemplate;
import ch.gbssg.app.ila.database.dao.MedicalHistoryJDBCTemplate;
import ch.gbssg.app.ila.database.dao.PatientJDBCTemplate;
import ch.gbssg.app.ila.database.dao.UserJDBCTemplate;
import ch.gbssg.app.model.Code;
import ch.gbssg.app.model.CodeType;
import ch.gbssg.app.model.Faktura;
import ch.gbssg.app.model.MedicalHistory;
import ch.gbssg.app.model.Patient;
import ch.gbssg.app.model.User;

public class DatabaseModel {
 // wenn u1 != null
		// jdbctamplte.get(p1)
 // jdbctample.get();
	private static ApplicationContext ctx;
	
	private CodeJDBCTemplate codeJdbcTemplate;
	private CodeTypeJDBCTemplate codeTypeJdbcTemplate;
	private FakturaJDBCTemplate fakturaJdbcTemplate;
	private MedicalHistoryJDBCTemplate medicalHistoryJdbcTemplate;
	private PatientJDBCTemplate patientJdbcTemplate;
	private UserJDBCTemplate userJdbcTemplate;
	
	public DatabaseModel() {
		// include the beans config file.
		ctx = new ClassPathXmlApplicationContext("Beans.xml");
		
		codeJdbcTemplate = (CodeJDBCTemplate)ctx.getBean("CodeJDBCTemplate");
		codeTypeJdbcTemplate = (CodeTypeJDBCTemplate)ctx.getBean("CodeTypeJDBCTemplate");
		fakturaJdbcTemplate = (FakturaJDBCTemplate)ctx.getBean("FakturaJDBCTemplate");
		medicalHistoryJdbcTemplate = (MedicalHistoryJDBCTemplate)ctx.getBean("MedicalHistoryJDBCTemplate");
		patientJdbcTemplate = (PatientJDBCTemplate)ctx.getBean("PatientJDBCTemplate");
		userJdbcTemplate = (UserJDBCTemplate)ctx.getBean("UserJDBCTemplate");

	}
	
	/*
	 * Filtering part
	 */
	/**
	 * returns a list of matches Codes. don't found any matches, return null.
	 * @param user filtered Code; by null return all Codes
	 * @return get a list of matches codes which matches with code parameter
	 */
	public List<Code> getFilteredCode(Code code) {
		if (code != null) {
			return  codeJdbcTemplate.filterByEntity(code);
		} else {
			return codeJdbcTemplate.get();
		}
	}
	
	/**
	 * returns a list of matches CodeTypes. don't found any matches, return null.
	 * @param user filtered Codetype; by null return all Codetypes
	 * @return get a list of matches CodeTypes which matches with CodeTypes parameter
	 */
	public List<CodeType> getFilteredCodeType(CodeType codeType) {
		if (codeType != null) {
			return  codeTypeJdbcTemplate.filterByEntity(codeType);
		} else {
			return codeTypeJdbcTemplate.get();
		}
	}
	
	/**
	 * returns a list of matching Faktura. don't found any matches, return null.
	 * @param user filtered Faktura; by null return all Fakturen
	 * @return get a list of matches Fakturen which matches with Faktura parameter
	 */
	public List<Faktura> getFilteredFaktura(Faktura faktura) {
		if (faktura != null) {
			return  fakturaJdbcTemplate.filterByEntity(faktura);
		} else {
			return fakturaJdbcTemplate.get();
		}
	}
	
	/**
	 * returns a list of matching Medical Histories. don't found any matches, return null.
	 * @param user filtered Med History; by null return all Med History
	 * @return get a list of matches Med Histories which matches with Med History parameter
	 */
	public List<MedicalHistory> getFilteredMedicalHistory(MedicalHistory medHistory) {
		if (medHistory != null) {
			return  medicalHistoryJdbcTemplate.filterByEntity(medHistory);
		} else {
			return medicalHistoryJdbcTemplate.get();
		}
	}
	
	/**
	 * returns a list of matching Patient. don't found any matches, return null.
	 * @param user filtered Patient; by null return all Patient
	 * @return get a list of matches Patient which matches with Patient parameter
	 */
	public List<Patient> getFilteredPatient(Patient patient) {
		if (patient != null) {
			return  patientJdbcTemplate.filterByEntity(patient);
		} else {
			return patientJdbcTemplate.get();
		}
	}
	
	/**
	 * returns a list of matches user. don't found any matches, return null.
	 * @param user filtered user; by null return all users
	 * @return get a list of matches users which matches with user parameter
	 */
	public List<User> getFilteredUser(User user) {
		if (user != null) {
			return  userJdbcTemplate.filterByEntity(user);
		} else {
			return userJdbcTemplate.get();
		}
	}
	
	/*
	 * Insert Part
	 */
	/**
	 * 
	 * @param code
	 * @return
	 */
	public int addCode(Code code){
		return codeJdbcTemplate.create(code);
	}
	/**
	 * 
	 * @param codeType
	 * @return
	 */
	public int addCodeType(CodeType codeType){
		return codeTypeJdbcTemplate.create(codeType);
	}
	/**
	 * 
	 * @param medHistory
	 * @return
	 */
	public int addMedicalHistory(MedicalHistory medHistory){
		return medicalHistoryJdbcTemplate.create(medHistory);
	}
	/**
	 * 
	 * @param patient
	 * @return
	 */
	public int addPatient(Patient patient){
		return patientJdbcTemplate.create(patient);
	}
	/**
	 * 
	 * @param user
	 * @return
	 */
	public int addUser(User user){
		return userJdbcTemplate.create(user);
	}
	/*
	 * Remove Part
	 */
	/**
	 * 
	 * @param code
	 * @return
	 */
	public int removeCode(Code code) {
		return codeJdbcTemplate.delete(code.getId());
	}
	
	/**
	 * 
	 * @param CodeType
	 * @return
	 */
	public int removeCodeType(CodeType codeType){
		return codeTypeJdbcTemplate.delete(codeType.getId());
	}
	
	/**
	 * 
	 * @param medHistory
	 * @return
	 */
	public int removeMedicalHistory(MedicalHistory medHistory){
		return medicalHistoryJdbcTemplate.delete(medHistory.getId());
	}
	
	/**
	 * 
	 * @param patient
	 * @return
	 */
	public int removePatient(Patient patient){
		return patientJdbcTemplate.delete(patient.getId());
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public int removeUser(User user){
		return userJdbcTemplate.delete(user.getId());
	}
	
	/*
	 * Update Part
	 */
	/**
	 * 
	 * @param code
	 * @return
	 */
	public int updateCode(Code code){
		return codeJdbcTemplate.update(code.getId(), code);
	}
	/**
	 * 
	 * @param codeType
	 * @return
	 */
	public int updateCodeType(CodeType codeType){
		return codeTypeJdbcTemplate.update(codeType.getId(), codeType);
	}
	/**
	 * 
	 * @param medHistory
	 * @return
	 */
	public int updateMedicalHistory(MedicalHistory medHistory){
		return medicalHistoryJdbcTemplate.update(medHistory.getId(), medHistory);
	}
	/**
	 * 
	 * @param patient
	 * @return
	 */
	public int updatePatient(Patient patient){
		return patientJdbcTemplate.update(patient.getId(), patient);
	}
	/**
	 * 
	 * @param user
	 * @return
	 */
	public int updateUser(User user){
		return userJdbcTemplate.update(user.getId(), user);
	}
}
