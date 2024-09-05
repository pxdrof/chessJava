package boardgame;

public class Board {

    private Integer rows;
    private Integer columns;
    private Piece[][] pieces;

    public Piece piece(Integer row, Integer column){
        if (!positionExists(row, column)){
            throw new BoardException("Position not on the board");
        }
        return pieces[row][column];
        /* Retornar a matriz informando a linha e a coluna */
    }

    public Board(Integer rows, Integer columns) {
        if (rows < 1 || columns < 1){
            throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    public Integer getRows() {
        return rows;
    }

    public Integer getColumns() {
        return columns;
    }

    public Piece piece(Position position){
        if (!positionExists(position)){
            throw new BoardException("Position not on the board");
        }
        return pieces[position.getRow()][position.getColumn()];
        /* Retornar a posição da linha e coluna da matriz */
    }

    public void placePiece(Piece piece, Position position){
        if (thereIsAPiece(position)){
            /* Retorna error caso alguma peça tente ocupar um lugar que já está sendo usado
             informando a linha e a coluna desse erro */
            throw new BoardException("There is already a piece on row " + position.getRow() + " and column " + position.getColumn());
        }
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

    public Piece removePiece(Position position){
        if(!positionExists(position)){
            /* Verifica se a posição digitada existe no tabuleiro */
            throw new BoardException("Position not on the board");
        }
        if(piece(position) ==  null){
            return null;
        }
        Piece aux = piece(position);
        aux.position = null;
        pieces[position.getRow()][position.getColumn()] = null;
        return aux;
    }

    private boolean positionExists(Integer row, Integer column){
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    public boolean positionExists(Position position){
        return positionExists(position.getRow(), position.getColumn());
    }

    public boolean thereIsAPiece(Position position){
        if (!positionExists(position)){
            /* Verifica se a posição digitada existe no tabuleiro */
            throw new BoardException("Position not on the board");
        }
        return piece(position) != null;
    }
}