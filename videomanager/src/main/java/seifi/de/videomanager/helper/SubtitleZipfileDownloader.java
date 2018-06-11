package seifi.de.videomanager.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class SubtitleZipfileDownloader {
	
	public static boolean DownloadSrtZip(String subtitlefilename, String urlString) {
		
		String zipfile = subtitlefilename;
		zipfile = zipfile.toLowerCase().replace(".srt", ".zip");
		if(!saveUrl(zipfile, urlString)) {
			return false;
		}
		
		return unZipIt(zipfile, subtitlefilename);
	}
	
	public static boolean saveUrl(final String filename, final String urlString){
	    InputStream in = null;
	    FileOutputStream fout = null;
	    try {
	        fout = new FileOutputStream(filename);
	        
	        HttpURLConnection httpcon = (HttpURLConnection) new URL(urlString).openConnection();
	        httpcon.addRequestProperty("User-Agent", "Mozilla/4.0");

	        in = httpcon.getInputStream();
	      
	        //in = new BufferedInputStream(new URL(urlString).openStream());

	        final byte data[] = new byte[1024];
	        int count;
	        while ((count = in.read(data, 0, 1024)) != -1) {
	            fout.write(data, 0, count);
	        }
	        
	        if (in != null) {
	            in.close();
	        }
	        if (fout != null) {
	            fout.close();
	        }
	        
	        return true;
	        
	    } 
	    catch(IOException ex)
        {
	    	System.out.println("Error : " + ex.getMessage());
        }
	    finally {
	        
	    }
	    
	    return false;
	}
	

	public static boolean unZipIt(String zipFile, String outputFile){

	     byte[] buffer = new byte[1024];

	     try{

	    	//create output directory is not exists
	    	

	    	FileInputStream fs = new FileInputStream(zipFile);
	    	ZipInputStream zis = new ZipInputStream(fs);
	    	
	    	ZipEntry ze = zis.getNextEntry();

	    	while(ze!=null){

	    	   String fileName = ze.getName();
	    	   if(fileName.toLowerCase().endsWith(".srt")) {
	    		   File newFile = new File(outputFile);

		            //create all non exists folders
		            //else you will hit FileNotFoundException for compressed folder
		            new File(newFile.getParent()).mkdirs();

		            FileOutputStream fos = new FileOutputStream(newFile);

		            int len;
		            while ((len = zis.read(buffer)) > 0) {
		       		fos.write(buffer, 0, len);
		            }

		            fos.close();
		            ze = zis.getNextEntry();
		            break;
	    	   }
	           


	    	}

	        zis.closeEntry();
	    	zis.close();

	    	File f = new File(zipFile);
	    	f.delete();
	    	return true;

	    }catch(IOException ex){
	       ex.printStackTrace();
	    }
	     
	     return false;
	   }
}
