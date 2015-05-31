package ch.gbssg.app.ila.database.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ch.gbssg.app.model.MedicalHistory;
/**
 * This class defines the relation between the MedicalHistory Model and the table t_MedicalHistory
 * @author Michael Huber
 * @version 1.0
 */
public class MedicalHistoryMapper implements RowMapper<MedicalHistory> {

	@Override
	public MedicalHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
		MedicalHistory medicalHistory = new MedicalHistory();
		/*Medical History Infos*/
		medicalHistory.setId(rs.getInt("MedHId"));
		medicalHistory.setUserId(rs.getInt("MedHUsrID_FK"));
		medicalHistory.setPatientId(rs.getInt("MedHPatID_FK"));
		medicalHistory.setDateFrom(rs.getDate("MedHDateFrom").toLocalDate());
		medicalHistory.setHour(rs.getDouble("MedHHour"));
		medicalHistory.setSymptoms(rs.getString("MedHSymptoms"));
		medicalHistory.setDiagnostic(rs.getString("MedHDiagnostic"));
		medicalHistory.setNotes(rs.getString("MedHNotes"));
		medicalHistory.setBillState(rs.getInt("MedHBillState_CD"));
		medicalHistory.setBillDueTo(rs.getDate("MedHBillDueTo").toLocalDate());

		return medicalHistory;
	}

}
