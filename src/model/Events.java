package model;

public class Events {
	private String endString;
	private String startString;
	private int startTime;
	private int endTime;
	private String location;
	private String name;
	private int date;
	
	public Events(){
		this("name",0,0,"Location",0);
	}
	public Events(int s, int e){
		this("name",s,e,"Location",0);
	}
	public Events(int s, int e, int d){
		this("name",s,e,"Location",d);
	}
	public Events(String n,int s, int e, String l, int d){
		name = n;
		startTime=s;startString=""+s;
		endTime=s;endString=""+e;
		location = l;
		date = d;
	}
	public Events(String n,String s, String e, String l, int d){
		name = n;
		startTime=Integer.parseInt(s);startString=""+s;
		endTime=Integer.parseInt(e);endString=""+e;
		location = l;
		date = d;
	}
	public void setTime(String time){
		startString=""+time.charAt(0)+time.charAt(1)+time.charAt(2)+time.charAt(3);
		endString=""+time.charAt(4)+time.charAt(5)+time.charAt(6)+time.charAt(7);
		startTime= Integer.parseInt(""+time.charAt(0)+time.charAt(1)+time.charAt(2)+time.charAt(3));
		endTime= Integer.parseInt(""+time.charAt(4)+time.charAt(5)+time.charAt(6)+time.charAt(7));
	}
	public void setLocation(String l){
		location=l;
	}
	public void setName(String n){
		name = n;
	}
	public void setDate(String i){
		date = Integer.parseInt(i);
	}
	public int getStart(){
		return startTime;
	}
	public int getEnd(){
		return endTime;
	}
	public String getStartString(){
		return startString;
	}
	public String getEndString(){
		return endString;
	}
	public String getTime(){
		return ""+startTime+""+endTime;
	}
	public String getLocation(){
		return location;
	}
	public String getName(){
		return name;
	}
	public int getDate(){
		return date;
	}
	public String stringDate(){
		switch(date){
		case(0):return "Sunday";
		case(1):return "Monday";
		case(2):return "Tuesday";
		case(3):return "Wednesday";
		case(4):return "Thursday";
		case(5):return "Friday";
		case(6):return "Satday";
		default:return "Error";
		}
	}
	@Override
	public String toString(){
		String s="";
		s+="Name: "+name+"\n";
		s+="Start Time: "+startTime+"\n";
		s+="End Time "+endTime+"\n";
		s+="Location: "+location+"\n";
		s+="Date: "+stringDate()+"\n";
		return s;
	}
}
