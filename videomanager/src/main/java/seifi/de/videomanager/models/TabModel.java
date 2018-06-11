package seifi.de.videomanager.models;


public class TabModel {
  int id;
  int index;
  String name;
  String url;
  int state;
  
  public TabModel() {
    
  }
  
  public TabModel(int id, int index, String name, String url, int state) {
    this.id = id;
    this.index = index;
    this.name = name;
    this.url = url;
    this.state = state;
  }
  
  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public int getIndex() {
    return index;
  }
  
  public void setIndex(int index) {
    this.index = index;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getUrl() {
    return url;
  }
  
  public void setUrl(String url) {
    this.url = url;
  }
  
  public int getState() {
    return state;
  }
  
  public void setState(int state) {
    this.state = state;
  }
 
}
