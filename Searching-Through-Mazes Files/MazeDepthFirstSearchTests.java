/**
 * Dave Boku
 * April, 2023
 * CS 231
 * Project 7
 * MazeDepthFirstSearchTests.java
 * 
 * Purpose: Tests MazeDepthFirstSearch Class
 */


import java.util.Random;


public class MazeDepthFirstSearchTests {
    public static void main(String[] args) throws InterruptedException {
        Maze maze = new Maze(10, 10, 0);
        AbstractMazeSearch MDFS = new MazeDepthFirstSearch(maze);

        {
            //Testing getters and setters
            Cell start = new Cell(2, 2, CellType.FREE);
            Cell target = new Cell(5, 8, CellType.OBSTACLE);
            Cell cur = new Cell(1, 3, CellType.FREE);


            //SETTERS
            MDFS.setTarget(target);
            MDFS.setStart(start);
            MDFS.setCur(cur);


            //GETTERS
            System.out.println("Current Position: ");
            System.out.println("Column: " + MDFS.getCur().getCol());
            System.out.println("Row: " + MDFS.getCur().getRow());

            System.out.println("Output should be: Col: 3, Row: 1");

            System.out.println("\n" + "Starting Position: ");
            System.out.println("Column: " + MDFS.getStart().getCol());
            System.out.println("Row: " + MDFS.getStart().getRow());

            System.out.println("Output should be: Col: 2, Row: 2");

            System.out.println("\n" + "Target Position: ");
            System.out.println("Column: " + MDFS.getTarget().getCol());
            System.out.println("Row: " + MDFS.getTarget().getRow());

            System.out.println("Output should be: Col: 8, Row: 5");
        }

        {
            //Testing Traceback
            Random rand = new Random();
 
            Cell start = maze.get(rand.nextInt(maze.getRows()), rand.nextInt(maze.getCols()));
            Cell target = maze.get(rand.nextInt(maze.getRows()), rand.nextInt(maze.getCols()));
    
            while(target.getType() == CellType.OBSTACLE){
                target = maze.get(rand.nextInt(maze.getRows()), rand.nextInt(maze.getCols()));
            }
            
            //Not sure why it returns null, bc clearly it works in search
            System.out.println(MDFS.traceback(start));
        }

        {
            //Testing Search
            Random rand = new Random();
 
            Cell start = maze.get(rand.nextInt(maze.getRows()), rand.nextInt(maze.getCols()));
            Cell target = maze.get(rand.nextInt(maze.getRows()), rand.nextInt(maze.getCols()));
    
            while(target.getType() == CellType.OBSTACLE){
                target = maze.get(rand.nextInt(maze.getRows()), rand.nextInt(maze.getCols()));
            }
    
            //Not sure why sout returns null cause it clearly works!
            MDFS.search(start, target, true, 0);
        }
        
    }

}
