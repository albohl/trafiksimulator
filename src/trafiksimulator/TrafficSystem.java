package trafiksimulator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

// TODO: Auto-generated Javadoc
//import java.awt.event.*;

/**
 * The Class TrafficSystem.
 */
public class TrafficSystem {
    // Definierar de vägar och signaler som ingår i det 
    // system som skall studeras.
    // Samlar statistik
    
    // Attribut som beskriver beståndsdelarna i systemet
    /** The first lane segment without a lane to the side. */
    private Lane  r0;
    
    /** The lane segment following on from r0 meant for cars going forward*/
    private Lane  r1;
    
    /** The lane segment to the side of r1 meant for cars that are going to turn */
    private Lane  r2;
    
    /** The light for the lane going forward. */
    private Light s1;
    
    /** The light for the lane going to turn*/
    private Light s2;
    
    /** The period for the traffic lights*/
    private int p;
    
    /** The green time for the first light*/
    private int g1;
    
    /** The green time for the second light*/
    private int g2;
    
    /** The length of the first lane segment (where there is only one lane)*/
    private int l1;
    
    /** The length of the segment lane segment (where there are two lanes)*/
    private int l2;
    
    /** The arrival intensity, as a percentage chance of a car arriving each 
     * time unit */
    private int arrivalIntensity;
    
    /** The number of cars that have passed the first lane. */
    private int pc1;
    
    /** The number of cars that have passed the second lane. */
    private int pc2;
    
    /** The average time the cars spent in the first lane. */
    private int avTime1;
    
    /** The average time the cars spent in the second lane. */
    private int avTime2;
    
    /** The maximum time spent in the first lane. */
    private int maxTime1;
    
    /** The The maximum time spent in the second lane. */
    private int maxTime2;
    
    /** The full lane count. */
    private int fullLaneCount;

    // Diverse attribut för simuleringsparametrar (ankomstintensiteter,
    // destinationer...)

    // Diverse attribut för statistiksamling
    //....
    
    /** The time. */
    private int time;

    /**
     * Instantiates a new traffic system.
     */
    public TrafficSystem() {
    	readParameters();
    	s1 = new Light(p, g1);
    	s2 = new Light(p, g2);
    	r2 = new Lane(l2, null, s2);
    	r1 = new Lane(l2, null, s1, r2);
    	r0 = new Lane(l1, r1, null);
    	
    }

    /**
     * Requires a property file in the same folder named config.properties 
     * <p>
	 * Reads the arguments from the properties file.
     */
    public void readParameters() {
    	// Läser in parametrar för simuleringen
    	// Metoden kan läsa från terminalfönster, dialogrutor
    	// eller från en parameterfil. Det sista alternativet
    	// är att föredra vid uttestning av programmet eftersom
    	// man inte då behöver mata in värdena vid varje körning.
    	// Standardklassen Properties är användbar för detta. 

    	Properties properties = new Properties();
		try {
		  properties.load(new FileInputStream("config.properties"));
		} catch (IOException e) {System.out.println("config.properties not found");}
		p = Integer.parseInt(properties.getProperty("Period"));
		g1 = Integer.parseInt(properties.getProperty("Green_time_1"));
		g2 = Integer.parseInt(properties.getProperty("Green_time_2"));
		arrivalIntensity = Integer.parseInt(properties.getProperty("Arrival_intensity"));
		l1 = Integer.parseInt(properties.getProperty("Length_lane_1"));
		l2 = Integer.parseInt(properties.getProperty("Length_lane_2"));
		
    }

    /**
     * Steps the TrafficSystem forward by calling the components' step-methods
     * and spawning new cars
     */
    public void step() {
	// Stega systemet ett tidssteg m h a komponenternas step-metoder
	// Skapa bilar, lägg in och ta ur på de olika Lane-kompenenterna
    	
    	Car c1 = r1.firstCar();
    	Car c2 = r2.firstCar();
    	if (c1 != null){
    		if (r1.getLight().isGreen()){
    			pc1++;
    			int bt1 = c1.getBornTime();
    			int pt = time - bt1;
    			avTime1 = (avTime1 * (pc1 - 1) + pt)/pc1;
    			if(pt > maxTime1) maxTime1 = pt;
    		}	
    	}
    	if (c2 != null){
    		if (r2.getLight().isGreen()){
    			pc2++;
    			int bt2 = c2.getBornTime();
    			int pt = time - bt2;
    			avTime2 = (avTime2 * (pc2 - 1) + pt)/pc2;
    			if(pt > maxTime2) maxTime2 = pt;
    		}	
    	}
    	
    	s1.step();
    	s2.step();
    	r2.step();
    	r1.step();
    	r0.step();
    	
    	if(Math.random() > arrivalIntensity * 0.01){
    		int dest;
    		if(Math.random() > 0.5)  dest = 1;
    		else dest = 2;
    		
    		try{r0.putLast(new Car(time, dest));}
    		catch(Lane.OverflowException e){
    			fullLaneCount++;
    		}
    	}
    	time++;
    	
    }

    /**
     * Prints the statistics collected.
     */
    public void printStatistics() {
	// Skriv statistiken samlad så här långt
    	System.out.println("Passed cars lane r1: " + pc1 + "\n");
    	System.out.println("Passed cars lane r2: " + pc2 + "\n");
    	System.out.println("Average time lane r1: " + avTime1 + "\n");
    	System.out.println("Average time lane r2: " + avTime2 + "\n");
    	System.out.println("Max time lane r1: " + maxTime1 + "\n");
    	System.out.println("Max time lane r2: " + maxTime2 + "\n");
    	System.out.println("Number of full lane exceptions: " + fullLaneCount + "\n");
    }

    /**
     * Prints the contents of the TrafficSystems components converted into 
     * strings.
     */
    public void print() {
	// Skriv ut en grafisk representation av kösituationen
	// med hjälp av klassernas toString-metoder
    	System.out.println("r0:\n" + r0.toString());
    	System.out.println("r1:\n" + r1.toString());
    	System.out.println("r2:\n" + r2.toString());
    	System.out.println("s1: " + s1.toString());
    	System.out.println("s2: " + s2.toString() + "\n");
    }
//    
//   ActionListener  l = new ActionListener(){
//	   public void actionPerformed(ActionEvent e){
//		   
//	   }
//   };

}
