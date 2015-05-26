package ch.gbssg.app.ila.database.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ch.gbssg.app.model.CodeType;

public class CodeTypeMapper implements RowMapper<CodeType> {

	@Override
	public CodeType mapRow(ResultSet rs, int rowNum) throws SQLException {
		CodeType code = new CodeType();
		code.setId(rs.getInt("CdtID"));
		code.setDesc(rs.getString("CdtDesc"));
		return code;
	}

}
