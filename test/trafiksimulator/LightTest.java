package trafiksimulator;

import static org.junit.Assert.*;

import org.junit.Test;

public class LightTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalPeriodArgument(){
		Light testLight = new Light(-1, 5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalGreenTimeArgument(){
		Light testLight = new Light(10, -1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgumentGreenGreaterThanPeriod(){
		Light testLight = new Light(10, 15);
	}


	@Test
	public void testStep() {
		Light testLight = new Light(10, 5);
		testLight.step();
		assertEquals(1, testLight.getTime());
	}
	
	@Test
	public void testTimeResetInStep(){
		Light testLight = new Light(5,3);
		for(int i = 0; i < 5; ++i){
			testLight.step();
		}
		assertEquals(0, testLight.getTime());
	}

	@Test
	public void testIsGreenAtBeginning() {
		Light testLight = new Light(10, 5);
		assertTrue(testLight.isGreen());
	}

	@Test
	public void testToString() {
		Light testLight = new Light(10,5);
		assertEquals("Light(period = 10, time = 0, green = 5)", testLight.toString());
	}

}
