package trafiksimulator;

public class TrafficSystem {
    // Definierar de vägar och signaler som ingår i det 
    // system som skall studeras.
    // Samlar statistik
    
    // Attribut som beskriver beståndsdelarna i systemet
    private Lane  r0;
    private Lane  r1;
    private Lane  r2;
    private Light s1;
    private Light s2;

    // Diverse attribut för simuleringsparametrar (ankomstintensiteter,
    // destinationer...)

    // Diverse attribut för statistiksamling
    //....
    
    private int time;

    public TrafficSystem() {
    	s1 = new Light(10, 5);
    	s2 = new Light(10, 3);
    	r2 = new Lane(5, null, s2);
    	r1 = new Lane(5, null, s1, r2);
    	r0 = new Lane(3, r1, null);
    	
    }

    public void readParameters() {
	// Läser in parametrar för simuleringen
	// Metoden kan läsa från terminalfönster, dialogrutor
	// eller från en parameterfil. Det sista alternativet
	// är att föredra vid uttestning av programmet eftersom
	// man inte då behöver mata in värdena vid varje körning.
        // Standardklassen Properties är användbar för detta. 
    }

    public void step() {
	// Stega systemet ett tidssteg m h a komponenternas step-metoder
	// Skapa bilar, lägg in och ta ur på de olika Lane-kompenenterna
    	time++;
    	s1.step();
    	s2.step();
    	r2.step();
    	r1.step();
    	r0.step();
    	
    	if(r0.lastFree() && Math.random() > 0.5){
    		int dest;
    		if(Math.random() > 0.5)  dest = 1;
    		else dest = 2;
    		r0.putLast(new Car(time, dest));
    	}
    	
    }

    public void printStatistics() {
	// Skriv statistiken samlad så här långt
    }

    public void print() {
	// Skriv ut en grafisk representation av kösituationen
	// med hjälp av klassernas toString-metoder
    	System.out.println("r0:\n" + r0.toString());
    	System.out.println("r1:\n" + r1.toString());
    	System.out.println("r2:\n" + r2.toString());
    	System.out.println("s1: " + s1.toString());
    	System.out.println("s2: " + s2.toString() + "\n");
    }

}
