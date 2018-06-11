package seifi.de.videomanager.bl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import seifi.de.videomanager.dao.TabsDao;
import seifi.de.videomanager.models.TabModel;

@Service
public class TabsHandler {
  private final TabsDao tabsDao;

  @Autowired
  public TabsHandler(final TabsDao tabsDao) {
    this.tabsDao = tabsDao;
  }
  
  public List<TabModel> listTabs(){
    
    return tabsDao.listTabs();
  }

}
