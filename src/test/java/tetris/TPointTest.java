package tetris;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TPointTest {

	@Test
	void givenCoordinates_whenConstructing_thenSucceed() {
		// given
		int x = 1;
		int y = 2;
	        // when
		var tpoint = new TPoint(x,y);
		// then
		assertEquals(x, tpoint.getX());
		assertEquals(y, tpoint.getY());
	}

	@Test
	void givenInstance_whenCopyConstructor_thenSucceed() {
		// given
		var instance = new TPoint(2,3);
		// when
		var result = new TPoint(instance);
		// then
		assertEquals(instance.getX(), result.getX());
		assertEquals(instance.getY(), result.getY());
	}

	@Test
	void givenEqualInstances_whenEquals_thenSucceed() {
		// given
		var instance1 = new TPoint(2,3);
		var instance2 = new TPoint(2,3);
		var instance3 = new TPoint(1,1);
		// then
		assertTrue(instance1.equals(instance2));
		assertTrue(instance2.equals(instance1));
		assertTrue(instance1.equals(instance1));
		assertTrue(instance2.equals(instance2));
		assertFalse(instance1.equals(instance3));
		assertFalse(instance2.equals(instance3));
	}


	@Test
	void givenInstance_whenToString_thenSucceed() {
		// given
		var instance = new TPoint(1,2);
		// then
		assertTrue(instance.toString().contains("(1,2)"));
	}

	@Test
	void givenEqualInstances_whenhashCode_thenSucceed() {
		// given
		var instance1 = new TPoint(1,2);
		var instance2 = new TPoint(1,2);
		// then
		assertEquals(instance1.hashCode(), instance2.hashCode());
	}
}

