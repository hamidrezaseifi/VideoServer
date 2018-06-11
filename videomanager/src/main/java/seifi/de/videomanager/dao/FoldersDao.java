package seifi.de.videomanager.dao;

import java.util.List;

import seifi.de.videomanager.models.FolderModel;

public interface FoldersDao {
  
  List<FolderModel> listFolders();
  
  FolderModel readFolder(int id);
}
