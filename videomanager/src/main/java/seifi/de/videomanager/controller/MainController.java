package seifi.de.videomanager.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import seifi.de.videomanager.bl.FoldersHandler;
import seifi.de.videomanager.bl.PathSubtitlesHandler;
import seifi.de.videomanager.bl.TabsHandler;
import seifi.de.videomanager.helper.FileData;
import seifi.de.videomanager.helper.FileHelper;
import seifi.de.videomanager.helper.SubtitleData;
import seifi.de.videomanager.helper.SubtitleZipfileDownloader;
import seifi.de.videomanager.models.FolderModel;
import seifi.de.videomanager.models.PathSubtitleModel;
import seifi.de.videomanager.process.IVideoProcess;
import seifi.de.videomanager.process.MkvProcess;
import seifi.de.videomanager.process.ProcessInfo;
import seifi.de.videomanager.process.VideoProcessManager;



@Controller
public class MainController {

  private final Logger logger = LoggerFactory.getLogger(MainController.class);

  
  final private VideoProcessManager videoProcessManager;
  
  final private TabsHandler tabsHandler;
  
  final private FoldersHandler foldersHandler;
  
  final private PathSubtitlesHandler pathSubtitlesHandler;

  @Autowired
  public MainController(VideoProcessManager videoProcessManager, TabsHandler tabsHandler, FoldersHandler foldersHandler,
      PathSubtitlesHandler pathSubtitlesHandler) {
    this.tabsHandler = tabsHandler;
    this.videoProcessManager = videoProcessManager;
    this.foldersHandler = foldersHandler;
    this.pathSubtitlesHandler = pathSubtitlesHandler;
    
  }
  
  @GetMapping("/")
  public String index(final Model model) {
    //logger.debug("test");
    
    model.addAttribute("tabs", tabsHandler.listTabs());
    return "index";
  }
  
  
  @GetMapping("/general")
  public String showGeneralTab(final Model model){
    
    model.addAttribute("folders", foldersHandler.listFolders());
      
    return "general"; 
  }
  
  @GetMapping("/process")
  public String ProcessList(final Model model){
         
    return "process";     
  }
  
  @GetMapping("/tools")
  public String toolsList(final Model model){
    
    
    return "tools"; 
  }
  
  @GetMapping("/folderlist")
  @ResponseBody
  public List<FolderModel> folderList(final Model model){

    return foldersHandler.listFolders(); 
  }
  
  @GetMapping(value="/filelist/{id}", produces={"application/json"})
  @ResponseBody
  public List<FileData> fileList(@PathVariable("id") int id){
    
    List<FileData> files = new ArrayList<FileData>();
    
    FolderModel folder = foldersHandler.readFolder(id);
    if(folder != null) {
      files.addAll(FileHelper.getAllMediaFiles(folder.getPath(), foldersHandler, pathSubtitlesHandler));
    }
    
        return files; 
  }
  
  @GetMapping(value="/sublist/{id}", produces={"application/json"})
  @ResponseBody
  public List<SubtitleData> subtitlesList(@PathVariable("id") int id){
    
    List<SubtitleData> files = new ArrayList<SubtitleData>();
    
    FolderModel folder = foldersHandler.readFolder(id);
    if(folder != null) {
      files.addAll(FileHelper.getAllSubtitleFiles(folder.getPath()));
    }

        return files; 
  }

  @RequestMapping(value = "/addsubfromzip", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, String> addSubtitleZip(@RequestParam("path64") String path64, @RequestParam("subpath64") String subpath64, 
      @RequestParam("zipurl") String zipurl){
    
    
    byte[] realpathdata = Base64.getDecoder().decode(path64.getBytes());
    String realfilePath = new String(realpathdata);
    realpathdata = Base64.getDecoder().decode(subpath64.getBytes());
    String realsubPath = new String(realpathdata);
    
    String strin64 = "p64= " + path64 + " , sp64= " + subpath64 + " , zip= " + zipurl;
    String strin = "p= " + realfilePath + " , sp= " + realsubPath + " , zip= " + zipurl;

    boolean res = SubtitleZipfileDownloader.DownloadSrtZip(realsubPath, zipurl);
    
    Map<String, String> list = new HashMap<String, String>();
    list.put("strin64", strin64);
    list.put("strin", strin);
    list.put("result", res ? "ok" : "error");
    
    return list; 
  }

  @RequestMapping(value = "/addsubproc", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, String> addSubtitleProcess(@RequestParam("path64") String path64, @RequestParam("lang") String language){
    
    
    byte[] realpathdata = Base64.getDecoder().decode(path64.getBytes());
    String realfilePath = new String(realpathdata);

    Map<String, String> list = new HashMap<String, String>();
    
    MkvProcess proc = (MkvProcess)videoProcessManager.add(new MkvProcess(new FileData(realfilePath, foldersHandler, pathSubtitlesHandler), language));
    if(proc != null) {
      list.put("outpath", proc.getOutputPath());
    }
    else
    {
      list.put("outpath", "");
    }
    
    list.put("result", proc != null ? "ok" : "exists");
    list.put("count", "" + videoProcessManager.size());
    
    return list; 
  }

  @RequestMapping(value="/proclist", produces={"application/json"})
  @ResponseBody
  public List<ProcessInfo> processList(){
    
    List<ProcessInfo> list = new ArrayList<ProcessInfo>();
    
    for(int i=0; i<videoProcessManager.size(); i++) {
      list.add(videoProcessManager.get(i).getInfo());
    }
        
        return list; 
  }

  @RequestMapping(value = "/changeproc", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> toggleProcess(@RequestParam("path64") String path64, @RequestParam("start") int start){
    
    
    byte[] realpathdata = Base64.getDecoder().decode(path64.getBytes());
    String realfilePath = new String(realpathdata);

    Map<String, Object> list = new HashMap<String, Object>();
    
    IVideoProcess proc = videoProcessManager.processByOutput(realfilePath);
    if(proc != null) {
      
      if(start == 1) {
        proc.start();
        list.put("act", "start");
      }
      if(start == 2) {
        proc.stop();
        list.put("act", "stop");
      }
      if(start == 3) {
        if(proc.isRunning())
        {
          list.put("act", "running");
        }
        else {
          
          int size = videoProcessManager.size();
          videoProcessManager.removeByOutput(realfilePath);
          list.put("act", size > videoProcessManager.size() ? "del" : "nodel");
        }
        
        
      }
      
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        
      }
      list.put("info", proc.getInfo());

    }
    else
    {
      list.put("outpath", "");
    }
    
    list.put("found", proc != null ? "ok" : "no");
    
    return list; 
  }
  
  @RequestMapping(value="/checkproc/{out64}/{ticks}", produces={"application/json"})
  @ResponseBody
  public Map<String, Object> checkProcess(@PathVariable("out64") String path64){
    
    byte[] realpathdata = Base64.getDecoder().decode(path64.getBytes());
    String realfilePath = new String(realpathdata);

    Map<String, Object> list = new HashMap<String, Object>();
    
    IVideoProcess proc = videoProcessManager.processByOutput(realfilePath);
    if(proc != null) {
      list.put("info", proc.getInfo());
    }
    else
    {
      list.put("outpath", "");
    }
    
    list.put("found", proc != null ? "ok" : "no");
    
    return list; 
  }
  
  @RequestMapping(value = "/conversubtitle", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, String> convertSubtitle(@RequestParam("path64") String path64, @RequestParam("conv") String conv){
    
    Map<String, String> list = new HashMap<String, String>();
    
    //list.put("result", "ok");
    
    byte[] realpathdata = Base64.getDecoder().decode(path64.getBytes());
    String realfilePath = new String(realpathdata);
    
    File f = new File(realfilePath);
    if(!f.exists()) {
      list.put("found", "no");
      list.put("result", "Error: No File");
    }
    else {
      list.put("found", "ok");
      if(FileData.getFileExtention(realfilePath).toLowerCase().equals("srt")) {
        list.put("issrt", "ok");
        
        try {
          
          Path textFile = Paths.get(realfilePath);
          
          if(conv.equals("ascii")) {
            
            List<String> lines = Files.readAllLines(textFile, Charset.forName("windows-1256"));
            Files.write(textFile, lines, StandardCharsets.UTF_8);
            list.put("result", "ok");
          }
          
          if(conv.equals("unicode")) {
            
            List<String> lines = Files.readAllLines(textFile, StandardCharsets.UTF_16);
            Files.write(textFile, lines, StandardCharsets.UTF_8);
            list.put("result", "ok");
          }
          


        } 
        catch(IOException ex) {
          
          list.put("result", "Error: " + ex.getMessage());
        }
      }
      else {
        list.put("issrt", "no");
        list.put("result", "Error: No Srt");
      }
    }
    
    
    return list; 
  }

  
  @RequestMapping(value = "/subtitleaddsecs", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, String> addSubtitleSeconds(@RequestParam("path64") String path64, @RequestParam("secs") int seconds){
    
    Map<String, String> list = new HashMap<String, String>();
    list.put("found", "no");
    list.put("result", "Error: No File");

    /*byte[] realpathdata = Base64.getDecoder().decode(path64.getBytes());
    String realfilePath = new String(realpathdata);
    
    File f = new File(realfilePath);
    if(!f.exists()) {
      list.put("found", "no");
      list.put("result", "Error: No File");
    }
    else {
      list.put("found", "ok");
      if(FileData.getFileExtention(realfilePath).toLowerCase().equals("srt")) {
        list.put("issrt", "ok");
        
        if(seconds == 0) {
          list.put("result", "zero seconds");
        }
        else {
          SrtReader reader = new SrtReader(realfilePath);
          //reader.addSeconds(seconds);
          //reader.save();
          list.put("path", realfilePath);
          list.put("items", reader.itemList.size() + "");
          list.put("error", reader.lastError);
          list.put("result", "ok");
        }
          
      }
      else {
        list.put("issrt", "no");
        list.put("result", "Error: No Srt");
      }
    }*/
    
    
    return list; 
  }
  

  @RequestMapping(value="/pathsublist", produces={"application/json"})
  @ResponseBody
  public List<PathSubtitleModel> pathsubList(){
      
    List<PathSubtitleModel> res = new ArrayList<PathSubtitleModel>();
    List<PathSubtitleModel> list = pathSubtitlesHandler.listPathSubtitles(); 
    res.addAll(list);
    
    return res; 
  }

  @RequestMapping(value = "/addpathsub", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> addPathSubtitle(@RequestParam("path") String path, @RequestParam("suburl") String suburl){
    
    
    boolean res = pathSubtitlesHandler.savePathSubtitles(new PathSubtitleModel(-1, path, suburl));
    
    Map<String, Object> list = new HashMap<String, Object>();
    list.put("result", res ? "ok" : "failed");
    list.put("res", res);
    
    
    return list; 
  }

  @RequestMapping(value = "/delpathsub", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> addPathSubtitle(@RequestParam("psid") int psid){

    boolean res = pathSubtitlesHandler.deletePathSubtitle(psid);
    
    Map<String, Object> list = new HashMap<String, Object>();
    list.put("result", res ? "ok" : "failed");
    
    
    return list; 
  }
}
