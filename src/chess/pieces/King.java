package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString(){
        return "K";
    }

    private boolean canMove(Position position){
        /* Função para ler possíveis casas próximas ao rei */
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p == null || p.getColor() != getColor();
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matriz = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);

        /* Above - Cima
         Verificar casas do tabuleiro acima do rei */
        p.setValues(position.getRow() - 1, position.getColumn());
        if(getBoard().positionExists(p) && canMove(p)){
            matriz[p.getRow()][p.getColumn()]= true;
        }

        /* Below - Baixo
         Verificar casas do tabuleiro abaixo do rei */
        p.setValues(position.getRow() + 1, position.getColumn());
        if(getBoard().positionExists(p) && canMove(p)){
            matriz[p.getRow()][p.getColumn()]= true;
        }

        /* Left - Esquerda
         Verificar casas do tabuleiro à esquerda do rei */
        p.setValues(position.getRow() - 1, position.getColumn());
        if(getBoard().positionExists(p) && canMove(p)){
            matriz[p.getRow()][p.getColumn()]= true;
        }

        /* Right - Direita
         Verificar casas do tabuleiro à direita do rei */
        p.setValues(position.getRow() + 1, position.getColumn());
        if(getBoard().positionExists(p) && canMove(p)){
            matriz[p.getRow()][p.getColumn()]= true;
        }

        /* NW - noroeste
        verificar casas do tabuleiro a noroeste do rei */
        p.setValues(position.getRow() - 1, position.getColumn() - 1);
        if(getBoard().positionExists(p) && canMove(p)){
            matriz[p.getRow()][p.getColumn()]= true;
        }

        /* NE - nordeste
        verificar casas do tabuleiro a nordeste do rei */
        p.setValues(position.getRow() - 1, position.getColumn() + 1);
        if(getBoard().positionExists(p) && canMove(p)){
            matriz[p.getRow()][p.getColumn()]= true;
        }

        /* SW - sudoeste
        verificar casas do tabuleiro a sudoeste do rei */
        p.setValues(position.getRow() + 1, position.getColumn() - 1);
        if(getBoard().positionExists(p) && canMove(p)){
            matriz[p.getRow()][p.getColumn()]= true;
        }

        /* SE - sudeste
        verificar casas do tabuleiro a sudeste do rei */
        p.setValues(position.getRow() + 1, position.getColumn() + 1);
        if(getBoard().positionExists(p) && canMove(p)){
            matriz[p.getRow()][p.getColumn()]= true;
        }

        return matriz;
    }
}
