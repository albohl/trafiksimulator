package trafiksimulator;

public class Lorry extends Car{

	private int load;
	
	public Lorry(int bornTime, int dest) {
		super(bornTime, dest);
		load = bornTime * dest;
		// TODO Auto-generated constructor stub
	}

	/** 
     * Converts the contents of the Car into a string.
     * 
     * @return the contents of the Car as a string
     */
    public String toString() {
    	return "Lorry(bornTime = " + this.bornTime + ", dest = " + this.dest + ")";
    }
}
