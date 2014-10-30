package trafiksimulator;

public class Light {
    private int period;
    private int time;  // Intern klocka: 0, 1, ... period-1, 0, 1 ...
    private int green; // Signalen grön när time<green 

    public Light(int period, int green) {
    	this.period = period;
    	this.green = green;
    	time = 0;
    }

    public void    step() { 
       time++;
       if (time == period) time = 0;
    }

    public boolean isGreen()   {
    	if(time < green) return true;
    	return false;
    }

    public String  toString()  {
    	return "Light(period = " + this.period + ", time = " + this.time + ", green = "  + this.green + ")";
    }
	
}