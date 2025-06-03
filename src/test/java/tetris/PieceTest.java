package tetris;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PieceTest {

	@Test
	void givenPiece_whenRetrievingSquare_thenSucceed() {
		// given
		int width = 2;
		int height = 2;
		int[] skirt = {0,0};
		int size = 4;
		// when
		var piece = Piece.SQUARE;
		// then
		assertPiece(piece, width, height, skirt, size, "SQUARE");
		assertEquals(piece, piece.nextRotation());
	}

	@Test
	void givenPiece_whenRetrievingS_thenSucceed() {
		// given
		int width = 3;
		int height = 2;
		int[] skirt = {0,0,1};
		int size = 4;
		// when
		var piece = Piece.S;
		// then
		assertPiece(piece, width, height, skirt, size, "S");
		assertEquals(piece, piece.nextRotation().nextRotation());
	}

	@Test
	void givenPiece_whenRetrievingSTICK_thenSucceed() {
		// given
		int width = 1;
		int height = 4;
		int[] skirt = {0};
		int size = 4;
		// when
		var piece = Piece.STICK;
		// then
		assertPiece(piece, width, height, skirt, size, "STICK");
		assertEquals(piece, piece.nextRotation().nextRotation());
	}

	@Test
	void givenPiece_whenRetrievingL_thenSucceed() {
		// given
		int width = 2;
		int height = 3;
		int[] skirt = {0,0};
		int size = 4;
		// when
		var piece = Piece.L;
		// then
		assertPiece(piece, width, height, skirt, size, "L");
		assertEquals(piece, piece.nextRotation().nextRotation().nextRotation().nextRotation());
	}

	@Test
	void givenPiece_whenRetrievingL2_thenSucceed() {
		// given
		int width = 2;
		int height = 3;
		int[] skirt = {0,0};
		int size = 4;
		// when
		var piece = Piece.L2;
		// then
		assertPiece(piece, width, height, skirt, size, "L2");
		assertEquals(piece, piece.nextRotation().nextRotation().nextRotation().nextRotation());
	}

	@Test
	void givenPiece_whenRetrievingS2_thenSucceed() {
		// given
		int width = 3;
		int height = 2;
		int[] skirt = {1,0,0};
		int size = 4;
		// when
		var piece = Piece.S2;
		// then
		assertPiece(piece, width, height, skirt, size, "S2");
		assertEquals(piece, piece.nextRotation().nextRotation());
        }

	@Test
	void givenPiece_whenRetrievingPYRAMID_thenSucceed() {
		// given
		int width = 3;
		int height = 2;
		int[] skirt = {0,0,0};
		int size = 4;
		// when
		var piece = Piece.PYRAMID;
		// then
		assertPiece(piece, width, height, skirt, size, "PYRAMID");
		assertEquals(piece, piece.nextRotation().nextRotation().nextRotation().nextRotation());

	}

	private void assertPiece(Piece piece, int width, int height, int[] skirt, int size, String name) {
		printRotations(piece, name + ".....");
		assertEquals(width, piece.getWidth());
		assertEquals(height, piece.getHeight());
		assertArrayEquals(skirt, piece.getSkirt());
		assertEquals(size, piece.getBody().length);
		assertEquals(width, piece.nextRotation().getHeight());
		assertEquals(height, piece.nextRotation().getWidth());
        }


	private void printRotations(Piece piece, String message) {
		System.out.println(message);
		var rotation = piece;
		do {
			System.out.println(rotation);
			rotation = rotation.nextRotation();
		} while(!piece.equals(rotation));
		System.out.println("-----------------------------");

	}
}

