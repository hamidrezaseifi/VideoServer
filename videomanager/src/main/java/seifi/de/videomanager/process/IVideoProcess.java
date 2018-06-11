package seifi.de.videomanager.process;

import seifi.de.videomanager.helper.FileData;

public interface IVideoProcess {

	public FileData getFileData();
	public String getOutputPath();
	public String getInputPath();
	public Process getProcess();
	public ProcessState getState();
	public String getProcessType();
	public int getPercent();
	public String getPercentString();
	public ProcessInfo getInfo();
	public boolean isRunning();
	public void start();
	public void stop();
	
}
