package tetris;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BoardTest {

	@Test
	void givenDimensions_whenInstantiating_thenSucceed() {
		// given
		var width = 10;
		var height = 20;
		// when
		var board = new Board(width, height);
		// then
		System.out.println(board);
		assertEquals(width, board.getWidth());
		assertEquals(height, board.getHeight());
		for (int i = 0; i < width; i++) {
			assertEquals(0, board.getColumnHeight(i));
		}
		for (int i = 0; i < height; i++) {
			assertEquals(0, board.getRowWidth(i));
		}
		assertEquals(0, board.getMaxHeight());		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				assertFalse(board.isFilled(x, y));
			}
		}
	}

	@Test
	void givenOutOfBoundXY_whenPlace_thenException() {
		// given
		var width = 10;
		var height = 20;
		var board = new Board(width, height);
		var piece = Piece.L;
		// when, then
		System.out.println(board);
		assertThrows(OutOfBoundPlaceException.class, () -> board.place(piece, -1, 0));
		assertThrows(OutOfBoundPlaceException.class, () -> board.place(piece, 0, -3));
		assertThrows(OutOfBoundPlaceException.class, () -> board.place(piece, -2, -2));
		assertThrows(OutOfBoundPlaceException.class, () -> board.place(piece, 12, 2));
		assertThrows(OutOfBoundPlaceException.class, () -> board.place(piece, 5, 25));
		assertThrows(OutOfBoundPlaceException.class, () -> board.place(piece, 15, 30));
		assertThrows(OutOfBoundPlaceException.class, () -> board.place(piece, 9, 0));
		assertThrows(OutOfBoundPlaceException.class, () -> board.place(piece, 0, 18));
	}

	@Test
	void givenFilledXY_whenPlace_thenException() {
		// given
		var width = 10;
		var height = 20;
		var board = new Board(width, height);
		var piece = Piece.L;
		board.place(piece, 0,0);
		// when, then
		System.out.println(board);
		assertThrows(BadPlaceException.class, () -> board.place(piece, 0, 0));
		assertThrows(BadPlaceException.class, () -> board.place(piece, 0, 1));
		assertThrows(BadPlaceException.class, () -> board.place(piece, 1, 0));
	}

	@Test
	void givenXY_whenPlace_thenSucceed() {
		// given
		var width = 10;
		var height = 20;
		var board = new Board(width, height);
		var piece = Piece.L;
		// when
		board.place(piece, 0,0);
		// then
		System.out.println(board);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if ((x == 0) && (y == 0) ||
				    (x == 0) && (y == 1) ||
				    (x == 0) && (y == 2) ||
				    (x == 1) && (y == 0)) {
					assertTrue(board.isFilled(x,y));
				    } else {
					assertFalse(board.isFilled(x, y));
				    }
			}
		}
		assertEquals(3, board.getColumnHeight(0));
		assertEquals(1, board.getColumnHeight(1));
		for (int i = 2; i < width; i++) {
		    assertEquals(0, board.getColumnHeight(i));
		}		
		assertEquals(2, board.getRowWidth(0));
		assertEquals(1, board.getRowWidth(1));
		assertEquals(1, board.getRowWidth(2));
		for (int i = 3; i < height; i++) {
		    assertEquals(0, board.getRowWidth(i));
		}
	        assertEquals(3, board.getMaxHeight());
	}

	@Test
	void givenNotCommitted_whenUndo_thenSucceed() {
		// given
		var width = 10;
		var height = 20;
		var board = new Board(width, height);
		var piece = Piece.L;
		board.place(piece, 5,5);
		System.out.println(board);
		// when
		board.undo();
		// then
		System.out.println(board);
		for (int i = 0; i < width; i++) {
			assertEquals(0, board.getColumnHeight(i));
		}
		for (int i = 0; i < height; i++) {
			assertEquals(0, board.getRowWidth(i));
		}
		assertEquals(0, board.getMaxHeight());		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				assertFalse(board.isFilled(x, y));
			}
		}
	}

	@Test
	void givenNotCommitted_whenCommit_thenSucceed() {
		// given
		var width = 10;
		var height = 20;
		var board = new Board(width, height);
		var piece = Piece.L;
		board.place(piece, 0,0);
		// when
		board.commit();
		// then
		System.out.println(board);
		board.undo();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if ((x == 0) && (y == 0) ||
				    (x == 0) && (y == 1) ||
				    (x == 0) && (y == 2) ||
				    (x == 1) && (y == 0)) {
					assertTrue(board.isFilled(x,y));
				    } else {
					assertFalse(board.isFilled(x, y));
				    }
			}
		}
	}

	@Test
	void givenPlaced_whenDropHeight_thenSucceed() {
		// given
		var width = 10;
		var height = 20;
		var board = new Board(width, height);
		var piece = Piece.L;
		board.place(piece, 4,0);
		piece = Piece.S.nextRotation();
		// when
		var result = board.dropHeight(piece, 3);
		// then
		System.out.println(board);
		assertEquals(3, result);
	}

	@Test
	void givenFilledRows_whenClearRows_thenSucceed() {
		// given
		var width = 4;
		var height = 10;
		var board = new Board(width, height);
		var stick = Piece.STICK.nextRotation();
		board.place(stick, 0,0);
		board.place(Piece.PYRAMID, 1,1);
		board.place(stick, 0,3);
		board.place(Piece.S, 0,4);
		System.out.println(board);
		// when
		var hasFilledRowsBefore = board.hasFilledRows();
		var result = board.clearRows();
		var hasFilledRowsAfter = board.hasFilledRows();
		// then
		System.out.println(board);
		assertEquals(2, result);
		assertTrue(hasFilledRowsBefore);
		assertFalse(hasFilledRowsAfter);
		assertEquals(3, board.getColumnHeight(0));
		assertEquals(4, board.getColumnHeight(1));
		assertEquals(4, board.getColumnHeight(2));
		assertEquals(1, board.getColumnHeight(3));
		assertEquals(4, board.getMaxHeight());
		assertEquals(3, board.getRowWidth(0));
		assertEquals(1, board.getRowWidth(1));
		assertEquals(2, board.getRowWidth(2));
		assertEquals(2, board.getRowWidth(3));
		assertEquals(0, board.getRowWidth(4));
		assertEquals(0, board.getRowWidth(5));
		assertEquals(0, board.getRowWidth(6));
		assertEquals(0, board.getRowWidth(7));
		assertEquals(0, board.getRowWidth(8));
		assertEquals(0, board.getRowWidth(9));
	}
}

