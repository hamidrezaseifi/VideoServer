package seifi.de.videomanager.process;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class VideoProcessManager   {

	private List<IVideoProcess> processList;
	
	public VideoProcessManager() {
		
		processList = new ArrayList<IVideoProcess>();
		
	}
	
	public IVideoProcess add(IVideoProcess videoProcess) {
		if(!processByOutputExists(videoProcess.getOutputPath())) {
			processList.add(videoProcess);
			return videoProcess;
		}
		return null;
	}
	
	public IVideoProcess removeByOutput(String output) {
		int idx = processIndexByOutput(output);
		if(idx > -1) {
			IVideoProcess p = processList.get(idx);
			processList.remove(idx);
			return p;
		}
		return null;
	}

	public IVideoProcess processByOutput(String output) {
		for(IVideoProcess p:processList) {
			
			if(p.getOutputPath().toLowerCase().equals(output.toLowerCase())) {
				return p;
			}
		}
		return null;
	}

	public int processIndexByOutput(String output) {
		for(int i=0; i<processList.size(); i++) {
			IVideoProcess p = processList.get(i);
			if(p.getOutputPath().toLowerCase().equals(output.toLowerCase())) {
				return i;
			}
		}
		return -1;
	}

	public boolean processByOutputExists(String output) {
		
		return processByOutput(output) != null;
	}

	public boolean startProcessByOutput(String output) {
		
		IVideoProcess p = processByOutput(output);
		if(p!= null) {
			p.start();
			return true;
		}
		return false;
	}

	public boolean stopProcessByOutput(String output) {
		
		IVideoProcess p = processByOutput(output);
		if(p!= null) {
			p.stop();
			return true;
		}
		return false;
	}

	public int size() {
		return processList.size();
	}

	public IVideoProcess get(int index) {
		return processList.get(index);
	}
	
}
