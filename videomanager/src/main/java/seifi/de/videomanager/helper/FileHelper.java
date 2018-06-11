package seifi.de.videomanager.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import seifi.de.videomanager.bl.FoldersHandler;
import seifi.de.videomanager.bl.PathSubtitlesHandler;

public class FileHelper {

	public static List<FileData> getAllMediaFiles(String path, final FoldersHandler foldersHandler, final PathSubtitlesHandler pathSubtitlesHandler){
		List<FileData> files = new ArrayList<FileData>();
		
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				FileData filedata = new FileData(listOfFiles[i].getAbsolutePath(), foldersHandler, pathSubtitlesHandler);
				if(filedata.isMedia) {
					files.add(filedata);
				}
				
			} else if (listOfFiles[i].isDirectory()) {
				files.addAll(getAllMediaFiles(listOfFiles[i].getAbsolutePath(), foldersHandler, pathSubtitlesHandler));
			}
		}
		    
		return files;
	}

	public static List<SubtitleData> getAllSubtitleFiles(String path){
		List<SubtitleData> files = new ArrayList<SubtitleData>();
		
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				SubtitleData filedata = new SubtitleData(listOfFiles[i].getAbsolutePath());
				if(filedata.isSubtitle) {
					files.add(filedata);
				}
				
			} else if (listOfFiles[i].isDirectory()) {
				files.addAll(getAllSubtitleFiles(listOfFiles[i].getAbsolutePath()));
			}
		}
		    
		return files;
	}
}
