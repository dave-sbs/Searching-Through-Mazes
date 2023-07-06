/**
 * Dave Boku
 * April, 2023
 * CS 231
 * Project 7
 * Exploration.java
 * 
 * Purpose: Runs BFS, DFS, and A* Solvers
 */


 import java.util.Random;

 public class Exploration {
     public static void main(String[] args) throws InterruptedException {
         Maze maze = new Maze(25, 25, 0.20);
         AbstractMazeSearch search = new MazeDepthFirstSearch(maze);
        //  AbstractMazeSearch search = new MazeBreadthFirstSearch(maze); 
        //  AbstractMazeSearch search = new MazeAStarSearch(maze);
 
         Random rand = new Random();
 
         Cell start = maze.get(rand.nextInt(maze.getRows()), rand.nextInt(maze.getCols()));
         Cell target = maze.get(rand.nextInt(maze.getRows()), rand.nextInt(maze.getCols()));
 
         while(target.getType() == CellType.OBSTACLE){
             target = maze.get(rand.nextInt(maze.getRows()), rand.nextInt(maze.getCols()));
         }
 
         search.search(start, target, true, 20);
         
     }
 }
