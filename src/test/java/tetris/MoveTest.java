package tetris;

import java.util.Set;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MoveTest {

	@Test
	void givenMove_whenValues_thenSucceed() {
		assertEquals(5, Move.values().length);
		assertNotNull(Move.LEFT);
		assertNotNull(Move.RIGHT);
		assertNotNull(Move.ROTATE);
		assertNotNull(Move.DOWN);
		assertNotNull(Move.DROP);
	}
}

