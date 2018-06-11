package seifi.de.videomanager.bl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import seifi.de.videomanager.dao.FoldersDao;
import seifi.de.videomanager.models.FolderModel;

@Service
public class FoldersHandler {
  
  private final FoldersDao foldersDao;

  @Autowired
  public FoldersHandler(final FoldersDao foldersDao) {
    this.foldersDao = foldersDao;
  }

  public List<FolderModel> listFolders(){
    return foldersDao.listFolders();
  }

  public FolderModel readFolder(int id){
    return foldersDao.readFolder(id);
  }

}
