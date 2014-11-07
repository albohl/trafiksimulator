package trafiksimulator;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
 
public class SetProperties {
  public static void main(String[] args) {
 
	Properties prop = new Properties();
	OutputStream output = null;
 
	try {
 
		output = new FileOutputStream("config.properties");
 
		// set the properties value
		prop.setProperty("Length_lane_1", "3");
		prop.setProperty("Length_lane_2", "5");
		prop.setProperty("Arrival_intensity", "50");
		prop.setProperty("Green_time_1", "5");
		prop.setProperty("Green_time_2", "3");
		prop.setProperty("Period", "10");
 
		// save properties to project root folder
		prop.store(output, null);
 
	} catch (IOException io) {
		io.printStackTrace();
	} finally {
		if (output != null) {
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
 
	}
  }
}