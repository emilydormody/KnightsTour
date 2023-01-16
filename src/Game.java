import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame implements ActionListener {
    private int x,y,moveCount;
    private JPanel header, gameBoard, bottom;
    private JLabel label, possible;
    private JButton reset;
    private Square[][] board;
    private GamePiece knight;

    public Game(int x, int y){
        this.x = x;
        this.y = y;
        this.setSize(600,600);
        moveCount = 0;

        knight = new GamePiece();

        header = new JPanel();
        header.setLayout(new FlowLayout());
        bottom = new JPanel();
        bottom.setLayout(new FlowLayout());
        gameBoard = new JPanel();
        gameBoard.setLayout(new GridLayout(x,y));
        gameBoard.setSize(500,500);

        label = new JLabel("Sir Lancelot, visit every square once!");
        possible = new JLabel("Pick any square to begin");
        reset = new JButton("New Game");
        reset.addActionListener(e -> newGame());

        header.add(label);
        header.add(reset);
        bottom.add(possible);

        board = new Square[x][y]; //builds the board
        for (int column = 0; column < x; column ++){
            for (int row = 0; row < y; row ++){
                board[column][row] = new Square(column,row);
                setColor(board,column,row);
                board[column][row].setSize(20, 20);
                board[column][row].setOpaque(true);
                board[column][row].setBorderPainted(false);
                board[column][row].addActionListener(this);
                gameBoard.add(board[column][row]);
                knight.addEmpty(board[column][row]);
            }}

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(header, BorderLayout.NORTH);
        getContentPane().add(gameBoard, BorderLayout.CENTER);
        getContentPane().add(bottom, BorderLayout.SOUTH);
        //pack();

        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    public void updateLabel(){
        moveCount++;
        if (moveCount == x*y) {
            label.setText("You did it!");
            possible.setText("Hooray!");}
        else {
            label.setText("Moves made: ".concat(Integer.toString(moveCount)));
            possible.setText("Possible moves: ".concat(Integer.toString(knight.countMoves())));}
    }
    public void setColor(Square[][] board,int column,int row){ //determines the colour of a Square
        if ((column+row) %2 == 0){
            board[column][row].setBackground(Color.BLACK);}
        else {board[column][row].setBackground(Color.white);}
    }
    public void newGame(){ //resets the board
        moveCount = 0;
        label.setText("Sir Lancelot, visit every square once!");
        possible.setText("Pick any square to begin");
        knight = new GamePiece();
        for (int column = 0; column < x; column ++){
            for (int row = 0; row < y; row ++){
                setColor(board,column,row);
                knight.addEmpty(board[column][row]);}}
    }

    public void actionPerformed(ActionEvent e){
        Square selected = (Square) e.getSource();
        if (knight.checkFirst()){ //checks to see if this is the first move
            selected.setBackground(Color.yellow);
            knight.addVisited(selected);
            updateLabel();}
        else if (knight.checkLegal(selected) && !knight.checkVisited(selected)){
            knight.getCurr().setBackground(Color.BLUE);
            selected.setBackground(Color.yellow);
            knight.addVisited(selected);
            updateLabel();}
        else {label.setText("You can't go there!");}
        }
}
