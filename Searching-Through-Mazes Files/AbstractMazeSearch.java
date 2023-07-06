/**
 * Dave Boku
 * April, 2023
 * CS 231
 * Project 7
 * AbstractMazeSearch.java
 * 
 * Purpose: Defines the basic functionality of any Maze Searching algorithm
 */


import java.awt.Color;
import java.awt.Graphics;



public abstract class AbstractMazeSearch {

    private Maze maze;
    private Cell start;
    private Cell target;
    private Cell cur;

    public AbstractMazeSearch(Maze maze) {
        this.maze = maze;
        this.start = null;
        this.target = null;
        this.cur = null;
    }

    /**
     * Returns the underlying maze
     * 
     * @return the underlying Maze
     */
    public Maze getMaze() {
        return maze;
    }

    /**
     * Sets the given target to be the target of the search
     * 
     * @param target
     */
    public void setTarget(Cell target) {
        this.target = target;
    }

    public Cell getTarget() {
        return target;
    }

    public void setCur(Cell cell) {
        this.cur = cell;
    }

    public Cell getCur() {
        return cur;
    }

    public void setStart(Cell start) {
        start.setPrev(start);
        this.start = start;
    }

    public Cell getStart() {
        return start;
    }

    public void reset() {
        this.cur = null;
        this.start = null;
        this.target = null;
    }

    /**
     * Finds a path from the specified cell to the start by repeatedly following the
     * prev path
     * Returns the path if found
     * 
     * @return the path if found
     */
    public LinkedList<Cell> traceback(Cell cell) {
        Cell curCell = cell;
        LinkedList<Cell> path = new LinkedList<>();

        while (curCell != null) {
            path.add(curCell);
            if (curCell == start) {
                return path;
            }
            curCell = curCell.getPrev();
        }
        return null;
    }

    public LinkedList<Cell> search(Cell start, Cell target, boolean display, int delay) throws InterruptedException{
        setStart(start);
        setTarget(target);
        setCur(start);
        addCell(start);

        if (display == true){
            MazeSearchDisplay msd = new MazeSearchDisplay(this, 20);
    

        while (numRemainingCells() != 0){
           
            if (display == true){
                Thread.sleep(delay);
                msd.repaint();
            }

            // System.out.println(start);
            // System.out.println(target);

            if (start == target){
                System.out.println(start);
            }
            cur = findNextCell();
            // if (cur.getType() ){
            //     System.out.println(cur);
            // }
            // System.out.println(numRemainingCells());

            if(cur.equals(target)){
                System.out.println(cur);
            }

            for (Cell neighbor : maze.getNeighbors(cur)){
                if (neighbor.getPrev() == null){
                    neighbor.setPrev(cur);
                    addCell(neighbor);
                    if (neighbor.equals(target)){
                        return traceback(target); 
                    }
                }
            }
        }
    }
        return null;
    }


    public void draw(Graphics g, int scale) {
        // Draws the base version of the maze
        getMaze().draw(g, scale);
        // Draws the paths taken by the searcher
        getStart().drawAllPrevs(getMaze(), g, scale, Color.RED);
        // Draws the start cell
        getStart().draw(g, scale, Color.BLUE);
        // Draws the target cell
        getTarget().draw(g, scale, Color.RED);
        // Draws the current cell
        getCur().draw(g, scale, Color.MAGENTA);
    
        // If the target has been found, draws the path taken by the searcher to reach
        // the target sans backtracking.
        if (getTarget().getPrev() != null) {
            Cell traceBackCur = getTarget().getPrev();
            while (!traceBackCur.equals(getStart())) {
                traceBackCur.draw(g, scale, Color.GREEN);
                traceBackCur = traceBackCur.getPrev();
            }
            getTarget().drawPrevPath(g, scale, Color.BLUE);
        }
    }


    /**
     * Returns the next Cell to explore
     * 
     * @return the next Cell to explore
     */
    public abstract Cell findNextCell();

    /**
     * Adds the given Cell to whatever structure is storing the future Cells to explore
     * 
     * @param next
     */
    public abstract void addCell(Cell next);


    /**
     * Returns the number of future Cells to explore
     * 
     * @return the number of future Cells to explore
     */
    public abstract int numRemainingCells();
}
