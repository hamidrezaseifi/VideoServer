package seifi.de.videomanager.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import seifi.de.videomanager.bl.FileDataService;

public class FileHelper {

  public static List<FileData> getAllMediaFiles(final String path, final FileDataService fileDataService) {
    final List<FileData> files = new ArrayList<FileData>();

    final File folder = new File(path);
    final File[] listOfFiles = folder.listFiles();

    for (final File file : listOfFiles) {
      if (file.isFile()) {
        final FileData filedata = fileDataService.createFileData(file.getAbsolutePath());
        if (filedata.getIsMedia()) {
          files.add(filedata);
        }

      }
      else if (file.isDirectory()) {
        files.addAll(getAllMediaFiles(file.getAbsolutePath(), fileDataService));
      }
    }

    return files;
  }

  public static List<SubtitleData> getAllSubtitleFiles(final String path) {
    final List<SubtitleData> files = new ArrayList<SubtitleData>();

    final File folder = new File(path);
    final File[] listOfFiles = folder.listFiles();

    for (int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isFile()) {
        final SubtitleData filedata = new SubtitleData(listOfFiles[i].getAbsolutePath());
        if (filedata.isSubtitle) {
          files.add(filedata);
        }

      }
      else if (listOfFiles[i].isDirectory()) {
        files.addAll(getAllSubtitleFiles(listOfFiles[i].getAbsolutePath()));
      }
    }

    return files;
  }
}
