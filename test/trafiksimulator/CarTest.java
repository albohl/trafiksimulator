package trafiksimulator;

import static org.junit.Assert.*;

import org.junit.Test;

public class CarTest {

	@Test
	public void testToString() {
		Car test = new Car(0,1);
		assertEquals("Car(bornTime = 0, dest = 1)", test.toString());
	}

}
