package tetris;

import java.util.Set;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PieceFactoryTest {

	@Test
	void givenPieceFactory_whenRandomPiece_thenSucceed() {
		// given
		Set<Piece> pieces = new HashSet<>();
		for (int i = 1; i <= 100; i++) {
			pieces.add(PieceFactory.createRandomPiece());
		}
		// when, then
		assertEquals(PieceType.values().length, pieces.size());
		assertTrue(pieces.contains(PieceType.STICK.getPiece()));
		assertTrue(pieces.contains(PieceType.L.getPiece()));
		assertTrue(pieces.contains(PieceType.L2.getPiece()));
		assertTrue(pieces.contains(PieceType.SQUARE.getPiece()));
		assertTrue(pieces.contains(PieceType.S.getPiece()));
		assertTrue(pieces.contains(PieceType.S2.getPiece()));
		assertTrue(pieces.contains(PieceType.PYRAMID.getPiece()));
	}
}

