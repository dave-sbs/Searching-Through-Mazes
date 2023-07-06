/**
 * Dave Boku
 * April, 2023
 * CS 231
 * Project 7
 * MazeDepthFirstSearch.java
 * 
 * Purpose: Implements Depth First Search to reach the target
 */


public class MazeDepthFirstSearch extends AbstractMazeSearch{

    private Stack<Cell> stack;

    public MazeDepthFirstSearch(Maze maze){
        super(maze);
        stack = new LinkedList<>();
    }

    @Override
    public Cell findNextCell() {
        Cell cell = stack.pop();
        return cell;
    }

    @Override
    public void addCell(Cell next) {
        stack.push(next);
    }

    @Override
    public int numRemainingCells() {
        return stack.size();
    }
    
}
