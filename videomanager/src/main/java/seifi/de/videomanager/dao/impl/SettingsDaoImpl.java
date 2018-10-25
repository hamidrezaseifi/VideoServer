package seifi.de.videomanager.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import seifi.de.videomanager.dao.SettingsDao;
import seifi.de.videomanager.models.SettingModel;

@Repository
public class SettingsDaoImpl implements SettingsDao {

  private final Logger logger = LoggerFactory.getLogger(SettingsDaoImpl.class);

  private final JdbcTemplate               jdbcTemplate;
  private final PlatformTransactionManager platformTransactionManager;

  @Autowired
  public SettingsDaoImpl(final JdbcTemplate jdbcTemplate, final PlatformTransactionManager platformTransactionManager) {
    this.jdbcTemplate = jdbcTemplate;
    this.platformTransactionManager = platformTransactionManager;
  }

  @Override
  public List<SettingModel> listSettings() {

    return this.queryForTabs();
  }

  private List<SettingModel> queryForTabs() {
    
    final String sql = " select settingname, settingvalue from tblsettings order by settingname";
    
    final List<SettingModel> list = this.jdbcTemplate.query(sql, new Object[] {}, new RowMapper<SettingModel>() {

      @Override
      public SettingModel mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        return SettingsDaoImpl.this.createSettingsFromResultSet(rs);
      }
      
    });
    
    return list;
    
  }

  private SettingModel createSettingsFromResultSet(final ResultSet rs) throws SQLException {
    
    return new SettingModel(rs.getString("settingname"), rs.getString("settingvalue"));

  }
}
