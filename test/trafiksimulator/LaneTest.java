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


//	@Test
//	public void testMatchEnd() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testStep() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testLastFree() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testPutLast() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testToString() {
//		fail("Not yet implemented");
//	}

}
