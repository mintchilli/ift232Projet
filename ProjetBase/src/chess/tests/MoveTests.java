package chess.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chess.ChessBoard;
import chess.ChessGame;

public class MoveTests {

	private ChessGame game;

	@Before
	public void setup() {

	}

	@Test
	public void testBasicCollision() throws Exception {
		game = new ChessGame();
		game.setBoard(ChessBoard.readFromFile("boards/normalStart"));
		ChessBoard result = ChessBoard.readFromFile("boards/normalStart");
		// Move tower over a pawn of the same color
		game.movePiece("a1-a2");
		assertTrue(game.compareBoard(result));
	}
	
	@Test
	public void testPawnMoves() throws Exception {
		game = new ChessGame();
		
		game.setBoard(ChessBoard.readFromFile("boards/piecestarts/pawn"));
		ChessBoard result = ChessBoard.readFromFile("boards/moves/pawn/move");
		game.movePiece("d4-d5");
		assertTrue(game.compareBoard(result));
		
		game.setBoard(ChessBoard.readFromFile("boards/piecestarts/pawn"));
		result = ChessBoard.readFromFile("boards/piecestarts/pawn");
		game.movePiece("d4-d3");
		assertTrue(game.compareBoard(result));
	}
	
	@Test
	public void testRookMoves() throws Exception {
		game = new ChessGame();
		game.setBoard(ChessBoard.readFromFile("boards/piecestarts/rook"));
		ChessBoard result = ChessBoard.readFromFile("boards/moves/rook/moveRight3");
		game.movePiece("d4-g4");
		assertTrue(game.compareBoard(result));
		
		game.setBoard(ChessBoard.readFromFile("boards/piecestarts/rook"));
		result = ChessBoard.readFromFile("boards/moves/rook/moveUp3");
		game.movePiece("d4-d7");
		assertTrue(game.compareBoard(result));
		
		game.setBoard(ChessBoard.readFromFile("boards/piecestarts/rook"));
		result = ChessBoard.readFromFile("boards/piecestarts/rook");
		game.movePiece("d4-e5");
		assertTrue(game.compareBoard(result));
		
	}
	
	@Test
	public void testKnightMoves() throws Exception {
		game = new ChessGame();
		game.setBoard(ChessBoard.readFromFile("boards/piecestarts/knight"));
		ChessBoard result = ChessBoard.readFromFile("boards/moves/knight/up2");
		game.movePiece("d4-c6");
		assertTrue(game.compareBoard(result));
		
		game.setBoard(ChessBoard.readFromFile("boards/piecestarts/knight"));
		result = ChessBoard.readFromFile("boards/moves/knight/right2");
		game.movePiece("d4-f5");
		assertTrue(game.compareBoard(result));
		
		game.setBoard(ChessBoard.readFromFile("boards/piecestarts/knight"));
		result = ChessBoard.readFromFile("boards/piecestarts/knight");
		game.movePiece("d4-e5");
		assertTrue(game.compareBoard(result));

	}
	
	@Test
	public void testBishopMoves() throws Exception {
		game = new ChessGame();
		game.setBoard(ChessBoard.readFromFile("boards/piecestarts/bishop"));
		ChessBoard result = ChessBoard.readFromFile("boards/moves/bishop/diagLeft2");
		game.movePiece("d4-b6");
		assertTrue(game.compareBoard(result));
		
		game.setBoard(ChessBoard.readFromFile("boards/piecestarts/bishop"));
		result = ChessBoard.readFromFile("boards/moves/bishop/diagRight2");
		game.movePiece("d4-f6");
		assertTrue(game.compareBoard(result));
		
		game.setBoard(ChessBoard.readFromFile("boards/piecestarts/bishop"));
		result = ChessBoard.readFromFile("boards/piecestarts/bishop");
		game.movePiece("d4-e4");
		assertTrue(game.compareBoard(result));
	}
	
	@Test
	public void testKingMoves() throws Exception {
		game = new ChessGame();
		game.setBoard(ChessBoard.readFromFile("boards/piecestarts/king"));
		ChessBoard result = ChessBoard.readFromFile("boards/moves/king/right1");
		game.movePiece("d4-e4");
		assertTrue(game.compareBoard(result));
		
		game.setBoard(ChessBoard.readFromFile("boards/piecestarts/king"));
		result = ChessBoard.readFromFile("boards/moves/king/up1");
		game.movePiece("d4-d5");
		assertTrue(game.compareBoard(result));
		
		game.setBoard(ChessBoard.readFromFile("boards/piecestarts/king"));
		result = ChessBoard.readFromFile("boards/piecestarts/king");
		game.movePiece("d4-d6");
		assertTrue(game.compareBoard(result));
		
	}
	
	@Test
	public void testQueenMoves() throws Exception {
		game = new ChessGame();
		game.setBoard(ChessBoard.readFromFile("boards/piecestarts/queen"));
		ChessBoard result = ChessBoard.readFromFile("boards/moves/queen/move2");
		game.movePiece("d4-d6");
		assertTrue(game.compareBoard(result));
		
		game.setBoard(ChessBoard.readFromFile("boards/piecestarts/queen"));
		result = ChessBoard.readFromFile("boards/moves/queen/moveDiag2");
		game.movePiece("d4-f6");
		assertTrue(game.compareBoard(result));
		
		game.setBoard(ChessBoard.readFromFile("boards/piecestarts/queen"));
		result = ChessBoard.readFromFile("boards/moves/queen/moveRight2");
		game.movePiece("d4-f4");
		assertTrue(game.compareBoard(result));
		
		game.setBoard(ChessBoard.readFromFile("boards/piecestarts/queen"));
		result = ChessBoard.readFromFile("boards/piecestarts/queen");
		game.movePiece("d4-c6");
		assertTrue(game.compareBoard(result));

	}


}
