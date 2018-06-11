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

import seifi.de.videomanager.dao.FoldersDao;
import seifi.de.videomanager.dao.PathSubtitlesDao;
import seifi.de.videomanager.dao.TabsDao;
import seifi.de.videomanager.models.PathSubtitleModel;


@Repository
public class PathSubtitlesDaoImpl implements PathSubtitlesDao {
    
  private final Logger logger = LoggerFactory.getLogger(PathSubtitlesDaoImpl.class);

  private final JdbcTemplate               jdbcTemplate;
  private final PlatformTransactionManager platformTransactionManager;

  
  @Autowired
  public PathSubtitlesDaoImpl(final JdbcTemplate jdbcTemplate, final PlatformTransactionManager platformTransactionManager) {
    this.jdbcTemplate = jdbcTemplate;
    this.platformTransactionManager = platformTransactionManager;
  }

  
  @Override
  public List<PathSubtitleModel> listPathSubtitls() {

    return queryPathSubtitls();
  }
  

  private List<PathSubtitleModel> queryPathSubtitls() {
    
    final String sql = " select id, path, suburl from tblpathsubtitle";
    
    List<PathSubtitleModel> list = jdbcTemplate.query(sql, new Object[]{}, new RowMapper<PathSubtitleModel>(){

      @Override
      public PathSubtitleModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return createTabFromResultSet(rs);
      }
      
    });
    
    return list;
    
  }  

  private PathSubtitleModel createTabFromResultSet(ResultSet rs) throws SQLException {
      
    PathSubtitleModel amodel = new PathSubtitleModel();
  
    amodel.setId(rs.getInt("id"));
    amodel.setSuburl(rs.getString("suburl"));
    amodel.setPath(rs.getString("path"));
    
    return amodel;
      
  }


  @Override
  public boolean savePathSubtitles(PathSubtitleModel sub) {

    return queryInsertPathSubtitls(sub);
  }

  private boolean queryInsertPathSubtitls(PathSubtitleModel sub) {
    
    final String sql = " INSERT INTO tblpathsubtitle(path, suburl) VALUES (?, ?)";
    
    int affected = jdbcTemplate.update(sql, new Object[]{sub.getPath(), sub.getSubtitleUrl()});
    
    return affected > 0;
    
  }  


  @Override
  public boolean deletePathSubtitles(int id) {

    return queryDeletePathSubtitls(id);
  }

  private boolean queryDeletePathSubtitls(int id) {
    
    final String sql = " delete from tblpathsubtitle where id = ?";
    
    int affected = jdbcTemplate.update(sql, new Object[]{id});
    
    return affected > 0;
    
  }


  @Override
  public PathSubtitleModel getPathSubtitlesFromPath(String path) {

    return queryPathSubtitlsFromPath(path);
  } 
  
  private PathSubtitleModel queryPathSubtitlsFromPath(String path) {
    
    final String sql = " select id, path, suburl from tblpathsubtitle where path = ?";
    
    List<PathSubtitleModel> list = jdbcTemplate.query(sql, new Object[]{path}, new RowMapper<PathSubtitleModel>(){

      @Override
      public PathSubtitleModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return createTabFromResultSet(rs);
      }
      
    });
    
    return list.size() > 0 ? list.get(0) : null;
    
  }  
  
}
