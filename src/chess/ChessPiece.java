package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {

    private Color color;

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor(){
        return color;
    }

    protected boolean isThereOpponentPiece(Position position){
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        /* Verifica se há peças na posição da jogada e se é uma peça do jogador da rodada
         ou do oponente (verificação feita pela cor da peça) */
        return p != null && p.getColor() != color;
    }

}
