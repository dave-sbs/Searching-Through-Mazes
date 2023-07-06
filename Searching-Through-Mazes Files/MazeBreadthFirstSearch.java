/**
 * Dave Boku
 * April, 2023
 * CS 231
 * Project 7
 * MazeBreadthFirstSearch.java
 * 
 * Purpose: Implements Breadth First Search to reach the target
 */

 public class MazeBreadthFirstSearch extends AbstractMazeSearch{
    
    private Queue<Cell> queue;

    public MazeBreadthFirstSearch(Maze maze){
        super(maze);
        queue = new LinkedList<>();
    }

    @Override
    public Cell findNextCell() {
        return queue.poll();
    }

    @Override
    public void addCell(Cell next) {
        queue.offer(next);
    }

    @Override
    public int numRemainingCells() {
        return queue.size();
    }
    
}
