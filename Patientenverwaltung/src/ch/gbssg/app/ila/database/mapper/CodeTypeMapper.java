package ch.gbssg.app.ila.database.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ch.gbssg.app.model.CodeType;
/**
 * This class defines the relation between the CodeType Model and the table t_CodeType
 * @author Michael Huber
 * @version 1.0
 */
public class CodeTypeMapper implements RowMapper<CodeType> {

	@Override
	public CodeType mapRow(ResultSet rs, int rowNum) throws SQLException {
		CodeType code = new CodeType();
		code.setId(rs.getInt("CdtID"));
		code.setDesc(rs.getString("CdtDesc"));
		return code;
	}

}
