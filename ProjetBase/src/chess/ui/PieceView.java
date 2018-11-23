package chess.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import chess.ChessBoard;

public class PieceView {
	
	// Utilisé pour générer les noms de fichiers contenant les images des pièces.
	private static final String names[] = { "pawn", "knight", "bishop", "rook", "queen", "king" };
	private static final String prefixes[] = { "w", "b" };
	
	
	// Taille d'une pièce dans l'interface
	private static double pieceSize = 75.0;
	
	// Référence à la planche de jeu. Utilisée pour déplacer la pièce.
	private ChessBoard board;
	
	// Panneau d'interface contenant l'image de la pièce
	private Pane piecePane;
	
	private Point2D init;
	private Point2D end; 
	
	public PieceView(String name, String pos, ChessBoard b, int color, int type) {

		board = b;
		
		Image pieceImage;
		try {
			pieceImage = new Image(new FileInputStream("images/" + prefixes[color] + names[type] + ".png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		ImageView pieceView = new ImageView(pieceImage);

		pieceView.setX(0);
		pieceView.setY(0);
		pieceView.setFitHeight(pieceSize);
		pieceView.setFitWidth(pieceSize);

		pieceView.setPreserveRatio(true);
		piecePane = new Pane(pieceView);
		enableDragging(piecePane);
	}
	
	// Accesseurs divers
	public Pane getPane() {
		return piecePane;
	}
	
	// Gestionnaire d'événements pour le déplacement des pièces
	private void enableDragging(Node node) {
		final ObjectProperty<Point2D> mouseAnchor = new SimpleObjectProperty<>();


		// Lorsque la pièce est saisie, on préserve la position de départ
		node.setOnMousePressed(event -> {

			mouseAnchor.set(new Point2D(event.getSceneX(), event.getSceneY()));
			init = new Point2D(event.getSceneX(), event.getSceneY());

		});

		// À chaque événement de déplacement, on déplace la pièce et on met à
		// jour la position de départ
		node.setOnMouseDragged(event -> {
			double deltaX = event.getSceneX() - mouseAnchor.get().getX();
			double deltaY = event.getSceneY() - mouseAnchor.get().getY();
			node.relocate(node.getLayoutX() + deltaX, node.getLayoutY() + deltaY);
			node.toFront();
			mouseAnchor.set(new Point2D(event.getSceneX(), event.getSceneY()));

		});

		// Lorsqu'on relâche la pièce, le mouvement correspondant est appliqué
		// au jeu d'échecs si possible.
		// L'image de la pièce est également centrée sur la case la plus proche.
		node.setOnMouseReleased(event -> {

			end = new Point2D(event.getSceneX(), event.getSceneY());
			//board.move(init, end);
			//Point newGridPos = board.paneToGrid(event.getSceneX(), event.getSceneY());

				Point2D newGridPos = board.move(init, end);
				if (!(newGridPos.equals(init)) ) {
				
					//Point2D newPos = board.gridToPane(this, newGridPos.x, newGridPos.y);
					node.relocate(newGridPos.getX(), newGridPos.getY());
					//this.setGridPos(newGridPos);
				} else {
					//Point2D oldPos = board.gridToPane(this, getGridX(), getGridY());
					node.relocate(newGridPos.getX(), newGridPos.getY());
				}

		});
	}

}
