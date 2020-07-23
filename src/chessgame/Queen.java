package chessgame;

import static chessgame.Game.board;

public class Queen extends Chess_Piece{
    
    public Queen(String color, String name, String shortName, int row_Coor, int col_Coor){
        super(color, name, shortName, row_Coor, col_Coor);
    }
    
    @Override
    boolean movement(int row, int col){
        Bishop bish = new Bishop(color, name, shortName, row_Coor, col_Coor);
        Rook rook = new Rook(color, name, shortName, row_Coor, col_Coor);
        if(bish.movement(row,col) || rook.movement(row,col)){
            row_Coor = row;
            col_Coor = col;
            return true;
        }
        return false;
    }
    
}
