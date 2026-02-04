package dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;

public enum ConnectionUtil {

    INSTANCE;

    private HikariDataSource ds; 
// enum에서는 생성자가 자동으로 private로 지정된다.
    ConnectionUtil()  {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl("jdbc:mariadb://localhost:3307/webdb");
        config.setUsername("webuser");
        config.setPassword("kim61640");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        ds = new HikariDataSource(config); // 설정한 내용을 바탕으로 실제 커넥션 풀을 생성, 여러 개의 커넥션을 만들고 풀에 담아둠
    }

    public Connection getConnection()throws Exception {
        return ds.getConnection();
    }

}