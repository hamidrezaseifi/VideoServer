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

import seifi.de.videomanager.dao.TabsDao;
import seifi.de.videomanager.models.TabModel;


@Repository
public class TabsDaoImpl implements TabsDao {
    
  private final Logger logger = LoggerFactory.getLogger(TabsDaoImpl.class);

  private final JdbcTemplate               jdbcTemplate;
  private final PlatformTransactionManager platformTransactionManager;

  
  @Autowired
  public TabsDaoImpl(final JdbcTemplate jdbcTemplate, final PlatformTransactionManager platformTransactionManager) {
    this.jdbcTemplate = jdbcTemplate;
    this.platformTransactionManager = platformTransactionManager;
  }

  
  @Override
  public List<TabModel> listTabs() {

    return queryForTabs();
  }
  

  private List<TabModel> queryForTabs() {
    
    final String sql = " select id, name, tabindex, url, state from tbltabs order by tabindex";
    
    List<TabModel> list = jdbcTemplate.query(sql, new Object[]{}, new RowMapper<TabModel>(){

      @Override
      public TabModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return createTabFromResultSet(rs);
      }
      
    });
    
    return list;
    
  }  

  private TabModel createTabFromResultSet(ResultSet rs) throws SQLException {
      
    TabModel amodel = new TabModel();
  
    amodel.setId(rs.getInt("id"));
    amodel.setIndex(rs.getInt("tabindex"));
    amodel.setState(rs.getInt("state"));
    amodel.setName(rs.getString("name"));
    amodel.setUrl(rs.getString("url"));
    
    return amodel;
      
  }
}
