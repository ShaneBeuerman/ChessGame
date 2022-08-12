package chessgame;

public class Knight extends Chess_Piece{
    
    public Knight(String color, String name, String shortName, int row_Coor, int col_Coor){
        super(color, name, shortName, row_Coor, col_Coor);
    }
    
    /*  
        Knights can move in L-shaped patterns. They can move around pieces too.
    */
    @Override
    boolean movement(int row, int col){
        int rowAbs = Math.abs(row-row_Coor);
        int colAbs = Math.abs(col-col_Coor);
        if(rowAbs == 1 && colAbs == 2){
            row_Coor = row;
            col_Coor = col;
            return true;
        }
        if(rowAbs == 2 && colAbs == 1){
            row_Coor = row;
            col_Coor = col;
            return true;
        }
        return false;
    }
}
