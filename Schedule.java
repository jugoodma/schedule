package model;

import java.util.ArrayList;

public class Schedule {
	private ArrayList<Events> schedule;
	private String name;
	private int ID;
	public Schedule(){
		schedule = new ArrayList<Events>();
	}
	public void setName(String n){
		name = n;
	}
	public void setID(int id){
		ID=id;
	}
	public void addEvent(Events e){
		schedule.add(e);
	}
	public String getName(){
		return name;
	}
	public int getID(){
		return ID;
	}
	public ArrayList<Events> getSchedule(){
		return schedule;
	}
	@Override
	public String toString(){
		String s="";
		for(int i =0; i<schedule.size();i++){
			s+=schedule.get(i).toString();
		}
		return s;
	}
}
