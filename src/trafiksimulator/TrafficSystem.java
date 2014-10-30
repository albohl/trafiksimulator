package trafiksimulator;

public class TrafficSystem {
    // Definierar de v�gar och signaler som ing�r i det 
    // system som skall studeras.
    // Samlar statistik
    
    // Attribut som beskriver best�ndsdelarna i systemet
    private Lane  r0;
    private Lane  r1;
    private Lane  r2;
    private Light s1;
    private Light s2;

    // Diverse attribut f�r simuleringsparametrar (ankomstintensiteter,
    // destinationer...)

    // Diverse attribut f�r statistiksamling
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
	// L�ser in parametrar f�r simuleringen
	// Metoden kan l�sa fr�n terminalf�nster, dialogrutor
	// eller fr�n en parameterfil. Det sista alternativet
	// �r att f�redra vid uttestning av programmet eftersom
	// man inte d� beh�ver mata in v�rdena vid varje k�rning.
        // Standardklassen Properties �r anv�ndbar f�r detta. 
    }

    public void step() {
	// Stega systemet ett tidssteg m h a komponenternas step-metoder
	// Skapa bilar, l�gg in och ta ur p� de olika Lane-kompenenterna
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
	// Skriv statistiken samlad s� h�r l�ngt
    }

    public void print() {
	// Skriv ut en grafisk representation av k�situationen
	// med hj�lp av klassernas toString-metoder
    	System.out.println("r0:\n" + r0.toString());
    	System.out.println("r1:\n" + r1.toString());
    	System.out.println("r2:\n" + r2.toString());
    	System.out.println("s1: " + s1.toString());
    	System.out.println("s2: " + s2.toString() + "\n");
    }

}
