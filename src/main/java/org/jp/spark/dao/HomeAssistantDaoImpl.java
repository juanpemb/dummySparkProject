package org.jp.spark.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.jp.spark.model.HomeAssistant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class HomeAssistantDaoImpl implements HomeAssistantDao {
	private static final String SELECT_FROM_HOMEASSISTANT = "select ha.* from home_assistant ha";

	private NamedParameterJdbcTemplate template;

	@Autowired
	public HomeAssistantDaoImpl(DataSource ds) {
		template = new NamedParameterJdbcTemplate(ds);
	}

	@Override
	public List<HomeAssistant> getAll() {
		return template.query(SELECT_FROM_HOMEASSISTANT, homeAssistantMapper);
	}
	
	@Override
	public void save(HomeAssistant homeAssistant) {
		Map<String,Object> params = new HashMap<>();
		params.put("name",homeAssistant.getName() );
		params.put("last_name",homeAssistant.getLastName() );
		params.put("mail",homeAssistant.getMail() );
		params.put("phone",homeAssistant.getPhone() );
		params.put("valoracion",homeAssistant.getValoracion() );
		params.put("cp",homeAssistant.getCp() );
		
		template.update("insert into home_assistant (name, last_name,mail,phone,valoracion,cp) values (:name, :last_name, :mail, :phone, :valoracion, :cp) ", params);
	}
	

	private RowMapper<HomeAssistant> homeAssistantMapper = (rs, rowNum) -> {
		return new HomeAssistant(rs.getString("name"),
				rs.getString("last_name"),
				rs.getString("mail"),
				rs.getString("phone"),
				rs.getString("valoracion"),
				rs.getString("cp"));
	};

}
