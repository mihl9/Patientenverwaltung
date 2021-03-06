package ch.gbssg.app.ila.database.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ch.gbssg.app.model.Patient;
/**
 * This class defines the relation between the Patient Model and the table t_Patient
 * @author Michael Huber
 * @version 1.0
 */
public class PatientMapper implements RowMapper<Patient> {

	@Override
	public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
		Patient patient = new Patient();
		/*Patient Information*/
		patient.setId(rs.getInt("PatID"));
		patient.setGenderCode(rs.getInt("PatGender_CD"));
		patient.setLastname(rs.getString("PatLastName"));
		patient.setFirstname(rs.getString("PatFirstName"));
		try {
			patient.setBirthday(rs.getDate("PatBirthdate").toLocalDate());
		} catch (Exception e) {
			patient.setBirthday(null);
		}
		patient.setAddress(rs.getString("PatAddress"));
		patient.setPlz(rs.getString("PatPLZ"));
		patient.setPlace(rs.getString("PatPlace"));
		patient.setAhv(rs.getString("PatAhv"));
		patient.setInsuranceNumber(rs.getString("PatInsuranceNumber"));
		patient.setInactive(rs.getBoolean("PatInactive"));
		return patient;
	}

}
