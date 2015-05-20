package ch.gbssg.app.ila.database.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ch.gbssg.app.model.Code;

public class CodeMapper implements RowMapper<Code> {

	@Override
	public Code mapRow(ResultSet rs, int rowNum) throws SQLException {
		Code code = new Code();
		code.setId(rs.getInt("CdsID"));
		code.setDescription(rs.getString("CdsDesc"));
		code.setCodeTypeId(rs.getInt("CdsCdtID_FK"));
		return code;
	}

}
