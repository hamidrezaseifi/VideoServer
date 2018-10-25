package seifi.de.videomanager.bl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import seifi.de.videomanager.dao.SettingsDao;
import seifi.de.videomanager.models.SettingModel;

@Service
public class SettingsHandler {

  private static String SUBSCENE_SEARCH_PATH = "subscene_search_path";
  
  private final SettingsDao settingsDao;
  
  private final Map<String, String> listSettings;

  @Autowired
  public SettingsHandler(final SettingsDao settingsDao) {
    this.settingsDao = settingsDao;
    this.listSettings = new HashMap<>();
    final List<SettingModel> list = this.settingsDao.listSettings();
    for (final SettingModel model : list) {
      this.listSettings.put(model.getSettingName(), model.getSettingValue());
    }
  }
  
  // subscene_search_path
  public String subsceneSearchPath() {
    if (this.listSettings.keySet().contains(SUBSCENE_SEARCH_PATH)) {
      return this.listSettings.get(SUBSCENE_SEARCH_PATH);
    }

    return "";
  }

}
