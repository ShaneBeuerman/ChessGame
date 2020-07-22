package chessgame;

public class King extends Chess_Piece{
   
    King(String color, String name, String shortName, int row_Coor, int col_Coor){
        super(color, name, shortName, row_Coor, col_Coor);
    }
    
    @Override
    boolean movement(int row, int col){//update
        return !(Math.abs(row-row_Coor) >= 2 || Math.abs(col-col_Coor) >= 2);
    }
}
