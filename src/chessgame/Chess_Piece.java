package chessgame;

public class Chess_Piece {
    
    String color;
    String name;
    String shortName;
    int row_Coor;
    int col_Coor;
    
    public Chess_Piece(String color, String name, String shortName, int row_Coor, int col_Coor){
        this.color = color;
        this.name = name;
        this.shortName = shortName;
        this.row_Coor = row_Coor;
        this.col_Coor = col_Coor;
    }
    
    boolean movement(int row, int col){
        return true;
    }
}
