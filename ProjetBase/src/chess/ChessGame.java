package chess;

import java.awt.Point;

public class ChessGame {

	
	//Taille de la fenêtre
	//private int gameSizeX = 1200;
	//private int gameSizeY = 1000;
	
	//Position de l'échiquier dans la fenêtre
	//private int boardPosX = 200;
	//private int boardPosY = 100;

	//Objet racine de l'interface graphique
	//private Scene scene;

	//Dialogue utilis� pour choisir des noms de fichiers
	//private FileChooser fileDialog;

	//Panneau principal dans lequel se trouvent les éléments de jeu
	//private Pane gamePane;

	//Planche de jeu (incluant les pi�ces)
	private ChessBoard board;



	public void movePiece(String string) {
		Point start = ChessUtils.convertAlgebraicPosition(string.substring(0, 2));
		Point end = ChessUtils.convertAlgebraicPosition(string.substring(3, 5));
		
		board.move(start, end);
		
	}

	public boolean compareBoard(ChessBoard result) {
		if (board.equals(result)) {
			return true;
		}
		return false;
	}

	public ChessBoard getBoard() {
		return board;
	}

	public void setBoard(ChessBoard board) {
		this.board = board;
	}

	public void loadBoard(String string) {
		// TODO Auto-generated method stub
		
	}


}
