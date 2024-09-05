package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

    private Board board;

    public ChessMatch(){
        board = new Board(8, 8);
        initialSetup();
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

    public ChessPiece perfomChessPiece(ChessPosition sourcePosition, ChessPosition targetPosition){
        /* Localiza a posição de origem e posição que deixar mover a peça */
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        /* retorna o método que verifica se há peças na posição de origem */
        validateSourcePosition(source);
        /* Movimenta a peça */
        Piece capturedPiece = makeMove(source, target);
        /* DownCast */
        return (ChessPiece)capturedPiece;
    }

    private void validateSourcePosition(Position position){
        if(!board.thereIsAPiece(position)){
            /* Verifica se há peças na posição de origem */
            throw new ChessException("There is no piece on source position");
        }
    }

    private Piece makeMove(Position source, Position target){
        /* A variável p recebe e depois remove a peça que foi da origem */
        Piece p = board.removePiece(source);
        /* A variável capturedPiece remove a peça de destino */
        Piece capturedPiece = board.removePiece(target);
        /* A peça guardada na variável p irá ocupar a peça que foi retirada na posição de destino*/
        board.placePiece(p, target);
        return capturedPiece;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    private void initialSetup(){
        placeNewPiece('e', 8, new King(board, Color.Black));
        placeNewPiece('e', 1, new King(board, Color.White));
        placeNewPiece('c', 1, new Rook(board, Color.White));
        placeNewPiece('c', 2, new Rook(board, Color.White));
        placeNewPiece('d', 2, new Rook(board, Color.White));
        placeNewPiece('e', 2, new Rook(board, Color.White));
        placeNewPiece('d', 1, new King(board, Color.White));
        placeNewPiece('c', 7, new Rook(board, Color.Black));
        placeNewPiece('c', 8, new Rook(board, Color.Black));
        placeNewPiece('d', 7, new Rook(board, Color.Black));
        placeNewPiece('e', 7, new Rook(board, Color.Black));
        placeNewPiece('d', 8, new King(board, Color.Black));
    }
}