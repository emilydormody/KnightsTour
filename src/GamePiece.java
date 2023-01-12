
import java.util.ArrayList;

public class GamePiece {
    private ArrayList<Square> visitedSquares;
    private Square curr;

    public GamePiece(){
        visitedSquares = new ArrayList<>();
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
        return false; }

    public boolean checkVisited(Square selected){
        return visitedSquares.contains(selected);
    }
    public void addVisited(Square selected){
        visitedSquares.add(selected);
        curr = selected;
    }
    public Square getCurr() {return curr;}
}
