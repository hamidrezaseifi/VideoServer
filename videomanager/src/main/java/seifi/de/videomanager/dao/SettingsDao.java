package seifi.de.videomanager.dao;

import java.util.List;

import seifi.de.videomanager.models.SettingModel;

public interface SettingsDao {

  List<SettingModel> listSettings();
}
