package seifi.de.videomanager.bl;

import java.io.File;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import seifi.de.videomanager.helper.FileData;
import seifi.de.videomanager.models.PathSubtitleModel;

@Service
public class FileDataService {

  @Autowired
  private final FoldersHandler foldersHandler;

  @Autowired
  private final PathSubtitlesHandler pathSubtitlesHandler;

  @Autowired
  private final SettingsHandler settingsHandler;
  
  public FileDataService(final FoldersHandler foldersHandler, final PathSubtitlesHandler pathSubtitlesHandler,
      final SettingsHandler settingsHandler) {
    
    this.foldersHandler = foldersHandler;
    this.pathSubtitlesHandler = pathSubtitlesHandler;
    this.settingsHandler = settingsHandler;

  }
  
  public boolean isFileMedia(final String path) {
    final List<String> mediaExtentions = Arrays.asList(new String[] {
        "mpg",
        "mp4",
        "mkv",
        "avi" });
    final String extension = this.getFileExtention(path);
    
    return mediaExtentions.contains(extension);
  }
  
  public boolean isFileSubtitle(final String path) {
    
    return this.getFileExtention(path).toLowerCase().equals("srt");
  }
  
  public String getFileExtention(final String path) {
    
    String extension = "";
    
    final int i = path.lastIndexOf('.');
    if (i > 0) {
      extension = path.substring(i + 1);
      
    }
    
    return extension;
  }
  
  public FileData createFileData(final String path) {

    final FileData fileData = new FileData();

    fileData.setMkvMergPath(this.getClass().getClassLoader().getResource("static/mkv/mkvmerge.exe").getPath());

    final File file = new File(path);

    fileData.setName(file.getName());
    fileData.setFolderPath(file.getParent());
    fileData.setPath(path);
    fileData.setIsMedia(this.isFileMedia(path));

    fileData.setHasSubtitle(false);
    fileData.setSubtitlePath("");
    final String extension = this.getFileExtention(path);
    if (extension.length() > 0) {
      fileData.setSubtitlePath(path.substring(0, path.length() - 4) + ".srt");
      fileData.setHasSubtitle((new File(fileData.getSubtitlePath())).exists());

      fileData.setOutputPath(path.substring(0, path.length() - 4) + FileData.OutputExtentionTeil + "." + extension);
    }

    fileData.setIsConverted(fileData.getName().indexOf(FileData.OutputExtentionTeil) > 0);
    fileData.setHasConverted(new File(fileData.getOutputPath()).exists());

    byte[] bytesEncoded = Base64.getEncoder().encode(fileData.getPath().getBytes());
    fileData.setBase64Path(new String(bytesEncoded));

    bytesEncoded = Base64.getEncoder().encode(fileData.getSubtitlePath().getBytes());
    fileData.setBase64SubtitlePath(new String(bytesEncoded));

    fileData.setSubtitleUrl(this.settingsHandler.subsceneSearchPath().replace("%1", fileData.getName()));

    final PathSubtitleModel subpath = this.pathSubtitlesHandler.getPathSubtitlesFromPath(fileData.getFolderPath());

    if (subpath != null) {
      fileData.setSubtitleUrl(subpath.getSuburl());
    }

    fileData.setIsInProcess(false);
    fileData.setIsInConvertProcess(false);
    fileData.setIsInSubtitleProcess(false);

    fileData.setBase64OutputFilePath(new String(Base64.getEncoder().encode(fileData.getOutputPath().getBytes())));

    return fileData;
  }
  
}
