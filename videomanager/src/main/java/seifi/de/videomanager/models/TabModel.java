package seifi.de.videomanager.models;

public class TabModel {
  
  int    id;
  int    index;
  String name;
  String url;
  int    state;

  public TabModel() {

  }

  public TabModel(final int id, final int index, final String name, final String url, final int state) {
    this.id = id;
    this.index = index;
    this.name = name;
    this.url = url;
    this.state = state;
  }

  public int getId() {
    return this.id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  public int getIndex() {
    return this.index;
  }

  public void setIndex(final int index) {
    this.index = index;
  }

  public String getName() {
    return this.name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(final String url) {
    this.url = url;
  }

  public int getState() {
    return this.state;
  }

  public void setState(final int state) {
    this.state = state;
  }
  
}
