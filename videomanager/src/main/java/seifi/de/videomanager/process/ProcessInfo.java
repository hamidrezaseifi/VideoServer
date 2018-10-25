package seifi.de.videomanager.process;

public class ProcessInfo {
  
  private final String  outputFileName;
  private final String  outputFilePath;
  private final String  base64OutputFilePath;
  private final String  folderPath;
  private final String  processType;
  private final String  status;
  private final String  percentString;
  private final int     percent;
  private final boolean isRunning;
  private final boolean isFinished;
  
  public ProcessInfo(final IVideoProcess proc) {
    
    this.outputFileName = proc.getFileData().getName();
    this.outputFilePath = proc.getOutputPath();
    this.base64OutputFilePath = proc.getFileData().getBase64OutputFilePath();
    this.folderPath = proc.getFileData().getFolderPath();
    this.processType = proc.getProcessType();
    this.status = proc.getState().name();
    this.percent = proc.getPercent();
    this.percentString = proc.getPercentString();
    
    this.isRunning = proc.isRunning();
    this.isFinished = proc.getState() == ProcessState.Finished;
    
  }
  
  public String getOutputFileName() {
    return this.outputFileName;
  }
  
  public String getOutputFilePath() {
    return this.outputFilePath;
  }
  
  public String getBase64OutputFilePath() {
    return this.base64OutputFilePath;
  }
  
  public String getFolderPath() {
    return this.folderPath;
  }
  
  public String getProcessType() {
    return this.processType;
  }
  
  public String getStatus() {
    return this.status;
  }
  
  public String getPercentString() {
    return this.percentString;
  }
  
  public int getPercent() {
    return this.percent;
  }
  
  public boolean getIsRunning() {
    return this.isRunning;
  }
  
  public boolean getIsFinished() {
    return this.isFinished;
  }
  
}
