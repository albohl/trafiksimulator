package trafiksimulator;
import javax.swing.JOptionPane;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class Simulation {

	public static void main(String[] args) {
//		String indata = JOptionPane.showInputDialog("Period för trafikljusen:");
//		int p = Integer.parseInt(indata);
//		indata = JOptionPane.showInputDialog("Gröntid för trafikljus 1:");
//		int g1 = Integer.parseInt(indata);
//		indata = JOptionPane.showInputDialog("Gröntid för trafikljus 2:");
//		int g2 = Integer.parseInt(indata);
//		indata = JOptionPane.showInputDialog("Ankomstintensitet:");
//		int a = Integer.parseInt(indata);
//		indata = JOptionPane.showInputDialog("Väglängd för del 1:");
//		int l1 = Integer.parseInt(indata);
//		indata = JOptionPane.showInputDialog("Väglängd för del 2:");
//		int l2 = Integer.parseInt(indata);
		Properties properties = new Properties();
		try {
		  properties.load(new FileInputStream("config.properties"));
		} catch (IOException e) {}
		int p = Integer.parseInt(properties.getProperty("Period"));
		int g1 = Integer.parseInt(properties.getProperty("Green_time_1"));
		int g2 = Integer.parseInt(properties.getProperty("Green_time_2"));
		int a = Integer.parseInt(properties.getProperty("Arrival_intensity"));
		int l1 = Integer.parseInt(properties.getProperty("Length_lane_1"));
		int l2 = Integer.parseInt(properties.getProperty("Length_lane_2"));
		
		TrafficSystem ts = new TrafficSystem(p,g1,g2,a,l1,l2);
		for(int i = 0; i < 150; ++i){
			ts.step();
//			ts.print();
		}
		ts.printStatistics();
	}

}
