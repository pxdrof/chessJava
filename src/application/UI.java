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
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_WHITE = "\u001B[37m";


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

    public static void printBoard(ChessPiece[][] pieces){
        for (int icont = 0; icont < pieces.length; icont++){
            System.out.print((8 - icont) + " ");
            for (int jcont = 0; jcont < pieces.length; jcont++){
                printPiece(pieces[icont][jcont]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    private static void printPiece(ChessPiece piece) {
        if (piece == null) {
            System.out.print("-");
        }
        else {
            if (piece.getColor() == Color.White) {
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }
}
