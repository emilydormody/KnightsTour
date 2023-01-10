import javax.swing.*;

public class Square extends JButton {
    private int column,row;

    public Square(int column, int row){
        this.column = column;
        this.row = row;
    }

    public int getColumn() {return column;}
    public int getRow() {return row;}

}

