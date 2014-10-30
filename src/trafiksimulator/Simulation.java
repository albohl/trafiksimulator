package trafiksimulator;

public class Simulation {

	public static void main(String[] args) {
		TrafficSystem ts = new TrafficSystem();
		for(int i = 0; i < 15; ++i){
			ts.step();
			ts.print();
		}
	}

}
