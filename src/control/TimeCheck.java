package control;

import java.util.ArrayList;

import model.Events;
import model.Schedule;

public class TimeCheck {
	Schedule schedule;
	ArrayList<Integer> free;
	public TimeCheck(){
		schedule=new Schedule();
		free= new ArrayList<Integer>();
	}
	public void add(Schedule other){
		ArrayList<Events>day = new ArrayList<Events>();
		for(int i=0; i<7;i++){
			for(Events e:schedule.getSchedule()){
				if(e.getDate()==i){
					day.add(e);
				}
			}
			for(Events e:other.getSchedule()){
				if(e.getDate()==i){
					day.add(e);
				}
			}
			
			addFree(day,i);
		}
	}
	public void addFree(ArrayList<Events> d, int f){
		ArrayList<Events>day = new ArrayList<Events>();
		ArrayList<Integer>start = new ArrayList<Integer>();
		ArrayList<Integer>end = new ArrayList<Integer>();
		for(Events e:d){
			start.add(e.getStart());
			end.add(e.getEnd());
		}
		int i=0;
		while(day.size()!=d.size()&&i<d.size()-1){
			int s1 = start.get(i);
			int s2 = start.get(i+1);
			int e1=end.get(i);
			int e2=end.get(i+1);
			if(s1==s2&&e1==e2){
				day.add(new Events(s1,e1));
			}else if(s2<=s1&&s1<=e2&&e2<=e1){
				day.add(new Events(s2,e1));
			}else if(s1<=s2&&s2<=e1&&e1<=e2){
				day.add(new Events(s1,e2));
			}else if(s2<=s1&&e2>=e1){
				day.add(new Events(s2,e2));
			}else if(s1<=s2&&e1>=e2){
				day.add(new Events(s1,e1));
			}else if(e1<=s2||s1>=e2){
				day.add(new Events(s1,e1));
				day.add(new Events(s2,e2));
			}
			i=i+2;
		}
		for(Events e:day){
			free.add(f);
			free.add(e.getStart());
			free.add(e.getEnd());
		}
	}
	public ArrayList<Integer> getFree(){
		return free;
	}
}
