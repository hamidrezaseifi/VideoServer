package seifi.de.videomanager.dao;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * This class is used to configure the database connection and the connection behavior.
 *
 * @author linsoli
 *
 */
@Component
@PropertySource("classpath:config/db.properties")
public class DbConfiguration {

  private final Logger log = LoggerFactory.getLogger(getClass());

  @Value("${poolName}")
  private String poolName;

  @Value("${jdbcUrl}")
  private String jdbcUrl;

  @Value("${dbUserName}")
  private String dbUserName;

  @Value("${password}")
  private String password;

  @Value("${minIdleConnections}")
  private Integer minIdleConnections;

  @Value("${maxPoolSize}")
  private Integer maxPoolSize;

  @Value("${cachePreparedStatements}")
  private Boolean cachePreparedStatements;

  @Value("${preparedStatementsCacheSize}")
  private Integer preparedStatementsCacheSize;

  @Value("${preparedStatementsCacheSqlLimit}")
  private Integer preparedStatementsCacheSqlLimit;

  @Value("${useServerSidePreparedStatements}")
  private Boolean useServerSidePreparedStatements;

  @Value("${className}")
  private String className;

  @Bean
  public DataSource getDatasource() {
    log.info("DB config: {}@{}", dbUserName, jdbcUrl);

    final HikariConfig configuration = new HikariConfig();
    configuration.setAutoCommit(false);
    configuration.setPoolName(poolName);
    configuration.setDriverClassName(className);
    configuration.setMinimumIdle(minIdleConnections);
    configuration.setJdbcUrl(jdbcUrl);
    configuration.setIdleTimeout(120000L);
    configuration.setMaximumPoolSize(maxPoolSize);
    configuration.setUsername(dbUserName);
    configuration.setPassword(password);
    configuration.setTransactionIsolation("TRANSACTION_READ_COMMITTED");

    configuration.addDataSourceProperty("cachePrepStmts", cachePreparedStatements);
    configuration.addDataSourceProperty("prepStmtCacheSize", preparedStatementsCacheSize);
    configuration.addDataSourceProperty("prepStmtCacheSqlLimit", preparedStatementsCacheSqlLimit);
    configuration.addDataSourceProperty("useServerPrepStmts", useServerSidePreparedStatements);

    // experimental
    // configuration.addDataSourceProperty("useLocalSessionState", true);
    // configuration.addDataSourceProperty("useLocalTransactionState", true);
    // configuration.addDataSourceProperty("rewriteBatchedStatements", true);
    // configuration.addDataSourceProperty("cacheResultSetMetadata", true);
    // configuration.addDataSourceProperty("cacheServerConfiguration", true);
    // // configuration.addDataSourceProperty("elideSetAutoCommits", true);
    // configuration.addDataSourceProperty("maintainTimeStats", false);

    final HikariDataSource ds = new HikariDataSource(configuration);
    ds.validate();
    return ds;
  }

}
