package chessgame;

import static chessgame.Game.board;

public class Bishop extends Chess_Piece {

    public Bishop(String color, String name, String shortName, int row_Coor, int col_Coor) {
        super(color, name, shortName, row_Coor, col_Coor);
    }

    @Override
    boolean movement(int row, int col) {
        int rowVal = Math.abs(row - row_Coor);
        int colVal = Math.abs(col - col_Coor);
        boolean[] bounded = {false, false, false, false};
        int down = 7-row;
        int up = 7 - down;
        int[] bounds = {0,0,0,0};
        for(int i = 1; i < 8; i++){
            if(row_Coor-i >= 0){//up
                if(col_Coor-i >= 0){//left
                    if(!bounded[0]){
                        if(board[row_Coor-i][col_Coor-i] != null){
                            bounds[0] = i;
                            bounded[0] = true;
                        }else{
                            bounds[0] = i;
                        }
                    }
                }
                if(col_Coor+i < 8){//right
                    if(!bounded[1]){
                        if(board[row_Coor-i][col_Coor+i] != null){
                            bounds[1] = i;
                            bounded[1] = true;
                        }else{
                            bounds[1] = i;
                        }
                    }
                }
            }
            if(row_Coor+i < 8){//down
                if(col_Coor-i >= 0){//left
                    if(!bounded[1]){
                        if(board[row_Coor+i][col_Coor-i] != null){
                            bounds[2] = i;
                            bounded[2] = true;
                        }else{
                            bounds[2] = i;
                        }
                    }
                }
                if(col_Coor+i < 8){//right
                    if(!bounded[3]){
                        if(board[row_Coor+i][col_Coor+i] != null){
                            bounds[3] = i;
                            bounded[3] = true;
                        }else{
                            bounds[3] = i;
                        }
                    }
                }
            }
        }
        
        if(row-row_Coor < 0 && col-col_Coor < 0){//up left
            if(rowVal > bounds[0]){
                return false;
            }
        }
        if(row-row_Coor > 0 && col-col_Coor > 0){ //down right
            if(rowVal > bounds[3]){
                return false;
            }
        }
        if(row-row_Coor < 0 && col-col_Coor > 0){//up right
            if(rowVal > bounds[1]){
                return false;
            }
        }
        if(row-row_Coor > 0 && col-col_Coor < 0){ // down left
            if(rowVal > bounds[2]){
                return false;
            }
        }
        
        if (rowVal != colVal) {
            return false;
        }
        row_Coor = row;
        col_Coor = col;
        return true;
    }
    
}
