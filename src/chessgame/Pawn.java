package chessgame;

import java.util.Scanner;
import static chessgame.Game.board;

public class Pawn extends Chess_Piece {

    Pawn(String color, String name, String shortName, int row_Coor, int col_Coor) {
        super(color, name, shortName, row_Coor, col_Coor);
    }

    @Override
    boolean movement(int row, int col) {
        if ("Queen".equals(name)) {
            return queen(row, col);
        }
        if ("Rook".equals(name)) {
            return rook(row, col);
        }
        if ("Bishop".equals(name)) {
            return bishop(row, col);
        }
        if ("Knight".equals(name)) {
            return knight(row, col);
        }
        if (null != color) switch (color) {
            case "Black":
                if (row_Coor == 1) {
                    if (col == col_Coor && (row == row_Coor + 1 || row == row_Coor + 2)) {
                        col_Coor = col;
                        row_Coor = row;
                        return true;
                    }
                }   if (col == col_Coor && row == row_Coor + 1) {
                    if (row == 7) {
                        GUIUpgrade();
                    }
                    row_Coor = row;
                    col_Coor = col;
                    return true;
                }   if(col == col_Coor-1 && row == row_Coor+1 && board[row][col] != null){
                    row_Coor = row;
                    col_Coor = col;
                return true;
                }   if(col == col_Coor+1 && row == row_Coor+1 && board[row][col] != null){
                    row_Coor = row;
                    col_Coor = col;
                    return true;
                }   break;
            case "White":
                if (row_Coor == 6) {
                    if (col == col_Coor && (row == row_Coor - 1 || row == row_Coor - 2)) {
                        col_Coor = col;
                        row_Coor = row;
                        return true;
                    }
                }   if (col == col_Coor && row == row_Coor - 1) {
                    if (row == 0) {
                        GUIUpgrade();
                    }
                    row_Coor = row;
                    col_Coor = col;
                    return true;
                }   if(col == col_Coor-1 && row == row_Coor-1 && board[row][col] != null){
                row_Coor = row;
                col_Coor = col;
                return true;
                }   if(col == col_Coor+1 && row == row_Coor-1 && board[row][col] != null){
                row_Coor = row;
                col_Coor = col;
                return true;
            }   break;
        }

        return false;
    }
    
    /*
    
    */
    boolean bishop(int row, int col) {
        int rowVal = Math.abs(row - row_Coor);
        int colVal = Math.abs(col - col_Coor);
        boolean[] bounded = {false, false, false, false};
        int down = 7 - row;
        int up = 7 - down;
        int[] bounds = {0, 0, 0, 0};
        for (int i = 1; i < 8; i++) {
            if (row_Coor - i >= 0) {//up
                if (col_Coor - i >= 0) {//left
                    if (!bounded[0]) {
                        if (board[row_Coor - i][col_Coor - i] != null) {
                            bounds[0] = i;
                            bounded[0] = true;
                        } else {
                            bounds[0] = i;
                        }
                    }
                }
                if (col_Coor + i < 8) {//right
                    if (!bounded[1]) {
                        if (board[row_Coor - i][col_Coor + i] != null) {
                            bounds[1] = i;
                            bounded[1] = true;
                        } else {
                            bounds[1] = i;
                        }
                    }
                }
            }
            if (row_Coor + i < 8) {//down
                if (col_Coor - i >= 0) {//left
                    if (!bounded[1]) {
                        if (board[row_Coor + i][col_Coor - i] != null) {
                            bounds[2] = i;
                            bounded[2] = true;
                        } else {
                            bounds[2] = i;
                        }
                    }
                }
                if (col_Coor + i < 8) {//right
                    if (!bounded[3]) {
                        if (board[row_Coor + i][col_Coor + i] != null) {
                            bounds[3] = i;
                            bounded[3] = true;
                        } else {
                            bounds[3] = i;
                        }
                    }
                }
            }
        }

        if (row - row_Coor < 0 && col - col_Coor < 0) {//up left
            if (rowVal > bounds[0]) {
                return false;
            }
        }
        if (row - row_Coor > 0 && col - col_Coor > 0) { //down right
            if (rowVal > bounds[3]) {
                return false;
            }
        }
        if (row - row_Coor < 0 && col - col_Coor > 0) {//up right
            if (rowVal > bounds[1]) {
                return false;
            }
        }
        if (row - row_Coor > 0 && col - col_Coor < 0) { // down left
            if (rowVal > bounds[2]) {
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
    
    /*
    
    */
    boolean queen(int row, int col) {
        Bishop test = new Bishop(color, name, shortName, row_Coor, col_Coor);
        Rook test2 = new Rook(color, name, shortName, row_Coor, col_Coor);
        if(test.movement(row,col) || test2.movement(row,col)){
            row_Coor = row;
            col_Coor = col;
            return true;
        }
        return false;
    }
    
    /*
    
    */
    boolean rook(int row, int col) {
        int[] colBounds = {0, 7};
        int[] rowBounds = {0, 7};
        for (int i = 0; i < 8; i++) {
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
                && row >= rowBounds[0] && row <= rowBounds[1]) {
            row_Coor = row;
            return true;
        }
        if (row == row_Coor && col != col_Coor
                && col >= colBounds[0] && col <= colBounds[1]) {
            col_Coor = col;
            return true;
        }
        return false;
    }

    /*
    
    */
    boolean knight(int x, int y) {
        int xAbs = Math.abs(x - row_Coor);
        int yAbs = Math.abs(y - col_Coor);
        if (xAbs == 1 && yAbs == 2) {
            row_Coor = x;
            col_Coor = y;
            return true;
        }
        if (xAbs == 2 && yAbs == 1) {
            row_Coor = x;
            col_Coor = y;
            return true;
        }
        return false;
    }

    /*
    
    */
    void GUIUpgrade() {
        ChessGUI.pawnUpgrade(this);

    }

    /*
    
    */
    void upgrade() {
        Scanner user = new Scanner(System.in);
        System.out.println("Upgrade to? 1 for knight, 2 for queen, 3 for rook, 4 for bishop");
        int input = user.nextInt();
        while (input <= 0 || input > 5) {
            System.out.println("Try again.");
            upgrade();
        }
        if (input == 1) {
            System.out.println("Your pawn is now a knight");
            name = "Knight";
            shortName = color.charAt(0) + "k";
        }
        if (input == 2) {
            System.out.println("Your pawn is now a queen");
            name = "Queen";
            shortName = color.charAt(0) + "Q";
        }
        if (input == 3) {
            System.out.println("Your pawn is now a rook");
            name = "Rook";
            shortName = color.charAt(0) + "R";
        }
        if (input == 4) {
            System.out.println("Your pawn is now a bishop");
            name = "Bishop";
            shortName = color.charAt(0) + "B";
        }

    }
}
