package trafiksimulator;
import javax.swing.JOptionPane;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class Simulation {

	/**
	 * 
	 * steps the traffic system forward and prints the statistics gathered
	 * in the TrafficSystem.
	 *
	 */
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
		
		TrafficSystem ts = new TrafficSystem();
		for(int i = 0; i < 150; ++i){
			ts.step();
//			ts.print();
		}
		ts.printStatistics();
	}

}
