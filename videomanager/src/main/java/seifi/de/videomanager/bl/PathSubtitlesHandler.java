package seifi.de.videomanager.bl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import seifi.de.videomanager.dao.PathSubtitlesDao;
import seifi.de.videomanager.models.PathSubtitleModel;

@Service
public class PathSubtitlesHandler {
  
  private final PathSubtitlesDao pathSubtitlesDao;

  @Autowired
  public PathSubtitlesHandler(final PathSubtitlesDao pathSubtitlesDao) {
    this.pathSubtitlesDao = pathSubtitlesDao;
  }

  public List<PathSubtitleModel> listPathSubtitles(){
    return pathSubtitlesDao.listPathSubtitls();
  }

  public PathSubtitleModel getPathSubtitlesFromPath(String path){
    return pathSubtitlesDao.getPathSubtitlesFromPath(path);
  }

  public boolean savePathSubtitles(PathSubtitleModel sub){
    return pathSubtitlesDao.savePathSubtitles(sub);
  }

  public boolean deletePathSubtitle(int id){
    return pathSubtitlesDao.deletePathSubtitles(id);
  }

}
