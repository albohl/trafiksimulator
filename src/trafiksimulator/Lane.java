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
		// Undantag som kastas n�r det inte gick att l�gga 
		// in en ny bil p� v�gen
	}

    /** An array of carPosition objects that make up the lane segment. */
    private CarPosition[] theLane;
    
    /** The lane object this lane segment leads into. */
    public Lane nextLane;
    
    /** The traffic light of this lane segment if it has one, null otherwise  */
    public Light light;

    /**
     * Instantiates a new lane.
     *
     * @param n the length of the lane segment
     * @param nextLane the next lane object
     * @param light the traffic light
     * @param turn the lane beside this lane
     */
    public Lane(int n, Lane nextLane, Light light, Lane turn) {
	// Konstruerar ett Lane-objekt med plats f�r n fordon
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
     * @param n the length of the lane segment
     * @param nextLane the next lane object
     * @param light the traffic light
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
     * Checks if the car position target is the last in the lane.
     *
     * @param target the car position in question.
     * @return true, if target is the last carPosition in the lane, false otherwise
     */
    public boolean matchEnd(CarPosition target)
    {
    	if(theLane[0] == target)
    		return true;
    	else
    		return false;
    }

    /**
     * Iterates through the lane and moves all the cars forward if possible.
     */
    public void step() {
	// Stega fram alla fordon (utom det p� plats 0) ett steg 
        // (om det g�r). (Fordonet p� plats 0 tas bort utifr�n 
	// mm h a metoden nedan.)
    	for(int i = 0; i < theLane.length; ++i){
    		theLane[i].moveForward();
    	}
    }

    /**
     * Returns the first car in the lane and removes it.
     *
     * @return the first car in the lane.
     */
    public Car getFirst() {
	// Returnera och tag bort bilen som st�r f�rst
    	Car car = theLane[0].getCurrentCar();
    	return car;
    }

    /**
     * Returns the first car without removing it.
     *
     * @return the first car in the lane.
     */
    public Car firstCar() {
	// Returnera bilen som st�r f�rst utan att ta bort den
    	return theLane[0].getCurrentCar();
    }


    /**
     *Checks if the last position in the lane is free.
     *
     * @return true, if the last position is free, false otherwise
     */
    public boolean lastFree() {
	// Returnera true om sista platsen ledig, annars false
    	if (theLane[theLane.length - 1].getCurrentCar() == null) return true;
    	else return false;
    }

    /**
     * Places the car c last in the lane if the position is empty, throws OverflowException otherwise.
     * 
     *
     * @param c the car
     * @throws OverflowException Thrown if the last position in the lane is full.
     */
    public void putLast(Car c) throws OverflowException {
	// St�ll en bil p� sista platsen p� v�gen
	// (om det g�r).
    	if (theLane[theLane.length - 1].getCurrentCar() == null){
    		theLane[theLane.length - 1].setCurrentCar(c);
    		return;
    	}
    	throw new OverflowException();
    }
    
    /**
     * Returns the traffic light of the lane.
     *
     * @return the light
     */
    public Light getLight(){
    	return light;
    }
    
    /**
     * Sets the traffic light of the lane.
     *
     * @param light the traffic light
     */
    public void setLight(Light light){
    	this.light = light;
    }
    
    /**
     * Returns the next lane segment.
     *
     * @return the next lane segment
     */
    public Lane getNextLane(){
    	return nextLane;
    }
    
    /**
     * Sets the next lane segment.
     *
     * @param nextLane the next lane
     */
    public void setNextLane(Lane nextLane){
    	this.nextLane = nextLane;
    }

    /** 
     * Converts the contents of the lane into a string.
     * 
     * @return the contents of the lane as a string
     */
    public String toString() {
    	String returnMsg = "";
    	for(int i = 0; i < theLane.length; ++i){
    		returnMsg += "Car Position " + i + ": " + theLane[i].toString() + "\n";
    	}
    	return returnMsg;
    }

}