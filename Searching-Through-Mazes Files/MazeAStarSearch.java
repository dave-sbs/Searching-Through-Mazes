/**
 * Dave Boku
 * April, 2023
 * CS 231
 * Project 7
 * MazeAStarSearch.java
 * 
 * Purpose: Implements A Star Search to reach the target
 */


import java.util.*;

public class MazeAStarSearch extends AbstractMazeSearch {

    private PriorityQueue<Cell> priorityQueue;
    private Comparator<Cell> comparator;

    public MazeAStarSearch(Maze maze) {
        super(maze);
        this.comparator = new Comparator<Cell>() {

            public int compare(Cell cell1, Cell cell2){
                Cell target = getTarget();
                int targetRow = target.getRow() ;
                int targetCol = target.getCol() ;
                int cellOne = Math.abs(targetRow - cell1.getRow()) + Math.abs(targetCol - cell1.getCol()) + traceback(cell1).size();
                int cellTwo = Math.abs(targetRow - cell2.getRow()) + Math.abs(targetCol - cell2.getCol()) + traceback(cell2).size();

                //cellOne has higher priority (closer to target)
                if(cellOne < cellTwo){
                    return -1;
                }
                //cell 2 has higher priority (closer to target)
                else if(cellOne > cellTwo) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };
        priorityQueue = new Heap<Cell> (comparator);
    }

    @Override
    public Cell findNextCell() {
        return priorityQueue.poll();
    }

    @Override
    public void addCell(Cell next) {
        priorityQueue.offer(next);
    }

    @Override
    public int numRemainingCells() {
        return priorityQueue.size();
    }

}
