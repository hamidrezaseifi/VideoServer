package seifi.de.videomanager.helper;

import java.io.File;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import seifi.de.videomanager.bl.FoldersHandler;
import seifi.de.videomanager.bl.PathSubtitlesHandler;
import seifi.de.videomanager.models.PathSubtitleModel;



public class FileData {

	public static String OutputExtentionTeil = "_out";
	
	public String Path;
	public String OutputPath;
	public String Base64Path;
	public String FolderPath;
	public String SubtitlePath;
	public String Base64SubtitlePath;
	public String SubtitleUrl;
	public String Name;
	public boolean hasSubtitle;
	public boolean isMedia;
	public boolean isInProcess;
	public boolean isInConvertProcess;
	public boolean isInSubtitleProcess;
	public boolean isConverted;
	public boolean hasConverted;
	
	public String MkvMergPath;
	
  
  @Autowired
  private final FoldersHandler foldersHandler;
  
  @Autowired
  private final PathSubtitlesHandler pathSubtitlesHandler;

	public static boolean isFileMedia(String path) {
		List<String> mediaExtentions = Arrays.asList(new String[]{"mpg", "mp4", "mkv", "avi"});
		String extension = getFileExtention(path);
		
		return mediaExtentions.contains(extension);
	}
	
	public static boolean isFileSubtitle(String path) {
		
		return getFileExtention(path).toLowerCase().equals("srt");
	}
	
	public static String getFileExtention(String path) {
		
		String extension = "";

		int i = path.lastIndexOf('.');
		if (i > 0) {
		    extension = path.substring(i+1);
		    
		}

		return extension;
	}
	
	public FileData(String path, final FoldersHandler foldersHandler, final PathSubtitlesHandler pathSubtitlesHandler) {

	  this.foldersHandler = foldersHandler;
	  this.pathSubtitlesHandler = pathSubtitlesHandler;
    
	  MkvMergPath = this.getClass().getClassLoader().getResource("static/mkv/mkvmerge.exe").getPath();
	  
		
		
		File file = new File(path);
		
		Name = file.getName();
		FolderPath = file.getParent();
		Path = path;
		isMedia = isFileMedia(path);
		
		hasSubtitle = false;
		SubtitlePath = "";
		String extension = getFileExtention(path);
		if(extension.length() > 0) {
			SubtitlePath = path.substring(0, path.length() - 4) + ".srt";
			hasSubtitle = (new File(SubtitlePath)).exists();
			
			OutputPath = path.substring(0, path.length() - 4) + OutputExtentionTeil + "." + extension;
		}
		
		isConverted = Name.indexOf(OutputExtentionTeil) > 0;
		hasConverted = new File(OutputPath).exists();
		
		byte[] bytesEncoded = Base64.getEncoder().encode(Path.getBytes());
		Base64Path = new String(bytesEncoded);
		
		bytesEncoded = Base64.getEncoder().encode(SubtitlePath.getBytes());
		Base64SubtitlePath = new String(bytesEncoded);
		
		SubtitleUrl = "https://subscene.com/subtitles/release?q=" + Name;
						
		PathSubtitleModel subpath = pathSubtitlesHandler.getPathSubtitlesFromPath(FolderPath);
		
		if(subpath != null) {
			SubtitleUrl = subpath.getSuburl();
		}
		
		isInProcess = false;
		isInConvertProcess = false;
		isInSubtitleProcess = false;
	}
	
	public String getBase64OutputFilePath() {
		byte[] bytesEncoded = Base64.getEncoder().encode(OutputPath.getBytes());
		return new String(bytesEncoded);

	}
}
