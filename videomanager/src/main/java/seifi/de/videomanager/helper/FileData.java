package seifi.de.videomanager.helper;

public class FileData {
  
  public static String OutputExtentionTeil = "_out";
  
  private String  path;
  private String  outputPath;
  private String  base64Path;
  private String  folderPath;
  private String  subtitlePath;
  private String  base64SubtitlePath;
  private String  subtitleUrl;
  private String  name;
  private boolean hasSubtitle;
  private boolean isMedia;
  private boolean isInProcess;
  private boolean isInConvertProcess;
  private boolean isInSubtitleProcess;
  private boolean isConverted;
  private boolean hasConverted;
  private String  base64OutputFilePath;
  
  private String mkvMergPath;
  
  public FileData() {
    
  }

  public static String getOutputExtentionTeil() {
    return OutputExtentionTeil;
  }
  
  public static void setOutputExtentionTeil(final String outputExtentionTeil) {
    OutputExtentionTeil = outputExtentionTeil;
  }
  
  public String getPath() {
    return this.path;
  }
  
  public void setPath(final String path) {
    this.path = path;
  }
  
  public String getOutputPath() {
    return this.outputPath;
  }
  
  public void setOutputPath(final String outputPath) {
    this.outputPath = outputPath;
  }
  
  public String getBase64Path() {
    return this.base64Path;
  }
  
  public void setBase64Path(final String base64Path) {
    this.base64Path = base64Path;
  }
  
  public String getFolderPath() {
    return this.folderPath;
  }
  
  public void setFolderPath(final String folderPath) {
    this.folderPath = folderPath;
  }
  
  public String getSubtitlePath() {
    return this.subtitlePath;
  }
  
  public void setSubtitlePath(final String subtitlePath) {
    this.subtitlePath = subtitlePath;
  }
  
  public String getBase64SubtitlePath() {
    return this.base64SubtitlePath;
  }
  
  public void setBase64SubtitlePath(final String base64SubtitlePath) {
    this.base64SubtitlePath = base64SubtitlePath;
  }
  
  public String getSubtitleUrl() {
    return this.subtitleUrl;
  }
  
  public void setSubtitleUrl(final String subtitleUrl) {
    this.subtitleUrl = subtitleUrl;
  }
  
  public String getName() {
    return this.name;
  }
  
  public void setName(final String name) {
    this.name = name;
  }
  
  public boolean getHasSubtitle() {
    return this.hasSubtitle;
  }
  
  public void setHasSubtitle(final boolean hasSubtitle) {
    this.hasSubtitle = hasSubtitle;
  }
  
  public boolean getIsMedia() {
    return this.isMedia;
  }
  
  public void setIsMedia(final boolean isMedia) {
    this.isMedia = isMedia;
  }
  
  public boolean getIsInProcess() {
    return this.isInProcess;
  }
  
  public void setIsInProcess(final boolean isInProcess) {
    this.isInProcess = isInProcess;
  }
  
  public boolean getIsInConvertProcess() {
    return this.isInConvertProcess;
  }
  
  public void setIsInConvertProcess(final boolean isInConvertProcess) {
    this.isInConvertProcess = isInConvertProcess;
  }
  
  public boolean getIsInSubtitleProcess() {
    return this.isInSubtitleProcess;
  }
  
  public void setIsInSubtitleProcess(final boolean isInSubtitleProcess) {
    this.isInSubtitleProcess = isInSubtitleProcess;
  }
  
  public boolean getIsConverted() {
    return this.isConverted;
  }
  
  public void setIsConverted(final boolean isConverted) {
    this.isConverted = isConverted;
  }
  
  public boolean getHasConverted() {
    return this.hasConverted;
  }
  
  public void setHasConverted(final boolean hasConverted) {
    this.hasConverted = hasConverted;
  }
  
  public String getMkvMergPath() {
    return this.mkvMergPath;
  }
  
  public void setMkvMergPath(final String mkvMergPath) {
    this.mkvMergPath = mkvMergPath;
  }
  
  public String getBase64OutputFilePath() {
    return this.base64OutputFilePath;
  }
  
  public void setBase64OutputFilePath(final String base64OutputFilePath) {
    this.base64OutputFilePath = base64OutputFilePath;
  }
  
}
