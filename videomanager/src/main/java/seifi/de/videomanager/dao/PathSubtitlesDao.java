package seifi.de.videomanager.dao;

import java.util.List;

import seifi.de.videomanager.models.PathSubtitleModel;


public interface PathSubtitlesDao {
  
  List<PathSubtitleModel> listPathSubtitls();
  
  boolean savePathSubtitles(PathSubtitleModel sub);
  
  boolean deletePathSubtitles(int id);

  PathSubtitleModel getPathSubtitlesFromPath(String path);
  
}
