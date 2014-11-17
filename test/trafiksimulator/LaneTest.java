package trafiksimulator;

import static org.junit.Assert.*;

import org.junit.Test;

public class LaneTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgumentLenghtLessThanZero1(){
		Lane testLane = new Lane(-1, null, null, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgumentLenghtLessThanZero2(){
		Lane testLane = new Lane(-1, null, null);
	}


	@Test
	public void testPutLastFreeCarPosition() {
		Lane testLane = new Lane(5,null, null);
		Car testCar = new Car(0,1);
		testLane.putLast(testCar);
		assertTrue(testLane.getLast() == testCar);
	}
	
	@Test(expected = Lane.OverflowException.class)
	public void testPutLastFullCarPosition() {
		Lane testLane = new Lane(5,null, null);
		Car testCar = new Car(0,1);
		testLane.putLast(testCar);
		testLane.putLast(testCar);
	}

	@Test
	public void testStepOneStep() {
		Lane testLane = new Lane(5,null, null);
		Car testCar = new Car(0,1);
		testLane.putLast(testCar);
		testLane.step();
		assertTrue(testLane.getLast() == null);
	}
	
	@Test
	public void testStepUntilCarIsFirst() {
		Lane testLane = new Lane(5,null, null);
		Car testCar = new Car(0,1);
		testLane.putLast(testCar);
		for(int i = 0; i < 4; i++){
			testLane.step();
		}
		assertTrue(testLane.firstCar() == testCar);
	}
	
	@Test
	public void testStepCarIsRemoved() {
		Lane testLane = new Lane(5,null, null);
		Car testCar = new Car(0,1);
		testLane.putLast(testCar);
		for(int i = 0; i < 4; i++){
			testLane.step();
		}
		assertTrue(testLane.firstCar() == testCar);
		testLane.step();
		assertTrue(testLane.firstCar() == null);
	}

}
