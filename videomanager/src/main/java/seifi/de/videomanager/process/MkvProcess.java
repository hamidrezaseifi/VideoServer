package seifi.de.videomanager.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import seifi.de.videomanager.helper.FileData;


public class MkvProcess implements Runnable, IVideoProcess{

	private Process pMainProcess;
	private List<String> command;
	private List<String> statusList;
	private List<String> errorList;
	private FileData fileData;
	private ProcessState state;
	private int lastPercent;
	private String lastPercentString;
	
	
	public MkvProcess(FileData filedata, String lang) {
		
		fileData = filedata;
		command = new ArrayList<String>();
		statusList = new ArrayList<String>();
		errorList = new ArrayList<String>();
		state = ProcessState.Idle;
		
		command.add("\"C:\\Program Files\\MKVToolNix\\mkvmerge\"");
	    command.add("-o");
	    command.add(fileData.OutputPath);
	    command.add(fileData.Path);
	    command.add("--language");
	    command.add("\"0:" + lang + "\"");
	    command.add(fileData.SubtitlePath);
	    
	    lastPercent = 0;
	    lastPercentString = "0";
	    pMainProcess = null;
	}

	public void run() {

		ProcessBuilder builder = new ProcessBuilder(command);
	     	    
		try {
			pMainProcess = builder.start();
			state = ProcessState.Running;
			
			InputStream is = pMainProcess.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null) {
				statusList.add(line);
				line = line.trim();
				if(line.startsWith("Progress")) {
					
					String s = line.replace("Progress", "").replace(":", "").replace("%", "").trim();
					
					lastPercentString = s;
					try {
						lastPercent = Integer.parseInt(s);
					}
					catch(Exception ex){
						lastPercentString = line + " : " + s + " : err: " + ex.getMessage();
					}
					
				}
			}
			state = ProcessState.Finished;
			    
		} catch (IOException e) {
			//e.printStackTrace();
			state = ProcessState.Error;
			errorList.add(e.getMessage());
		}
		
		
	}
	
	public void start() {
		Thread t = new Thread(this);
        t.start();
	}
	
	public void stop() {
		
		if(pMainProcess != null) {
			pMainProcess.destroy();	
		}
		
	}

	public String getOutputPath() {
		return fileData.OutputPath;
	}

	public String getInputPath() {
		return fileData.Path;
	}

	public Process getProcess() {
		return pMainProcess;
	}

	public ProcessState getState() {
		return state;
	}

	public FileData getFileData() {
		return fileData;
	}

	public String getProcessType() {
		return "MKV";
	}

	public ProcessInfo getInfo() {
		ProcessInfo info = new ProcessInfo(this);
		
		return info;
	}

	public boolean isRunning() {
		
		return state == ProcessState.Running;
	}

	public int getPercent() {
		return lastPercent;
	}

	public String getPercentString() {
		return lastPercentString;
	}
}
