package application;

import boardgame.Piece;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {

    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    /* Cores usadas no tabuleiro */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";


    // https://stackoverflow.com/questions/2979383/java-clear-the-console
    public static void clearScreen() {
        /* Limpa a tela a cada movimentação de peça */
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static ChessPosition readChessPosition(Scanner sc){
        try{
            /* Ler a posição digitada pelo usuário, separando a primeira posição da segunda
                atribuindo a primeira a coluna e a segunda a linha
             */
            String s = sc.nextLine();
            char column = s.charAt(0);
            int row = Integer.parseInt(s.substring(1));
            return new ChessPosition(column, row);
        } catch (RuntimeException e) {
            throw new InputMismatchException("Error reading ChessPosition. Valid values are from a1 to h8");
        }
    }

    /* Imprime a paritda e não só o tabuleiro */
    public static void printMach(ChessMatch chessMatch){
        printBoard(chessMatch.getPieces());
        System.out.println();
        System.out.println("Turn: " + chessMatch.getTurn());
        System.out.println("Waiting player: " + chessMatch.getCurrentPlayer());
    }

    /* Impressão da matriz original só com as peças */
    public static void printBoard(ChessPiece[][] pieces){
        for (int icont = 0; icont < pieces.length; icont++){
            System.out.print((8 - icont) + " ");
            for (int jcont = 0; jcont < pieces.length; jcont++){
                printPiece(pieces[icont][jcont], false);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    /* Impressão da matriz original só com as peças e os movimentos possíveis */
    public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves){
        for (int icont = 0; icont < pieces.length; icont++){
            System.out.print((8 - icont) + " ");
            for (int jcont = 0; jcont < pieces.length; jcont++){
                printPiece(pieces[icont][jcont], possibleMoves[icont][jcont]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    private static void printPiece(ChessPiece piece, boolean background) {
        if (background){
            System.out.print(ANSI_BLUE_BACKGROUND);
        }
        if (piece == null) {
            System.out.print("-" + ANSI_RESET);
        }
        else {
            if (piece.getColor() == Color.Yellow) {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_GREEN + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }
}
