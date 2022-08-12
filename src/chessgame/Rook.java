package chessgame;

import static chessgame.Game.board;

public class Rook extends Chess_Piece{
    
    public Rook(String color, String name, String shortName, int row_Coor, int col_Coor){
        super(color, name, shortName, row_Coor, col_Coor);
    }
    
    /*
        Rooks can move vertically and horizontally any amount they want as long as
        a piece isn't in the way.
    */
    @Override
    boolean movement(int row, int col){
        int[] colBounds = {0,7};
        int[] rowBounds = {0,7};
        for(int i = 0; i < 8; i++){
            if (i < col_Coor && board[row][i] != null) {
                colBounds[0] = i;
            }
            if (i < row_Coor && board[i][col] != null) {
                rowBounds[0] = i;
            }
        }
        for (int i = 7; i >= 0; i--) {
            if (i > col_Coor && board[row][i] != null) {
                colBounds[1] = i;
            }
            if (i > row_Coor && board[i][col] != null) {
                rowBounds[1] = i;
            }
        }

        if (col == col_Coor && row != row_Coor
            && row >= rowBounds[0] && row <= rowBounds[1]){
            row_Coor = row;
            return true;
        }
        if(row == row_Coor && col != col_Coor
           && col >= colBounds[0] && col <= colBounds[1]){
            col_Coor = col;
            return true;
        }
        return false;
    }
    
}
