package control;

import view.TerminalView;

public class Schedulerunner {
	public static void main(String[] args){
		Control c= new Control();
		new TerminalView(c);
	}
}
