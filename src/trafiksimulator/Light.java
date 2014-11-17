package trafiksimulator;

// TODO: Auto-generated Javadoc
/**
 * The Class Light.
 */
public class Light {
    
    /** The period of the traffic light. */
    private int period;
    
    /** The time the internal clock of the traffic light. */
    private int time;  // Intern klocka: 0, 1, ... period-1, 0, 1 ...
    
    /** The length of time the light is green. */
    private int green; // Signalen grön när time<green 

    /**
     * Instantiates a new light.
     *
     * @param period the period of the traffic light
     * @param green the length of time the light is green
     */
    public Light(int period, int green) {
    	if(period < 0 || green < 0 || green > period) throw new IllegalArgumentException();
    	this.period = period;
    	this.green = green;
    	time = 0;
    }

    /**
     * Steps the clock forward, loops back around if the time is equal to the 
     * period.
     */
    public void step() { 
       time++;
       if (time == period) time = 0;
    }

    /**
     * Checks if the light is green.
     *
     * @return true, if the light is green, false otherwise
     */
    public boolean isGreen()   {
    	if(time < green) return true;
    	return false;
    }
    /** 
     * Returns the period of the traffic light
     * 
     * @return the period of the traffic light
     */
    
    public int getPeriod(){
    	return period;
    }
    /** 
     * Returns the current time of the traffic light
     * 
     * @return the current time of the traffic light
     */
    
    public int getTime(){
    	return time;
    }
    /** 
     * Returns the green time of the traffic light
     * 
     * @return the green time of the traffic light
     */
    
    public int getGreen(){
    	return green;
    }

    /** 
     * Converts the contents of the light into a string.
     * 
     * @return the contents of the light as a string
     */
    public String  toString()  {
    	return "Light(period = " + this.period + ", time = " + this.time + ", green = "  + this.green + ")";
    }
	
}