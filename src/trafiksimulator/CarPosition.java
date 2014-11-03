package trafiksimulator;

public class CarPosition{
	
	private Car currentCar = null; // null om ingen bil finns på positionen
	
	private Lane owner;
	
	private CarPosition forward;
	private CarPosition turn;
	
	public CarPosition(Lane a_Owner, CarPosition forward, CarPosition turn)
	{
		owner = a_Owner;
		this.forward = forward;
		this.turn = turn;
	}
	public CarPosition(Lane a_Owner, CarPosition forward)
	{
		owner = a_Owner;
		this.forward = forward;
	}
	
	public boolean isEnd(CarPosition target)
	{
		return owner.matchEnd(target);
	}
	
	public void moveForward()
	{
		if (currentCar == null) return;
		if(isEnd(this)){
			if(owner.getLight() != null){
				if(!owner.getLight().isGreen()) return;
			}
			if(owner.getNextLane() == null) currentCar = null;		
			else if(owner.getNextLane().lastFree()){
				if(owner.getNextLane().putLast(currentCar)) currentCar = null;
			}
		}
		else if(currentCar.getDest() == 1 || turn == null){
			if(forward.getCurrentCar() == null){
				forward.setCurrentCar(currentCar);
				currentCar = null;
			}
			}else{
				if(turn.getCurrentCar() == null){
					turn.setCurrentCar(currentCar);
					currentCar = null;
				}
			}
		// Flytta bilen fram till forward
	}
	
	public boolean turn()
	{
		return false;
		// Flytta bilen till turn
	}

	public void setTurn(CarPosition turn) {
		this.turn = turn;
	}

	public CarPosition getTurn() {
		return turn;
	}
	public Car getCurrentCar(){
		return currentCar;
	}
	public void setCurrentCar(Car car){
		currentCar = car;
	}
	public String toString(){
		if (currentCar == null){
			return "null";
		}else{
			return currentCar.toString();
		}
	}
	
}
