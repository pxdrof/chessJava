package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

import java.util.ArrayList;
import java.util.List;

public class ChessMatch {

    private int turn;
    private Color currentPlayer;
    private Board board;

    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();

    public ChessMatch(){
        board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.Yellow;
        initialSetup();
    }

    public int getTurn(){
        return turn;
    }

    public Color getCurrentPlayer(){
        return currentPlayer;
    }

    public ChessPiece[][] getPieces(){
        ChessPiece[][] auxMatriz = new ChessPiece[board.getRows()][board.getColumns()];
        for (int icont = 0; icont < board.getRows(); icont++){
            for (int jcont = 0; jcont < board.getColumns(); jcont++){
                auxMatriz[icont][jcont] = (ChessPiece) board.piece(icont, jcont);
            }
        }
        return auxMatriz;
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition){
        Position position = sourcePosition.toPosition();
        /* Valida a posição de origem */
        validateSourcePosition(position);
        /* Retorna as posições possíveis para jogada */
        return board.piece(position).possibleMoves();
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        /* Localiza a posição de origem e posição que deixar mover a peça */
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        /* retorna o método que verifica se há peças na posição de origem */
        validateSourcePosition(source);
        /* Valida a posição de destino */
        validateTargetPosition(source, target);
        /* Movimenta a peça */
        Piece capturedPiece = makeMove(source, target);
        /* DownCast */
        nextTurn();
        return (ChessPiece)capturedPiece;
    }

    private void validateSourcePosition(Position position){
        if (!board.thereIsAPiece(position)){
            /* Verifica se há peças na posição de origem */
            throw new ChessException("There is no piece on source position");
        }
        if (currentPlayer != ((ChessPiece)board.piece(position)).getColor()){
            /* Verifica se o jogador da vez está correto */
            throw new ChessException("The chosen piece is not yours");
        }
        if (!board.piece(position).isThereAnyPossibleMove()){
            throw new ChessException("There is no possible moves for the chosen piece");
        }
    }

    private void validateTargetPosition(Position source, Position target){
        if (!board.piece(source).possibleMoves(target)){
            throw new ChessException("The chosen piece can't move to target position");
        }
    }

    private Piece makeMove(Position source, Position target){
        /* A variável p recebe e depois remove a peça que foi da origem */
        Piece p = board.removePiece(source);
        /* A variável capturedPiece remove a peça de destino */
        Piece capturedPiece = board.removePiece(target);
        /* A peça guardada na variável p irá ocupar a peça que foi retirada na posição de destino*/
        board.placePiece(p, target);
        if (capturedPiece != null){
            /* Remove a peça da lista de peça do tabuleiro */
            piecesOnTheBoard.remove(capturedPiece);
            /*Adciona a peça na lista de peças capturadas */
            capturedPieces.add(capturedPiece);

        }
        return capturedPiece;
    }

    private void nextTurn(){
        turn++;
        currentPlayer = (currentPlayer == Color.Yellow) ? Color.Green : Color.Yellow;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
    }

    private void initialSetup(){
        placeNewPiece('e', 8, new King(board, Color.Green));
        placeNewPiece('e', 1, new King(board, Color.Yellow));
        placeNewPiece('c', 1, new Rook(board, Color.Yellow));
        placeNewPiece('c', 2, new Rook(board, Color.Yellow));
        placeNewPiece('d', 2, new Rook(board, Color.Yellow));
        placeNewPiece('e', 2, new Rook(board, Color.Yellow));
        placeNewPiece('d', 1, new King(board, Color.Yellow));
        placeNewPiece('c', 7, new Rook(board, Color.Green));
        placeNewPiece('c', 8, new Rook(board, Color.Green));
        placeNewPiece('d', 7, new Rook(board, Color.Green));
        placeNewPiece('e', 7, new Rook(board, Color.Green));
        placeNewPiece('d', 8, new King(board, Color.Green));
    }
}