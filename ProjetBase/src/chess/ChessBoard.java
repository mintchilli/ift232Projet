package chess;

import java.awt.Point;
import java.io.File;
import java.util.Scanner;

import chess.ui.BoardView;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

//Représente la planche de jeu avec les pièces.

public class ChessBoard {

	// Grille de jeu 8x8 cases. Contient des références aux piéces présentes
	// sur
	// la grille.
	// Lorsqu'une case est vide, elle contient une pièce spéciale
	// (type=ChessPiece.NONE, color=ChessPiece.COLORLESS).
	private ChessPiece[][] grid;
	private BoardView boardView;

	public ChessBoard(int x, int y) {
		boardView = new BoardView(x, y);
		// Initialise la grille avec des pièces vides.
		grid = new ChessPiece[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				grid[i][j] = new ChessPiece(i, j, this);
			}
		}
	}

	// Place une pièce vide dans la case
	public void clearSquare(int x, int y) {
		grid[x][y] = new ChessPiece(x, y, this);
	}

	// Place une pièce sur le planche de jeu.
	public void putPiece(ChessPiece piece) {

		Point2D pos = boardView.gridToPane(piece, piece.getGridX(),
				piece.getGridY());
		piece.getUI().relocate(pos.getX(), pos.getY());
		getUI().getChildren().add(piece.getUI());
		grid[piece.getGridX()][piece.getGridY()] = piece;
	}

	public Pane getUI() {
		return boardView.getPane();
	}

	// Les cases vides contiennent une pièce spéciale
	public boolean isEmpty(Point pos) {
		return (grid[pos.x][pos.y].getType() == ChessUtils.TYPE_NONE);
	}

	// Vérifie si une coordonnée dans la grille est valide
	public boolean isValid(Point pos) {
		return (pos.x >= 0 && pos.x <= 7 && pos.y >= 0 && pos.y <= 7);
	}

	// Vérifie si les pièces à deux positions dans la grille sont de la même
	// couleur.
	public boolean isSameColor(Point pos1, Point pos2) {
		return grid[pos1.x][pos1.y].getColor() == grid[pos2.x][pos2.y]
				.getColor();
	}

	// Effectue un mouvement à partir de la notation algébrique des cases
	// ("e2-b5" par exemple)
	public void algebraicMove(String move) {
		if (move.length() != 5) {
			throw new IllegalArgumentException("Badly formed move");
		}
		String start = move.substring(0, 2);
		String end = move.substring(3, 5);
		move(ChessUtils.convertAlgebraicPosition(start),
				ChessUtils.convertAlgebraicPosition(end));
	}

	// Effectue un mouvement sur l'échiqier. Quelques règles de base sont
	// implantées ici.
	public boolean move(Point gridPos, Point newGridPos) {

		// Vérifie si les coordonnées sont valides
		if (!isValid(newGridPos))
			return false;

		// Si la case destination est vide, on peut faire le mouvement
		else if (isEmpty(newGridPos)) {
			grid[newGridPos.x][newGridPos.y] = grid[gridPos.x][gridPos.y];
			grid[gridPos.x][gridPos.y] = new ChessPiece(gridPos.x, gridPos.y,
					this);
			return true;
		}

		// Si elle est occuppé par une pièce de couleur différente, alors
		// c'est une capture
		else if (!isSameColor(gridPos, newGridPos)) {
			getUI().getChildren().remove(
					grid[newGridPos.x][newGridPos.y].getUI());
			grid[newGridPos.x][newGridPos.y] = grid[gridPos.x][gridPos.y];
			grid[gridPos.x][gridPos.y] = new ChessPiece(gridPos.x, gridPos.y,
					this);

			return true;
		}

		return false;
	}

	public Point2D move(Point2D gridPos, Point2D newGridPos) {
		
		Point init = boardView.paneToGrid(gridPos.getX(), gridPos.getY());
		Point end = boardView.paneToGrid(newGridPos.getX(), newGridPos.getY());

		if (move(init, end)) {

			Point2D newPos = boardView.gridToPane(grid[init.x][init.y], end.x, end.y);
			grid[init.x][init.y].setGridPos(end);
			return newPos;
			
		} else {
			Point2D oldPos = boardView.gridToPane(grid[init.x][init.y], init.x, init.y);
			return oldPos;
		}
	}

	// Fonctions de lecture et de sauvegarde d'échiquier dans des fichiers. À
	// implanter.

	public static ChessBoard readFromFile(String fileName) throws Exception {
		return readFromFile(new File(fileName), 0, 0);
	}

	public static ChessBoard readFromFile(File file, int x, int y)
			throws Exception {
		Scanner sc = new Scanner(file);
		while (sc.hasNext()) {
			ChessPiece.readFromStream(sc.nextLine());
			//String s = sc.nextLine();
			//int colonne = s.charAt(0) - 96;
			//int ligne = s.charAt(1) - 48;
			//grid[colonne][ligne] = new ChessPiece(colonne, ligne, this);
		}
		sc.close();

		throw new Exception("Pas implanté");
	}

	public void saveToFile(File file) throws Exception {

		throw new Exception("Pas implanté");
	}

}
