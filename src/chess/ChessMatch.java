package chess;

import boardgame.Board;

public class ChessMatch {

    private Board board;

    public ChessMatch(){
        board = new Board(8, 8);
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
}
