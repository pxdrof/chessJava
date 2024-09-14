package boardgame;

public abstract class Piece {

    protected Position position;
    private Board board;

    public Piece(Board board) {
        this.board = board;
        position = null;
    }

    protected Board getBoard(){
        return board;
    }

    public abstract boolean[][] possibleMoves();

    public boolean possibleMoves(Position position){
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    public boolean isThereAnyPossibleMove(){
        /* Verifica se há posições de movimentações para a peça da jogada */
        boolean[][] matriz = possibleMoves();
        for (int icont = 0; icont < matriz.length; icont++){
            for (int jcont = 0; jcont < matriz.length; jcont++){
                if (matriz[icont][jcont]){
                    return true;
                }
            }
        }
        return false;
    }
}