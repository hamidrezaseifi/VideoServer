package seifi.de.videomanager.models;

public class SettingModel {
  
  String settingName;
  String settingValue;

  public SettingModel() {

  }

  public SettingModel(final String settingName, final String settingValue) {
    this.settingName = settingName;
    this.settingValue = settingValue;
  }

  public String getSettingName() {
    return this.settingName;
  }

  public void setSettingName(final String settingName) {
    this.settingName = settingName;
  }

  public String getSettingValue() {
    return this.settingValue;
  }

  public void setSettingValue(final String settingValue) {
    this.settingValue = settingValue;
  }
  
}
