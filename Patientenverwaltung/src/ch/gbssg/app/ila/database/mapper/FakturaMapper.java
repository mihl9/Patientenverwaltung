package ch.gbssg.app.ila.database.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ch.gbssg.app.model.Faktura;
/**
 * This class defines the relation between the Faktura Model and the table v_Faktura
 * @author Michael Huber
 * @version 1.0
 */
public class FakturaMapper implements RowMapper<Faktura> {

	@Override
	public Faktura mapRow(ResultSet rs, int rowNum) throws SQLException {
		Faktura faktura = new Faktura();
		/*Medical History Infos*/
		faktura.setId(rs.getInt("MedHId"));
		faktura.setUserId(rs.getInt("MedHUsrID_FK"));
		faktura.setPatientId(rs.getInt("MedHPatID_FK"));
		faktura.setDateFrom(rs.getDate("MedHDateFrom").toLocalDate());
		faktura.setHour(rs.getDouble("MedHHour"));
		faktura.setBillState(rs.getInt("MedHBillState_CD"));
		faktura.setBillDueTo(rs.getDate("MedHBillDueTo").toLocalDate());
		/*Patient Information*/
		faktura.setGenderCode(rs.getInt("PatGender_CD"));
		faktura.setFirstname(rs.getString("PatFirstName"));
		faktura.setLastname(rs.getString("PatLastName"));
		faktura.setAddress(rs.getString("PatAddress"));
		faktura.setPlz(rs.getString("PatPLZ"));
		faktura.setPlace(rs.getString("PatPlace"));
		faktura.setAhv(rs.getString("PatAhv"));
		faktura.setInsuranceNumber(rs.getString("PatInsuranceNumber"));
		/*User Information*/
		faktura.setUsrFirstname(rs.getString("UsrFirstName"));
		faktura.setUsrLastname(rs.getString("UsrLastName"));
		faktura.setHourlyWage(rs.getDouble("UsrHourlyWage"));
		return faktura;
	}

}
