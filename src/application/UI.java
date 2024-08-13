package application;

import boardgame.Piece;
import chess.ChessMatch;
import chess.ChessPiece;

public class UI {

    public static void printBoard(ChessPiece[][] pieces){
        for (int icont = 0; icont < pieces.length; icont++){
            System.out.print((8 - icont) + " ");
            for (int jcont = 0; jcont < pieces.length; jcont++){
                printPiece(pieces[icont][jcont]);
            }
            System.out.println();
        }
        System.out.println(" a b c d e f g h");
    }

    private static void printPiece(ChessPiece piece){
        if (piece == null){
            System.out.print("-");
        } else { System.out.print(piece); }
        System.out.print(" ");
    }
}
