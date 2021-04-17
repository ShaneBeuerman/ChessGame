package chessgame;

import static chessgame.Game.board;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChessGUI {
    
    static int w = 800;
    static int h = 800;
    static String curColor ="White";
    
    /*
        Creates a GUI board
    */
    public static void GUIBoard() throws Exception {
        
        JFrame frame = new JFrame();
        chess chess = new chess();
        chess.start();
        
        frame.setSize(w+100, h+50);
        frame.add(chess);
        
        JLabel turnOrder = new JLabel(curColor + "'s Turn");
        JLabel start = new JLabel("Starting Position");
        JLabel end = new JLabel("Ending Position");
        JTextField rowStart = new JTextField("col");
        JTextField colStart = new JTextField("row");
        JTextField rowCoor = new JTextField("col");
        JTextField colCoor = new JTextField("row");
         
        JButton moveButton = new JButton("Move");
        
        moveButton.addActionListener((ActionEvent arg) -> {
            try {
                int row1 = Integer.parseInt(rowStart.getText());
                int col1 = Integer.parseInt(colStart.getText());
                int row2 = Integer.parseInt(rowCoor.getText());
                int col2 = Integer.parseInt(colCoor.getText());
                if(Game.movePiece(row1, col1, row2, col2, curColor)){
                    if(curColor.equals("White")){
                        curColor = "Black";
                    }   else{
                        curColor = "White";
                    }
                }
                System.out.println(curColor + "'s turn");
                turnOrder.setText(curColor + "'s turn");
                chess.boardUpdate();
            } catch(NumberFormatException e){
                errorPopup();
            }
        });
        
        JPanel panel = new JPanel();
        
        panel.add(turnOrder);
        panel.add(start);
        panel.add(rowStart);
        panel.add(colStart);
        panel.add(end);
        panel.add(rowCoor);
        panel.add(colCoor);
        panel.add(moveButton);
        
        frame.add(panel, BorderLayout.SOUTH);
        
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    /*
        Popup Message when you win.
    */
    public static void victory(){
        JOptionPane.showMessageDialog(null, "You Won!", "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /*
        Popup message when movement is bad.
    */
    public static void errorPopup(){
            JOptionPane.showMessageDialog(null, "The values you gave will not work.", "Error", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /*
        A popup comes up when the pawn gets to be upgraded.
    */
    public static void pawnUpgrade(Chess_Piece cur_Piece){
        JFrame popUp = new JFrame();
        
        JButton Qbutton = new JButton("Queen");
        Qbutton.addActionListener(new pawnPress(popUp, cur_Piece, "Queen", "Q"));
        
        JButton Rbutton = new JButton("Rook");
        Rbutton.addActionListener(new pawnPress(popUp, cur_Piece, "Rook", "R"));
        
        JButton Bbutton = new JButton("Bishop");
        Bbutton.addActionListener(new pawnPress(popUp, cur_Piece, "Bishop", "B"));
        
        JButton Kbutton = new JButton("Knight");
        Kbutton.addActionListener(new pawnPress(popUp, cur_Piece, "Knight", "k"));
        
        JLabel upgradeText = new JLabel();
        upgradeText.setText("Upgrade to which unit?");
        popUp.add(upgradeText, BorderLayout.NORTH);
        popUp.add(Qbutton, BorderLayout.SOUTH);
        popUp.add(Rbutton, BorderLayout.WEST);
        popUp.add(Bbutton, BorderLayout.EAST);
        popUp.add(Kbutton, BorderLayout.CENTER);
        popUp.pack();
        popUp.setVisible(true);
    }
    
    
    /*
        ActionListener used to upgrade pawns. It changes the pawn's name,
        short name, and what type of piece it is.
    */
    static class pawnPress implements ActionListener{
        
        JFrame window;
        String name;
        String shortName;
        Chess_Piece cur_Piece;
        pawnPress(JFrame window, Chess_Piece cur_Piece,String name, String shortName){
            this.cur_Piece = cur_Piece;
            this.name = name;
            this.shortName = shortName;
            this.window = window;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            cur_Piece.name = name;
            cur_Piece.shortName = cur_Piece.color.charAt(0) + shortName;
            window.dispose();
        }
        
    }
    
    /*
        Creates a chess board
    */
    private static class chess extends JPanel{
        
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D square = (Graphics2D) g;
            boolean red = false;
            int r = 0;
            int c = 0;
            int counter = 0;
            int col = 120;
            int row = 40;
            int asciiValue = 97;
            Integer count = 0;
            for(int i = 0; i < 8; i++){
                count = i;
                square.drawString(count.toString(), col, row);
                col+=80;
            }
            col=40;
            row=120;
            for(int i = 0; i < 8; i++){
                count = i;
                square.drawString(count.toString(), col, row);
                row+=80;
            }

            
            for (int i = 80; i < 720; i += 80) {
                for (int j = 80; j < 720; j += 80) {
                    if (red) {
                        square.setColor(Color.red);
                        red = !red;
                    } else {
                        square.setColor(Color.black);
                        red = !red;
                    }
                    square.fillRect(i, j, 80, 80);
                    square.setColor(Color.white);
                    if (board[r][c] != null) {
                        square.drawString(board[r][c].shortName, i + 32, j + 40);
                    }
                    r++;
                }
                red = !red;
                c++;
                r = 0;
            }
        }
        
        /*
            starts the game by initializing the board
        */
        public void start() throws InterruptedException {
            Game.initializeBoard();
        }
        
        /*
            only called to repaint()
        */
        public void boardUpdate(){
            repaint();
        }        
    }
}