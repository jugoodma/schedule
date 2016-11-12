package control;
import java.util.ArrayList;

import model.IOStream;
import model.Schedule;

public class Control {
	IOStream input;
	TimeCheck tc;
	ArrayList<String> IDs;
	public Control(){
		input = new IOStream();
		IDs=new ArrayList<String>();
		tc = new TimeCheck();
		//IDs.add("1001");
		//IDs.add("1000");
		IDs.add("1003");
	}
	public Schedule getSchedule(int ID){
		return input.loadSchedule(ID);
	}
	public void saveSchedule(Schedule s){
		input.saveSchedule(s);
	}
	public ArrayList<String> getIDs(){
		return IDs;
	}
	public IOStream getIOS(){
		return input;
	}
}
