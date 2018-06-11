package seifi.de.videomanager.process;

public class ProcessInfo {
	
	public String OutputFileName;
	public String OutputFilePath;
	public String Base64OutputFilePath;
	public String FolderPath;
	public String ProcessType;
	public String Status;
	public String PercentString;
	public int Percent;
	public boolean isRunning;
	public boolean isFinished;

	public ProcessInfo(IVideoProcess proc) {
		
		OutputFileName = proc.getFileData().Name;
		OutputFilePath = proc.getOutputPath();
		Base64OutputFilePath = proc.getFileData().getBase64OutputFilePath();
		FolderPath = proc.getFileData().FolderPath;
		ProcessType = proc.getProcessType();
		Status = proc.getState().name();
		Percent = proc.getPercent();
		PercentString = proc.getPercentString();

		isRunning = proc.isRunning();
		isFinished = proc.getState() == ProcessState.Finished;
		
	}

}
