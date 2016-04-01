package org.jp.spark.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConfig {

	private static final String CLEARDB_DATABASE_URL = "CLEARDB_DATABASE_URL";
	@Autowired
	private Environment environment;

	@Bean
	public DataSource dataSource() {
		if (isProductionEnviroment())
			return produccionDataSource();
		return devDataSource();
	}

	private boolean isProductionEnviroment() {
		return null != environment.getProperty(CLEARDB_DATABASE_URL);
	}

	private DataSource devDataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.HSQL)
				.addScript("sql/create-db.sql")
				.addScript("sql/insert-data.sql").build();
		return db;
	}

	private DataSource produccionDataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
		
		hikariConfig
				.setJdbcUrl("jdbc:mysql://us-cdbr-iron-east-03.cleardb.net/heroku_2a8513372871120?reconnect=true");
		hikariConfig.setUsername("b1aeebee2a388c");
		hikariConfig.setPassword("e1cadd2b");
//		hikariConfig.setJdbcUrl(environment.getProperty(CLEARDB_DATABASE_URL));

		hikariConfig.setMaximumPoolSize(5);
		hikariConfig.setConnectionTestQuery("SELECT 1");
		hikariConfig.setPoolName("springHikariCP");

		hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
		hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize",
				"250");
		hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit",
				"2048");
		hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts",
				"true");

		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		return dataSource;
	}

}