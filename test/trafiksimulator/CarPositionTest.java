package trafiksimulator;

import static org.junit.Assert.*;

import org.junit.Test;

public class CarPositionTest {

	@Test
	public void testIsEnd() {
		fail("Not yet implemented");
	}

	@Test
	public void testMoveForward() {
		fail("Not yet implemented");
	}

	@Test
	public void testTurnWithFreeCarPosition() {
		CarPosition cp1 = new CarPosition(null, null, null);
		CarPosition cp2 = new CarPosition(null, null, cp1);
		Car testCar = new Car(0,1);
		cp2.setCurrentCar(testCar);
		cp2.turn();
		assertTrue(cp1.getCurrentCar() == testCar);
	}
	
	@Test
	public void testTurnWithFullCarPosition() {
		CarPosition cp1 = new CarPosition(null, null, null);
		CarPosition cp2 = new CarPosition(null, null, cp1);
		Car testCar1 = new Car(0,1);
		Car testCar2 = new Car(0,1);
		cp1.setCurrentCar(testCar1);
		cp2.setCurrentCar(testCar2);
		cp2.turn();
		assertFalse(cp1.getCurrentCar() == testCar2);
	}

}
