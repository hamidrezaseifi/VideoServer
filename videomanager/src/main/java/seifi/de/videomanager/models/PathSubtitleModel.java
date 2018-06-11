package seifi.de.videomanager.models;


public class PathSubtitleModel {
  int id;
  String suburl;
  String path;
  
  public PathSubtitleModel(){
     
  }
  
  public PathSubtitleModel(int id, String path, String suburl){
    this.id = id;
    this.path = path;
    this.suburl = suburl;
    
  }
  
  public int getId() {
    return id;
  }
  
  public String getPath() {
    return path;
  }
  
  public String getSubtitleUrl() {
    return suburl;
  }

  
  public String getSuburl() {
    return suburl;
  }

  
  public void setSuburl(String suburl) {
    this.suburl = suburl;
  }

  
  public void setId(int id) {
    this.id = id;
  }

  
  public void setPath(String path) {
    this.path = path;
  }
  
}
