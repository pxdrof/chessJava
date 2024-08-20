package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

    private Board board;

    public ChessMatch(){
        board = new Board(8, 8);
        initalSetup();
    }

    public ChessPiece[][] getPieces(){
        ChessPiece[][] auxMatch = new ChessPiece[board.getRows()][board.getColumns()];
        for (int icont = 0; icont < board.getRows(); icont++){
            for (int jcont = 0; jcont < board.getColumns(); jcont++){
                auxMatch[icont][jcont] = (ChessPiece) board.piece(icont, jcont);
            }
        }
        return auxMatch;
    }

    private void initalSetup(){
        board.placePiece(new Rook (board, Color.White), new Position(2, 1));
        board.placePiece(new King (board, Color.Black), new Position(0, 1));
    }
}
