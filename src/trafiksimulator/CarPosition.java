package trafiksimulator;

// TODO: Auto-generated Javadoc
/**
 * The Class CarPosition.
 */
public class CarPosition{
	
	/** The current car. */
	private Car currentCar = null; // null om ingen bil finns på positionen
	
	/** The lane the carPosition is in. */
	private Lane owner;
	
	/** The CarPosition in front of this. */
	private CarPosition forward;
	
	/** The CarPosition to the side of this. */
	private CarPosition turn;
	
	/**
	 * Instantiates a new car position.
	 *
	 * @param a_Owner the lane the carPosition is to be in
	 * @param forward the CarPosition in front of this
	 * @param turn the CarPosition to the side of this
	 */
	public CarPosition(Lane a_Owner, CarPosition forward, CarPosition turn)
	{
		owner = a_Owner;
		this.forward = forward;
		this.turn = turn;
	}
	
	/**
	 * Instantiates a new car position.
	 *
	 * @param a_Owner the lane the carPosition is to be in
	 * @param forward the CarPosition in front of this
	 */
	public CarPosition(Lane a_Owner, CarPosition forward)
	{
		owner = a_Owner;
		this.forward = forward;
	}
	
	/**
	 * Checks if the CarPosition is at the end of the lane segment.
	 *
	 * @param target the target CarPosition
	 * @return true, if the CarPosition is at the end of the lane segment, false otherwise
	 */
	public boolean isEnd(CarPosition target)
	{
		return owner.matchEnd(target);
	}
	
	/**
	 * Moves the car in this CarPosition forward if
	 * * it exists and
	 * * has destination 1 and the spot ahead is open or
	 * * has destination 2 and the spot ahead is open and there is no turn 
	 * lane to the side
	 * 
	 * or moves the car in this CarPosition to the lane to the side if that spot
	 * is open
	 * 
	 * or removes the car if it is at the traffic light and it is green.
	 * 
	 */
	public void moveForward()
	{
		if (currentCar == null) return;
		if(isEnd(this)){
			if(owner.getLight() != null){
				if(!owner.getLight().isGreen()) return;
			}
			if(owner.getNextLane() == null) currentCar = null;		
			else if(owner.getNextLane().lastFree()){
				try{owner.getNextLane().putLast(currentCar);}
				catch (Lane.OverflowException e) {
					return;
				}
				currentCar = null;
			}
		}
		else if(currentCar.getDest() == 1 || turn == null){
			if(forward.getCurrentCar() == null){
				forward.setCurrentCar(currentCar);
				currentCar = null;
			}
			}else{
				turn();
			}
		// Flytta bilen fram till forward
	}
	
	/**
	 * Moves
	 *
	 * @return true, if successful
	 */
	public boolean turn() 
	{
		if(turn.getCurrentCar() == null){
			turn.setCurrentCar(currentCar);
			currentCar = null;
		}
		return false;
		// Flytta bilen till turn
	}

	/**
	 * Sets the CarPosition to the side of this.
	 *
	 * @param turn the CarPosition to the side of this one in the parallel lane
	 */
	public void setTurn(CarPosition turn) {
		this.turn = turn;
	}

	/**
	 * Returns the CarPosition to the side of this one.
	 *
	 * @return the CarPosition to the side of this one in the parallel lane
	 */
	public CarPosition getTurn() {
		return turn;
	}
	
	/**
	 * Returns the car in the current CarPosition.
	 *
	 * @return the  car in the current CarPosition
	 */
	public Car getCurrentCar(){
		return currentCar;
	}
	
	/**
	 * Sets the current car.
	 *
	 * @param car the new current car
	 */
	public void setCurrentCar(Car car){
		currentCar = car;
	}
	
	/** 
     * Converts the contents of the CarPosition into a string.
     * 
     * @return the contents of the CarPosition as a string
     */
	public String toString(){
		if (currentCar == null){
			return "null";
		}else{
			return currentCar.toString();
		}
	}
	
}
