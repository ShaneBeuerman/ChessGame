package chessgame;

/*

*/
public class Game {

    static Chess_Piece current;
    static int rowCoor;
    static int colCoor;
    static Chess_Piece[][] board = new Chess_Piece[8][8];
    static boolean won = false;
    
    /*
     Adds all the pieces to the 2 dimensional chess board. Each piece has a
     color, name, shortened name, and location. There are 32 pieces total on
     a 8x8 board. That means that half the board is filled at the start.
     */
    public static void initializeBoard() {
        King wK = new King("White", "King", "WK", 7, 4);
        board[7][4] = wK;

        Queen wQ = new Queen("White", "Queen", "WQ", 7, 3);
        board[7][3] = wQ;

        Bishop[] wB = new Bishop[2];
        wB[0] = new Bishop("White", "Bishop", "WB", 7, 2);
        board[7][2] = wB[0];
        wB[1] = new Bishop("White", "Bishop", "WB", 7, 5);
        board[7][5] = wB[1];

        Rook[] wR = new Rook[2];
        wR[0] = new Rook("White", "Rook", "WR", 7, 0);
        board[7][0] = wR[0];
        wR[1] = new Rook("White", "Rook", "WR", 7, 7);
        board[7][7] = wR[1];

        Knight[] wKn = new Knight[2];
        wKn[0] = new Knight("White", "Knight", "Wk", 7, 1);
        board[7][1] = wKn[0];
        wKn[1] = new Knight("White", "Knight", "Wk", 7, 6);
        board[7][6] = wKn[1];

        Pawn[] wP = new Pawn[8];
        wP[0] = new Pawn("White", "Pawn", "Wp", 6, 0);
        board[6][0] = wP[0];
        wP[1] = new Pawn("White", "Pawn", "Wp", 6, 1);
        board[6][1] = wP[1];
        wP[2] = new Pawn("White", "Pawn", "Wp", 6, 2);
        board[6][2] = wP[2];
        wP[3] = new Pawn("White", "Pawn", "Wp", 6, 3);
        board[6][3] = wP[3];
        wP[4] = new Pawn("White", "Pawn", "Wp", 6, 4);
        board[6][4] = wP[4];
        wP[5] = new Pawn("White", "Pawn", "Wp", 6, 5);
        board[6][5] = wP[5];
        wP[6] = new Pawn("White", "Pawn", "Wp", 6, 6);
        board[6][6] = wP[6];
        wP[7] = new Pawn("White", "Pawn", "Wp", 6, 7);
        board[6][7] = wP[7];

        King bK = new King("Black", "King", "BK", 0, 4);
        board[0][4] = bK;

        Queen bQ = new Queen("Black", "Queen", "BQ", 0, 3);
        board[0][3] = bQ;

        Bishop[] bB = new Bishop[2];
        bB[0] = new Bishop("Black", "Bishop", "BB", 0, 2);
        board[0][2] = bB[0];
        bB[1] = new Bishop("Black", "Bishop", "BB", 0, 5);
        board[0][5] = bB[1];

        Rook[] bR = new Rook[2];
        bR[0] = new Rook("Black", "Rook", "BR", 0, 0);
        board[0][0] = bR[0];
        bR[1] = new Rook("Black", "Rook", "BR", 0, 7);
        board[0][7] = bR[1];

        Knight[] bKn = new Knight[2];
        bKn[0] = new Knight("Black", "Knight", "Bk", 0, 1);
        board[0][1] = bKn[0];
        bKn[1] = new Knight("Black", "Knight", "Bk", 0, 6);
        board[0][6] = bKn[1];

        Pawn[] bP = new Pawn[8];
        bP[0] = new Pawn("Black", "Pawn", "Bp", 1, 0);
        board[1][0] = bP[0];
        bP[1] = new Pawn("Black", "Pawn", "Bp", 1, 1);
        board[1][1] = bP[1];
        bP[2] = new Pawn("Black", "Pawn", "Bp", 1, 2);
        board[1][2] = bP[2];
        bP[3] = new Pawn("Black", "Pawn", "Bp", 1, 3);
        board[1][3] = bP[3];        
        bP[4] = new Pawn("Black", "Pawn", "Bp", 1, 4);
        board[1][4] = bP[4];
        bP[5] = new Pawn("Black", "Pawn", "Bp", 1, 5);
        board[1][5] = bP[5];
        bP[6] = new Pawn("Black", "Pawn", "Bp", 1, 6);
        board[1][6] = bP[6];
        bP[7] = new Pawn("Black", "Pawn", "Bp", 1, 7);
        board[1][7] = bP[7];
    }


    /*
     Check if king is killed.
    */
    public static boolean win() {
        return won;
    }

    /*
     movePiece
     */
    public static boolean movePiece(int locRow, int locCol, int desRow, int desCol, String color) {
        if (locRow < 0 || locRow >= 8 || locCol < 0 || locCol >= 8 || desRow < 0 || desRow >= 8 || desCol < 0 || desCol >= 8) {
            System.out.println("Invalid move. Try Again.");
            ChessGUI.errorPopup();
            return false;
        }

        if (board[locCol][locRow] == null) {
            System.out.println("No Piece there. Try Again.");
            ChessGUI.errorPopup();
            return false;
        }
        if (!board[locCol][locRow].color.equals(color)) {
            System.out.println("Can't move opponent's piece. Try Again.");
            ChessGUI.errorPopup();
            return false;
        }
        if (board[desCol][desRow] != null && board[desCol][desRow].color.equals(color)) {
            System.out.println("Can't move to where another one of your pieces already sits. Try Again.");
            ChessGUI.errorPopup();
            return false;
        }
        
        /*
            Movement succeeds
        */
        if (board[locCol][locRow].movement(desCol, desRow)) {
            if (board[desCol][desRow] != null && board[desCol][desRow].name.equals("King")) {
                won = true;
                ChessGUI.victory();
                System.out.println(color + " wins.");
            }
            board[desCol][desRow] = board[locCol][locRow];
            board[locCol][locRow] = null;
            return true;
        }
        ChessGUI.errorPopup();
        System.out.println("Sorry, didn't work.");
        return false;
    }
}
