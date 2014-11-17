package trafiksimulator;

public class Car {

    private int bornTime;
    
    /**
     * 1 if the car is going forward; 2 if the car is turning
     */
    private int dest;

    /**
     * 
     * @param bornTime 
     * @param dest
     */
    public Car(int bornTime, int dest){
    	if(bornTime < 0 || dest < 1 || dest > 2) throw new IllegalArgumentException();
    	this.bornTime = bornTime;
    	this.dest = dest;
    }
    /**
     * 
     * @return The born time of the car
     */
    public int getBornTime(){
    	return bornTime;
    }
    /**
     * 
     * @return 1 if the car is going forward; 2 if the car is turning
     */
    public int getDest(){
    	return dest;
    }
    /** 
     * Converts the contents of the Car into a string.
     * 
     * @return the contents of the Car as a string
     */
    public String toString() {
    	return "Car(bornTime = " + this.bornTime + ", dest = " + this.dest + ")";
    }
	
}