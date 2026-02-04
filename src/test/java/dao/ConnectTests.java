package dao;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectTests {

	 @Test
	    public void testConnection() throws Exception {

	        Class.forName("org.mariadb.jdbc.Driver");

	        Connection connection = DriverManager.getConnection(
	                "jdbc:mariadb://localhost:3307/webdb",
	                "webuser",
	               "kim61640");

	        Assertions.assertNotNull(connection);

	        connection.close();
	    }
	 @Test
	    public void test1() {

	        int v1 = 10;
	        int v2 = 110;

	        Assertions.assertEquals(v1,v2);

	    }

	    @Test
	    public void testHikariCP() throws Exception {

	        HikariConfig config = new HikariConfig();
	        config.setDriverClassName("org.mariadb.jdbc.Driver");
	        config.setJdbcUrl("jdbc:mariadb://localhost:3307/webdb");
	        config.setUsername("webuser");
	        config.setPassword("kim61640");											
	        config.addDataSourceProperty("cachePrepStmts", "true");
	        config.addDataSourceProperty("prepStmtCacheSize", "250");
	        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

	        HikariDataSource ds = new HikariDataSource(config);
	        Connection connection = ds.getConnection();

	        System.out.println(connection);

	        connection.close();

	    }
	}

