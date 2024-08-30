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

    private void placeNewPiece(char column, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }
    private void initalSetup(){
        placeNewPiece('a', 6, new Rook (board, Color.White));
        placeNewPiece('b', 1, new King (board, Color.Black));
    }
}
