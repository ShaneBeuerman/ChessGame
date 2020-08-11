package chessgame;

import java.util.Scanner;
import static chessgame.Game.board;

public class Pawn extends Chess_Piece {

    Pawn(String color, String name, String shortName, int row_Coor, int col_Coor) {
        super(color, name, shortName, row_Coor, col_Coor);
    }
    
    Queen queen = new Queen(color, name, shortName, row_Coor, col_Coor);
    Rook rook = new Rook(color, name, shortName, row_Coor, col_Coor);
    Bishop bish = new Bishop(color, name, shortName, row_Coor, col_Coor);
    Knight knight = new Knight(color, name, shortName, row_Coor, col_Coor);

    @Override
    boolean movement(int row, int col) {
        if ("Queen".equals(name)) {
            queen.col_Coor = col_Coor;
            queen.row_Coor = row_Coor;
            if(queen.movement(row, col)){
                queen.col_Coor = col;
                queen.row_Coor = row;
                col_Coor = col;
                row_Coor = row;
                return true;
            }
            return false;
        }
        if ("Rook".equals(name)) {
            rook.col_Coor = col_Coor;
            rook.row_Coor = row_Coor;
            if(rook.movement(row, col)){
                rook.col_Coor = col;
                rook.row_Coor = row;
                col_Coor = col;
                row_Coor = row;
                return true;
            }
            return false;
        }
        if ("Bishop".equals(name)) {
            bish.col_Coor = col_Coor;
            bish.row_Coor = row_Coor;
            if(bish.movement(row, col)){
                bish.col_Coor = col;
                bish.row_Coor = row;
                col_Coor = col;
                row_Coor = row;
                return true;
            }
            return false;
        }
        if ("Knight".equals(name)) {
            knight.col_Coor = col_Coor;
            knight.row_Coor = row_Coor;
            if(knight.movement(row, col)){
                knight.col_Coor = col;
                knight.row_Coor = row;
                col_Coor = col;
                row_Coor = row;
                return true;
            }
            return false;
        }
        if (null != color) {
            switch (color) {
                case "Black":
                    if (row_Coor == 1) {
                        if (col == col_Coor && (row == row_Coor + 1 || row == row_Coor + 2)) {
                            col_Coor = col;
                            row_Coor = row;
                            return true;
                        }
                    }
                    if (col == col_Coor && row == row_Coor + 1) {
                        if (row == 7) {
                            GUIUpgrade();
                        }
                        row_Coor = row;
                        col_Coor = col;
                        return true;
                    }
                    if (col == col_Coor - 1 && row == row_Coor + 1 && board[row][col] != null) {
                        if (row == 7) {
                            GUIUpgrade();
                        }
                        row_Coor = row;
                        col_Coor = col;
                        return true;
                    }
                    if (col == col_Coor + 1 && row == row_Coor + 1 && board[row][col] != null) {
                        if (row == 7){
                            GUIUpgrade();
                        }
                        row_Coor = row;
                        col_Coor = col;
                        return true;
                    }
                    break;
                case "White":
                    if (row_Coor == 6) {
                        if (col == col_Coor && (row == row_Coor - 1 || row == row_Coor - 2)) {
                            col_Coor = col;
                            row_Coor = row;
                            return true;
                        }
                    }
                    if (col == col_Coor && row == row_Coor - 1) {
                        if (row == 0) {
                            GUIUpgrade();
                        }
                        row_Coor = row;
                        col_Coor = col;
                        return true;
                    }
                    if (col == col_Coor - 1 && row == row_Coor - 1 && board[row][col] != null) {
                        if(row == 0){
                            GUIUpgrade();
                        }
                        row_Coor = row;
                        col_Coor = col;
                        return true;
                    }
                    if (col == col_Coor + 1 && row == row_Coor - 1 && board[row][col] != null) {
                        if(row == 0){
                            GUIUpgrade();
                        }
                        row_Coor = row;
                        col_Coor = col;
                        return true;
                    }
                    break;
            }
        }

        return false;
    }
    
    /*
    
    */
    void GUIUpgrade() {
        ChessGUI.pawnUpgrade(this);

    }
    
}
