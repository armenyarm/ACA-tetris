package tetris;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SpotTest {

	@Test
	void givenNoPrecondition_whenInstantiating_thenSucceed() {
		// given
		// when
		var result = new Spot();
		// then
		assertFalse(result.isFilled());
		assertFalse(result.isCommitted());
	}

	@Test
	void givenInstance_whenFill_thenSucceed() {
		// given
		var spot = new Spot();
		// when
		spot.fill();
		// then
		assertTrue(spot.isFilled());
		assertFalse(spot.isCommitted());
	}

	@Test
	void givenNotCommitted_whenUndo_thenSucceed() {
		// given
		var spot = new Spot();
		spot.fill();
		// when
		spot.undo();
		// then
		assertFalse(spot.isFilled());
		assertFalse(spot.isCommitted());
	}

	@Test
	void givenNotCommitted_whenCommit_thenSucceed() {
		// given
		var spot = new Spot();
		spot.fill();
		// when
		spot.commit();
		// then
		assertTrue(spot.isFilled());
		assertTrue(spot.isCommitted());
	}

	@Test
	void givenCommitted_whenUndo_thenException() {
		// given
		var spot = new Spot();
		spot.fill();
		spot.commit();
		// when, then
		assertThrows(SpotCommittedException.class, () -> spot.undo());
	}

	@Test
	void givenCommitted_whenFill_thenException() {
		// given
		var spot = new Spot();
		spot.fill();
		spot.commit();
		// when, then
		assertThrows(SpotCommittedException.class, () -> spot.fill());
	}

	@Test
	void givenInstances_whenEquals_thenSucceed() {
		// given
		var spot1 = new Spot();
		var spot2 = new Spot();
		var spot3 = new Spot();
		spot3.fill();
		// when, then
		assertTrue(spot1.equals(spot2));
		assertTrue(spot2.equals(spot1));
		assertFalse(spot1.equals(spot3));
	}
	
	@Test
	void givenInstances_whenHashCode_thenSucceed() {
		// given
		var spot1 = new Spot();
		var spot2 = new Spot();
		var spot3 = new Spot();
		spot3.fill();
		// when, then
		assertEquals(spot1.hashCode(), spot2.hashCode());
		assertNotEquals(spot1.hashCode(), spot3.hashCode());
	}

	@Test
	void givenInstance_whenToString_thenSucceed() {
		// given
		var spot = new Spot();
		assertTrue(spot.toString().contains("Spot"));
		assertTrue(spot.toString().contains("filled"));
		assertTrue(spot.toString().contains("committed"));
		assertTrue(spot.toString().contains("false"));
		assertTrue(spot.toString().contains("{"));
		assertTrue(spot.toString().contains("}"));
	}
}

