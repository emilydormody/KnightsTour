
import java.util.ArrayList;

public class GamePiece {
    private ArrayList<Square> visitedSquares;
    private ArrayList<Square> emptySquares;
    private Square curr;

    public GamePiece(){
        visitedSquares = new ArrayList<>();
        emptySquares = new ArrayList<>();
    }

    public boolean checkFirst(){
        return visitedSquares.size() == 0;
    }
    public boolean checkLegal(Square selected){ //compares against valid knight moves
        if (selected.getColumn() == curr.getColumn()-1){
            return selected.getRow() == curr.getRow() + 2 || selected.getRow() == curr.getRow() - 2;
        }
        else if (selected.getColumn() == curr.getColumn()+1){
            return selected.getRow() == curr.getRow() + 2 || selected.getRow() == curr.getRow() - 2;
        }
        else if (selected.getColumn() == curr.getColumn()-2){
            return selected.getRow() == curr.getRow() + 1 || selected.getRow() == curr.getRow() - 1;
        }
        else if (selected.getColumn() == curr.getColumn()+2){
            return selected.getRow() == curr.getRow() + 1 || selected.getRow() == curr.getRow() - 1;
        }
        return false;}

    public boolean checkVisited(Square selected){
        return visitedSquares.contains(selected);
    }
    public void addVisited(Square selected){
        visitedSquares.add(selected);
        emptySquares.remove(selected);
        curr = selected;
    }
    public int countMoves(){ //counts the available moves
        int possibleCount = 0;
        for (Square emptySquare : emptySquares){
            if (checkLegal(emptySquare)){
                possibleCount++;}}
        return possibleCount;}

    public Square getCurr() {return curr;}

    public void addEmpty(Square selected){ //builds a list of squares that haven't been visited
        emptySquares.add(selected);
    }
}
