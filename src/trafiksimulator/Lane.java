package trafiksimulator;

// TODO: Auto-generated Javadoc
/**
 * The Class Lane.
 */
public class Lane {

	/**
	 * The Class OverflowException.
	 */
	public static class OverflowException extends RuntimeException {
		// Undantag som kastas när det inte gick att lägga 
		// in en ny bil på vägen
	}

    /** The lane. */
    private CarPosition[] theLane;
    
    /** The next lane. */
    public Lane nextLane;
    
    /** The light. */
    public Light light;

    /**
     * Instantiates a new lane.
     *
     * @param n the n
     * @param nextLane the next lane
     * @param light the light
     * @param turn the turn
     */
    public Lane(int n, Lane nextLane, Light light, Lane turn) {
	// Konstruerar ett Lane-objekt med plats för n fordon
    	theLane = new CarPosition[n];
    	theLane[0] = new CarPosition(this, null, turn.theLane[0]);
		for(int i = 1; i < n; ++i){
			theLane[i] = new CarPosition(this, theLane[i-1], turn.theLane[i]);
		}
		this.nextLane = nextLane;
		this.light = light;
    }
    
    /**
     * Instantiates a new lane.
     *
     * @param n the n
     * @param nextLane the next lane
     * @param light the light
     */
    public Lane(int n, Lane nextLane, Light light){
    	theLane = new CarPosition[n];
    	theLane[0] = new CarPosition(this, null);
		for(int i = 1; i < n; ++i){
			theLane[i] = new CarPosition(this, theLane[i-1]);
		}
		this.nextLane = nextLane;
		this.light = light;
    }
    
    /**
     * Match end.
     *
     * @param target the target
     * @return true, if successful
     */
    public boolean matchEnd(CarPosition target)
    {
    	if(theLane[0] == target)
    		return true;
    	else
    		return false;
    }

    /**
     * Step.
     */
    public void step() {
	// Stega fram alla fordon (utom det på plats 0) ett steg 
        // (om det går). (Fordonet på plats 0 tas bort utifrån 
	// mm h a metoden nedan.)
    	for(int i = 0; i < theLane.length; ++i){
    		theLane[i].moveForward();
    	}
    }

    /**
     * Gets the first.
     *
     * @return the first
     */
    public Car getFirst() {
	// Returnera och tag bort bilen som står först
    	Car car = theLane[0].getCurrentCar();
    	return car;
    }

    /**
     * First car.
     *
     * @return the car
     */
    public Car firstCar() {
	// Returnera bilen som står först utan att ta bort den
    	return theLane[0].getCurrentCar();
    }


    /**
     * Last free.
     *
     * @return true, if successful
     */
    public boolean lastFree() {
	// Returnera true om sista platsen ledig, annars false
    	if (theLane[theLane.length - 1].getCurrentCar() == null) return true;
    	else return false;
    }

    /**
     * Put last.
     *
     * @param c the c
     * @return true, if successful
     * @throws OverflowException the overflow exception
     */
    public boolean putLast(Car c) throws OverflowException {
	// Ställ en bil på sista platsen på vägen
	// (om det går).
    	if (theLane[theLane.length - 1].getCurrentCar() == null){
    		theLane[theLane.length - 1].setCurrentCar(c);
    		return true;
    	}
    	throw new OverflowException();
    }
    
    /**
     * Gets the light.
     *
     * @return the light
     */
    public Light getLight(){
    	return light;
    }
    
    /**
     * Sets the light.
     *
     * @param light the new light
     */
    public void setLight(Light light){
    	this.light = light;
    }
    
    /**
     * Gets the next lane.
     *
     * @return the next lane
     */
    public Lane getNextLane(){
    	return nextLane;
    }
    
    /**
     * Sets the next lane.
     *
     * @param nextLane the new next lane
     */
    public void setNextLane(Lane nextLane){
    	this.nextLane = nextLane;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
    	String returnMsg = "";
    	for(int i = 0; i < theLane.length; ++i){
    		returnMsg += "Car Position " + i + ": " + theLane[i].toString() + "\n";
    	}
    	return returnMsg;
    }

}