package trafiksimulator;

public class Car {

    private int bornTime;
    public int dest; // 1 f�r rakt fram, 2 f�r v�nstersv�ng

    // konstruktor och get-metoder
    public Car(int bornTime, int dest){
    	this.bornTime = bornTime;
    	this.dest = dest;
    }
    public int getBornTime(){
    	return bornTime;
    }
    public int getDest(){
    	return dest;
    }
    public String toString() {
    	return "Car(bornTime = " + this.bornTime + ", dest = " + this.dest + ")";
    }
	
}