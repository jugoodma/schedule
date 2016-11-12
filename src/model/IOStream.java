package model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class IOStream {
	public Schedule loadSchedule(int ID){
		Schedule s= new Schedule();
        try
        {
        	FileReader fileReader = new FileReader(ID+".txt");
        	String line=null;
            BufferedReader br = new BufferedReader(fileReader);
        	s.setID(ID);
        	line = br.readLine();
        	s.setName(line);
            while((line = br.readLine())!=null){
            	Events e = new Events();
            	e.setName(line);
            	line = br.readLine();
            	e.setTime(line);
            	line = br.readLine();
            	e.setDate(line);
            	line = br.readLine();
            	e.setLocation(line);
            	s.addEvent(e);
            }
            br.close();
        }
        catch(FileNotFoundException ex)
        {
        	System.out.println(ex.getMessage());
        	System.out.println("not found");
           System.exit(0);
        } catch (IOException e) {

		}
        catch(Exception e){
        	
        }
        return s;
    }
	public void saveSchedule(Schedule s){
		try{
		    PrintWriter fout = new PrintWriter(s.getID()+".txt", "UTF-8");
		    fout.println(s.getName());
		    for(int i =0; i<s.getSchedule().size();i++){
				fout.println(s.getSchedule().get(i).getName());
				fout.println(s.getSchedule().get(i).getTime());
				fout.println(s.getSchedule().get(i).getLocation());
				fout.println(s.getSchedule().get(i).getDate());
			}
		    fout.close();
		} catch (Exception e) {
		   // do something
		}
	}
	public void deleteSchedule(Schedule s){
		try{
			File file = new File(s.getID()+".txt");
			file.delete();
		}catch(Exception e){
			
		}
	}
}
