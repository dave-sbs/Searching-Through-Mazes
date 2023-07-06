/**
 * Dave Boku
 * April, 2023
 * CS 231
 * Project 7
 * MazeAStarSearch.java
 * 
 * Purpose: Tests MazeAStartSearch Class
 */


import java.util.Random;

public class AStartSearchTests {
    public static void main(String[] args) throws InterruptedException {
        Maze maze = new Maze(10, 10, 0);
        AbstractMazeSearch MBFS = new MazeAStarSearch(maze);

        {
            //Testing getters and setters
            Cell start = new Cell(2, 2, CellType.FREE);
            Cell target = new Cell(5, 8, CellType.OBSTACLE);
            Cell cur = new Cell(1, 3, CellType.FREE);


            //SETTERS
            MBFS.setTarget(target);
            MBFS.setStart(start);
            MBFS.setCur(cur);


            //GETTERS
            System.out.println("Current Position: ");
            System.out.println("Column: " + MBFS.getCur().getCol());
            System.out.println("Row: " + MBFS.getCur().getRow());

            System.out.println("Output should be: Col: 3, Row: 1");

            System.out.println("\n" + "Starting Position: ");
            System.out.println("Column: " + MBFS.getStart().getCol());
            System.out.println("Row: " + MBFS.getStart().getRow());

            System.out.println("Output should be: Col: 2, Row: 2");

            System.out.println("\n" + "Target Position: ");
            System.out.println("Column: " + MBFS.getTarget().getCol());
            System.out.println("Row: " + MBFS.getTarget().getRow());

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
            System.out.println(MBFS.traceback(start));
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
            MBFS.search(start, target, true, 0);
        }
        
    }

}

