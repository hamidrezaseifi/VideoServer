package seifi.de.videomanager.helper;

import java.io.File;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;


public class SubtitleData {

	public String Path;
	public String Base64Path;
	public String FolderPath;
	public String Name;
	public boolean isSubtitle;
	
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
	
	public SubtitleData(String path) {

		
		File file = new File(path);
		
		Name = file.getName();
		FolderPath = file.getParent();
		Path = path;
		
		String extension = getFileExtention(path);
		if(extension.length() > 0) {
			isSubtitle = extension.toLowerCase().equals("srt");
		}
		
		byte[] bytesEncoded = Base64.getEncoder().encode(Path.getBytes());
		Base64Path = new String(bytesEncoded);
		

	}
	
}
