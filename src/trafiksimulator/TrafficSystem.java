package trafiksimulator;

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
    /** The r0. */
    private Lane  r0;
    
    /** The r1. */
    private Lane  r1;
    
    /** The r2. */
    private Lane  r2;
    
    /** The s1. */
    private Light s1;
    
    /** The s2. */
    private Light s2;
    
    /** The arrival intensity. */
    private int arrivalIntensity;
    
    /** The pc1. */
    private int pc1;
    
    /** The pc2. */
    private int pc2;
    
    /** The av time1. */
    private int avTime1;
    
    /** The av time2. */
    private int avTime2;
    
    /** The max time1. */
    private int maxTime1;
    
    /** The max time2. */
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
     *
     * @param p the p
     * @param g1 the g1
     * @param g2 the g2
     * @param a the a
     * @param l1 the l1
     * @param l2 the l2
     */
    public TrafficSystem(int p, int g1, int g2, int a, int l1, int l2) {
    	s1 = new Light(p, g1);
    	s2 = new Light(p, g2);
    	r2 = new Lane(l2, null, s2);
    	r1 = new Lane(l2, null, s1, r2);
    	r0 = new Lane(l1, r1, null);
    	
    }

    /**
     * Read parameters.
     */
    public void readParameters() {
	// Läser in parametrar för simuleringen
	// Metoden kan läsa från terminalfönster, dialogrutor
	// eller från en parameterfil. Det sista alternativet
	// är att föredra vid uttestning av programmet eftersom
	// man inte då behöver mata in värdena vid varje körning.
        // Standardklassen Properties är användbar för detta. 
    }

    /**
     * Step.
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
     * Prints the statistics.
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
     * Prints the.
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
