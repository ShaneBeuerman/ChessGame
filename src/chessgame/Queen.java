package chessgame;

import static chessgame.Game.board;

public class Queen extends Chess_Piece{
    
    public Queen(String color, String name, String shortName, int row_Coor, int col_Coor){
        super(color, name, shortName, row_Coor, col_Coor);
    }
    
    @Override
    boolean movement(int row, int col){
        Bishop test = new Bishop(color, name, shortName, row_Coor, col_Coor);
        Rook test2 = new Rook(color, name, shortName, row_Coor, col_Coor);
        if(test.movement(row,col) || test2.movement(row,col)){
            row_Coor = row;
            col_Coor = col;
            return true;
        }
        return false;
    }
    
}
