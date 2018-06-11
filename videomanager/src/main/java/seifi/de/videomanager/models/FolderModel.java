package seifi.de.videomanager.models;


public class FolderModel {
  int id;
  String name;
  String path;
  int state;
  
  public FolderModel(){
  }
  
  public FolderModel(int id, String name, String path, int state){
    this.id = id;
    this.path = path;
    this.name = name;
    this.state = state;
  }
  
  
  public void setId(int id) {
    this.id = id;
  }

  
  public void setName(String name) {
    this.name = name;
  }

  
  public void setPath(String path) {
    this.path = path;
  }

  
  public void setState(int state) {
    this.state = state;
  }

  public int getId() {
    return id;
  }
  
  public String getPath() {
    return path;
  }
  
  public String getName() {
    return name;
  }
  
  public int getState() {
    return state;
  }

}
