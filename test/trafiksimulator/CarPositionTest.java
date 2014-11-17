package trafiksimulator;

import static org.junit.Assert.*;

import org.junit.Test;

public class CarPositionTest {

	@Test
	public void testMoveForwardCarToMoveFreeCarPosition() {
		Lane testLane = new Lane(5, null, null);
		CarPosition cp1 = new CarPosition(null, null, null);
		CarPosition cp2 = new CarPosition(testLane, cp1, null);
		Car testCar = new Car(0,1);
		cp2.setCurrentCar(testCar);
		cp2.moveForward();
		assertTrue(cp1.getCurrentCar() == testCar);
	}
	
	@Test
	public void testMoveForwardCarToMoveFullCarPosition() {
		Lane testLane = new Lane(5, null, null);
		CarPosition cp1 = new CarPosition(null, null, null);
		CarPosition cp2 = new CarPosition(testLane, cp1, null);
		Car testCar1 = new Car(0,1);
		Car testCar2 = new Car(0,1);
		cp1.setCurrentCar(testCar1);
		cp2.setCurrentCar(testCar2);
		cp2.moveForward();
		assertTrue(cp1.getCurrentCar() == testCar1);
	}
	
	@Test
	public void testMoveForwardNoCarToMoveFreeCarPosition() {
		Lane testLane = new Lane(5, null, null);
		CarPosition cp1 = new CarPosition(null, null, null);
		CarPosition cp2 = new CarPosition(testLane, cp1, null);
		cp2.moveForward();
		assertTrue(cp1.getCurrentCar() == null);
	}
	
	@Test
	public void testMoveForwardNoCarToMoveFullCarPosition() {
		Lane testLane = new Lane(5, null, null);
		CarPosition cp1 = new CarPosition(null, null, null);
		CarPosition cp2 = new CarPosition(testLane, cp1, null);
		Car testCar = new Car(0,1);
		cp1.setCurrentCar(testCar);
		cp2.moveForward();
		assertTrue(cp1.getCurrentCar() == testCar);
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
		assertTrue(cp2.getCurrentCar() == testCar2);
		assertTrue(cp1.getCurrentCar() == testCar1);
	}

}
