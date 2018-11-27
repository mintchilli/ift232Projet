package chess;

import java.awt.Point;
import java.util.ArrayList;

import chess.ui.PieceView;
import javafx.scene.layout.Pane;

public class ChessPiece {

	// Position de la pièce sur l'échiquier
	private int gridPosX;
	private int gridPosY;

	private int type;
	private int color;

	private PieceView pieceView;

	// Pour créer des pièces à mettre sur les cases vides
	public ChessPiece(int x, int y) {
		this.type = ChessUtils.TYPE_NONE;
		this.color = ChessUtils.COLORLESS;
		gridPosX = x;
		gridPosY = y;
		// board = b;
	}

	// Création d'une pièce normale. La position algébrique en notation d'échecs
	// lui donne sa position sur la grille.
	public ChessPiece(String name, String pos, ChessBoard b) {

		color = ChessUtils.getColor(name);
		type = ChessUtils.getType(name);
		setAlgebraicPos(pos);
		pieceView = new PieceView(name, pos, b, color, type);
	}

	// Change la position avec la notation algébrique
	public void setAlgebraicPos(String pos) {

		Point pos2d = ChessUtils.convertAlgebraicPosition(pos);

		gridPosX = pos2d.x;
		gridPosY = pos2d.y;
	}

	// Crée la liste de pièces avec leur position de départ pour un jeu d'échecs
	// standard
	public static ArrayList<ChessPiece> createInitialPieces(ChessBoard board) {

		ArrayList<ChessPiece> pieces = new ArrayList<ChessPiece>();

		pieces.add(new ChessPiece("wr", "a1", board));
		pieces.add(new ChessPiece("wr", "h1", board));
		pieces.add(new ChessPiece("wn", "b1", board));
		pieces.add(new ChessPiece("wn", "g1", board));
		pieces.add(new ChessPiece("wb", "c1", board));
		pieces.add(new ChessPiece("wb", "f1", board));
		pieces.add(new ChessPiece("wq", "d1", board));
		pieces.add(new ChessPiece("wk", "e1", board));

		for (int i = 0; i < 8; i++) {
			pieces.add(new ChessPiece("wp", ((char) ('a' + i)) + "2", board));
		}

		pieces.add(new ChessPiece("br", "a8", board));
		pieces.add(new ChessPiece("br", "h8", board));
		pieces.add(new ChessPiece("bn", "b8", board));
		pieces.add(new ChessPiece("bn", "g8", board));
		pieces.add(new ChessPiece("bb", "c8", board));
		pieces.add(new ChessPiece("bb", "f8", board));
		pieces.add(new ChessPiece("bq", "d8", board));
		pieces.add(new ChessPiece("bk", "e8", board));

		for (int i = 0; i < 8; i++) {
			pieces.add(new ChessPiece("bp", ((char) ('a' + i)) + "7", board));
		}

		return pieces;
	}

	// Pour savoir si c'est une pièce vide (pour les cases vides de
	// l'échiquier).
	public boolean isNone() {

		return type == ChessUtils.TYPE_NONE;
	}

	// Accesseurs divers
	public Pane getUI() {
		return pieceView.getPane();
	}

	public int getType() {
		return type;
	}

	public int getColor() {
		return color;
	}

	public int getGridX() {
		return gridPosX;
	}

	public int getGridY() {
		return gridPosY;
	}

	public Point getGridPos() {
		return new Point(gridPosX, gridPosY);
	}

	public void setGridPos(Point pos) {
		gridPosX = pos.x;
		gridPosY = pos.y;
	}

	public static ChessPiece readFromStream(String line, ChessBoard board) {

		String pos = line.substring(0, 2);
		String colorType = line.substring(3, 5);

		return new ChessPiece(colorType, pos, board);

	}

	public static String SaveToStream(ChessPiece piece) {

		String ret = ChessUtils.makeAlgebraicPosition(piece.gridPosX,
				piece.gridPosY)
				+ "-"
				+ ChessUtils.makePieceName(piece.color, piece.type) + "\n";

		return ret;

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChessPiece other = (ChessPiece) obj;
		if (color != other.color)
			return false;
		if (gridPosX != other.gridPosX)
			return false;
		if (gridPosY != other.gridPosY)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	public boolean verifyMove(Point gridPos, Point newGridPos) {

		int deltax = newGridPos.x - gridPos.x;
		int deltay = newGridPos.y - gridPos.y;

		switch (this.type) {
		case ChessUtils.TYPE_PAWN:
			if (this.color == ChessUtils.BLACK) {
				if (deltax == 0 && deltay == 1) {
					return true;
				}
				return false;
			} else {
				if (deltax == 0 && deltay == -1) {
					return true;
				}
				return false;
			}
		case ChessUtils.TYPE_KNIGHT:
			if ((Math.abs(deltax) == 2 && Math.abs(deltay) == 1) || (Math.abs(deltax) == 1 && Math.abs(deltay) == 2)) {
				return true;
			}
			return false;
		case ChessUtils.TYPE_BISHOP:
			if (Math.abs(deltax) == Math.abs(deltay)) {
				return true;
			}
			return false;
		case ChessUtils.TYPE_ROOK:
			if ((Math.abs(deltax) == 0 && (Math.abs(deltay) >= 1 && Math.abs(deltay) <= 7))
					|| (Math.abs(deltay) == 0 && (Math.abs(deltax) >= 1 && Math.abs(deltax) <= 7))) {
				return true;
			}
			return false;
		case ChessUtils.TYPE_QUEEN:
			if ((Math.abs(deltax) == Math.abs(deltay)) || ((Math.abs(deltax) == 0 && (Math.abs(deltay) >= 1 && Math.abs(deltay) <= 7))
					|| (Math.abs(deltay) == 0 && (Math.abs(deltax) >= 1 && Math.abs(deltax) <= 7)))) {
				return true;
			}
			return false;
		case ChessUtils.TYPE_KING:
			if ((Math.abs(deltax) == 0 && Math.abs(deltay) == 1) || (Math.abs(deltax) == 1 && Math.abs(deltay) == 0) || (Math.abs(deltax) == 1 && Math.abs(deltay) == 1)) {
				return true;
			}
		default:
			return false;
		}
	}

}
