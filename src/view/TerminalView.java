package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import control.Control;
import model.Events;
import model.Schedule;
public class TerminalView extends JPanel implements KeyListener, MouseListener, ActionListener{
	private String[] days = {"Sunday","Monday","Tuesday","Wednesday","thursday","friday","Saturday"};
	private static final long serialVersionUID = 1L;
	private JFrame window;
	private JButton makeSchedule;
	private JPanel timeInput, textInput,sInput, input;
	private JTextField location,pname, ID;
	private JSpinner sTime, eTime;
	private JComboBox<String> day;
	private JLabel start, end, lname, place, sID,date;
	Control c;
	public TerminalView(Control con){
		c=con;
		window = new JFrame("Schedule Builder");
		window.setPreferredSize(new Dimension(765,900));
		window.setLayout(new BorderLayout());
		createInput();
		window.add(this,BorderLayout.CENTER);
		//input.setPreferredSize(new Dimension(765,20));
		window.add(input,BorderLayout.SOUTH);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setResizable(false);
		this.setFocusable(true);
		this.addKeyListener(this);
		window.setFocusable(false);
		window.setVisible(true);
	}
	private void createInput(){
		input = new JPanel();input.setLayout(new BorderLayout());input.setFocusable(false);
		timeInput = new JPanel();timeInput.setFocusable(false);timeInput.setLayout(new BorderLayout());
		textInput = new JPanel();textInput.setLayout(new BorderLayout());textInput.setFocusable(false);
		sInput = new JPanel();sInput.setLayout(new BorderLayout());sInput.setFocusable(false);
		
		

		location = new JTextField(10);
		location.addActionListener(this);
		pname = new JTextField(10);
		pname.addActionListener(this);
		ID = new JTextField(10);
		ID.addActionListener(this);
		start=new JLabel("Start Time:");
		end=new JLabel("End Time:");
		lname=new JLabel("Event :");
		place=new JLabel("Location: ");
		day = new JComboBox<String>(days);
		day.setFocusable(false);
		day.addActionListener(this);
		JPanel a = new JPanel();a.setLayout(new BorderLayout());a.setFocusable(false);
		JPanel b = new JPanel();b.setLayout(new BorderLayout());b.setFocusable(false);
		JPanel c = new JPanel();c.setLayout(new BorderLayout());c.setFocusable(false);
		JPanel d = new JPanel();d.setLayout(new BorderLayout());d.setFocusable(false);
		JPanel e = new JPanel();e.setLayout(new BorderLayout());e.setFocusable(false);
		JPanel f = new JPanel();f.setLayout(new BorderLayout());f.setFocusable(false);
		
		SpinnerDateModel star = new SpinnerDateModel();
		star.setCalendarField(Calendar.MINUTE);
		star.setCalendarField(Calendar.HOUR_OF_DAY);
		SpinnerDateModel en = new SpinnerDateModel();
		en.setCalendarField(Calendar.MINUTE);
		star.setCalendarField(Calendar.HOUR_OF_DAY);
		sTime= new JSpinner();
		eTime= new JSpinner();
		sTime.setModel(star);
		eTime.setModel(en);
		sTime.setEditor(new JSpinner.DateEditor(sTime, "h:mm"));
		eTime.setEditor(new JSpinner.DateEditor(eTime, "h:mm"));
		date= new JLabel("Day Of Week ");
		sID= new JLabel("ID NUmber: ");
		makeSchedule = new JButton("New...");
		
		
		
		a.add(sTime,BorderLayout.NORTH);
		a.add(eTime,BorderLayout.SOUTH);
		b.add(end, BorderLayout.SOUTH);
		b.add(start, BorderLayout.NORTH);
		c.add(pname,  BorderLayout.NORTH);
		c.add(location,  BorderLayout.SOUTH);
		e.add(date,  BorderLayout.NORTH);
		e.add(sID,  BorderLayout.SOUTH);
		d.add(place, BorderLayout.SOUTH);
		d.add(lname, BorderLayout.NORTH);
		f.add(day, BorderLayout.NORTH);
		f.add(ID,BorderLayout.SOUTH);
		
		timeInput.add(a, BorderLayout.EAST);
		timeInput.add(b, BorderLayout.WEST);
		textInput.add(c, BorderLayout.EAST);
		textInput.add(d, BorderLayout.WEST);
		sInput.add(f, BorderLayout.EAST);
		sInput.add(e, BorderLayout.WEST);
		input.add(timeInput, BorderLayout.EAST);
		input.add(sInput,BorderLayout.CENTER);
		input.add(textInput, BorderLayout.WEST);

		makeSchedule.addActionListener(this);
		input.add(makeSchedule, BorderLayout.SOUTH);
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		paintBackground(g);
		paintSchedule(g);
	}
	
	private void paintBackground(Graphics g){
		g.setColor(Color.BLACK);
		g.drawRect(25, 75, 100,648);
		g.drawRect(125, 75, 100, 648);
		g.drawRect(225, 75, 100, 648);
		g.drawRect(325, 75, 100, 648);
		g.drawRect(425, 75, 100, 648);
		g.drawRect(525, 75, 100, 648);
		g.drawRect(625, 75, 100, 648);
	}
	private void paintSchedule(Graphics g){
		g.setColor(Color.BLUE);
		for(String i :c.getIDs()){
			Schedule s = c.getIOS().loadSchedule(Integer.parseInt(i));
			for(Events e:s.getSchedule()){
				String x1 = e.getStartString();
				String x2 = e.getEndString();
				int y1,y2,y3,y4;
				y1=Integer.parseInt(x1.substring(0,2))*24;
				y2=Integer.parseInt(x1.substring(2,4))/3;
				y3=Integer.parseInt(x2.substring(0,2))*24;
				y4=Integer.parseInt(x2.substring(2,4))/3;
				g.fillRect((e.getDate()*100)+25, y1+y2+75, 100, ((y3-y1)+(y4-y2))+75);
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(makeSchedule)){
			try{
			Schedule s= new Schedule();
			s.setID(Integer.parseInt(ID.getText()));
			String st="";String et="";
			if(sTime.getValue().toString().charAt(10)==' '){
				st=sTime.getValue().toString().charAt(11)+""+sTime.getValue().toString().charAt(12)+sTime.getValue().toString().charAt(14)+sTime.getValue().toString().charAt(15);
			}else{
				st=sTime.getValue().toString().charAt(10)+""+sTime.getValue().toString().charAt(11)+sTime.getValue().toString().charAt(13)+sTime.getValue().toString().charAt(14);
			}
			if(eTime.getValue().toString().charAt(10)==' '){
				et=sTime.getValue().toString().charAt(11)+""+sTime.getValue().toString().charAt(12)+sTime.getValue().toString().charAt(14)+sTime.getValue().toString().charAt(15);
			}else{
				et=sTime.getValue().toString().charAt(10)+""+sTime.getValue().toString().charAt(11)+sTime.getValue().toString().charAt(13)+sTime.getValue().toString().charAt(14);
			}
			s.addEvent(new Events(pname.getText(),st,et,location.getText(),day.getSelectedIndex()));
			c.getIOS().saveSchedule(s);
			repaint();
			}catch(Exception e){
				
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	@Override
	public void keyPressed(KeyEvent arg0) {}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
	
}
