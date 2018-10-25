package seifi.de.videomanager.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import seifi.de.videomanager.helper.FileData;

public class MkvProcess implements Runnable, IVideoProcess {
  
  private Process            pMainProcess;
  private final List<String> command;
  private final List<String> statusList;
  private final List<String> errorList;
  private final FileData     fileData;
  private ProcessState       state;
  private int                lastPercent;
  private String             lastPercentString;
  
  public MkvProcess(final FileData filedata, final String lang) {
    
    this.fileData = filedata;
    this.command = new ArrayList<String>();
    this.statusList = new ArrayList<String>();
    this.errorList = new ArrayList<String>();
    this.state = ProcessState.Idle;
    
    this.command.add("\"C:\\Program Files\\MKVToolNix\\mkvmerge\"");
    this.command.add("-o");
    this.command.add(this.fileData.getOutputPath());
    this.command.add(this.fileData.getPath());
    this.command.add("--language");
    this.command.add("\"0:" + lang + "\"");
    this.command.add(this.fileData.getSubtitlePath());
    
    this.lastPercent = 0;
    this.lastPercentString = "0";
    this.pMainProcess = null;
  }
  
  @Override
  public void run() {
    
    final ProcessBuilder builder = new ProcessBuilder(this.command);
    
    try {
      this.pMainProcess = builder.start();
      this.state = ProcessState.Running;
      
      final InputStream is = this.pMainProcess.getInputStream();
      final InputStreamReader isr = new InputStreamReader(is);
      final BufferedReader br = new BufferedReader(isr);
      String line;
      while ((line = br.readLine()) != null) {
        this.statusList.add(line);
        line = line.trim();
        if (line.startsWith("Progress")) {
          
          final String s = line.replace("Progress", "").replace(":", "").replace("%", "").trim();
          
          this.lastPercentString = s;
          try {
            this.lastPercent = Integer.parseInt(s);
          }
          catch (final Exception ex) {
            this.lastPercentString = line + " : " + s + " : err: " + ex.getMessage();
          }
          
        }
      }
      this.state = ProcessState.Finished;
      
    }
    catch (final IOException e) {
      // e.printStackTrace();
      this.state = ProcessState.Error;
      this.errorList.add(e.getMessage());
    }
    
  }
  
  @Override
  public void start() {
    final Thread t = new Thread(this);
    t.start();
  }
  
  @Override
  public void stop() {
    
    if (this.pMainProcess != null) {
      this.pMainProcess.destroy();
    }
    
  }
  
  @Override
  public String getOutputPath() {
    return this.fileData.getOutputPath();
  }
  
  @Override
  public String getInputPath() {
    return this.fileData.getPath();
  }
  
  @Override
  public Process getProcess() {
    return this.pMainProcess;
  }
  
  @Override
  public ProcessState getState() {
    return this.state;
  }
  
  @Override
  public FileData getFileData() {
    return this.fileData;
  }
  
  @Override
  public String getProcessType() {
    return "MKV";
  }
  
  @Override
  public ProcessInfo getInfo() {
    final ProcessInfo info = new ProcessInfo(this);
    
    return info;
  }
  
  @Override
  public boolean isRunning() {
    
    return this.state == ProcessState.Running;
  }
  
  @Override
  public int getPercent() {
    return this.lastPercent;
  }
  
  @Override
  public String getPercentString() {
    return this.lastPercentString;
  }
}
