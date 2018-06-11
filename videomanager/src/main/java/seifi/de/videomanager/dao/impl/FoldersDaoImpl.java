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
import seifi.de.videomanager.models.FolderModel;


@Repository
public class FoldersDaoImpl implements FoldersDao {
    
  private final Logger logger = LoggerFactory.getLogger(FoldersDaoImpl.class);

  private final JdbcTemplate               jdbcTemplate;
  private final PlatformTransactionManager platformTransactionManager;

  
  @Autowired
  public FoldersDaoImpl(final JdbcTemplate jdbcTemplate, final PlatformTransactionManager platformTransactionManager) {
    this.jdbcTemplate = jdbcTemplate;
    this.platformTransactionManager = platformTransactionManager;
  }

  
  @Override
  public List<FolderModel> listFolders() {

    return queryForFolders();
  }
  

  private List<FolderModel> queryForFolders() {
    
    final String sql = " select id, name, path, state from tblfolders";
    
    List<FolderModel> list = jdbcTemplate.query(sql, new Object[]{}, new RowMapper<FolderModel>(){

      @Override
      public FolderModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return createTabFromResultSet(rs);
      }
      
    });
    
    return list;
    
  }  

  private FolderModel createTabFromResultSet(ResultSet rs) throws SQLException {
      
    FolderModel amodel = new FolderModel();
  
    amodel.setId(rs.getInt("id"));
    amodel.setState(rs.getInt("state"));
    amodel.setName(rs.getString("name"));
    amodel.setPath(rs.getString("path"));
    
    return amodel;
      
  }

  @Override
  public FolderModel readFolder(int id) {

    return queryForFolder(id);
  }

  private FolderModel queryForFolder(int id) {
    
    final String sql = " select id, name, path, state from tblfolders where id = ?";
    
    List<FolderModel> list = jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<FolderModel>(){

      @Override
      public FolderModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return createTabFromResultSet(rs);
      }
      
    });
    
    return list.size() > 0 ? list.get(0) : null;
    
  }  

}
