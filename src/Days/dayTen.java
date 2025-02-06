package Days;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class dayTen {

    //first solution 6886
    //Second solution

    static final char NS = '|';
    static final char EW = '-';
    static final char SE = 218; // F
    static final char NW = 217; // J
    static final char NE = 192; // L
    static final char SW = 191; // 7
    static final char ground = '.';
    static final char start = 'S';

    public static int start(ArrayList<String> input) {
        int solution = 0;

        char[][] maze = new char[input.size()][];
        for (int i = 0; i < input.size(); i++) {
            maze[i] = input.get(i).toCharArray();
        }
        int row = 0;
        int col = 0;

        //get start point
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == start) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        solution = findPath(maze, row, col, 'u'); //start direction specified through looking at the input
        int solution2 = findPath2(maze,row,col,'u');
        //output to txt file
        //saveToFile(maze);

        //return solution / 2;
        return solution2; //365 zu low
    }

    private static int findPath(char[][] maze, int row, int col, char direction) {

        /**
         * derzeitiges symbol anschauen
         * in welche richtung muss ich?
         * in diese richtung gehen
         * vor bewegen derzeitiges symbol ändern
         * rekursiver aufruf
         * direction
         * o = oben
         * u = unten
         * l = links
         * r = rechts
         */

        int counter = 0;

        while (true) {
            if (counter > 0 && maze[row][col] == 'S') {
                return counter;
            }

            switch (direction) {
                case 'o':
                    direction = findDirection(direction, maze[row - 1][col]);
                    row--;
                    break;
                case 'u':
                    direction = findDirection(direction, maze[row + 1][col]);
                    row++;
                    break;
                case 'l':
                    direction = findDirection(direction, maze[row][col - 1]);
                    col--;
                    break;
                case 'r':
                    direction = findDirection(direction, maze[row][col + 1]);
                    col++;
                    break;
            }


            counter++;
        }

    }

    private static int findPath2(char[][] maze, int row, int col, char direction) {

        char [][] check = new char[maze.length][maze[0].length];
        for (int i = 0; i < check.length; i++) {
            for (int j = 0; j < check[0].length; j++) {
                check[i][j] = '0';
            }
        }

        int counter = 0;
        boolean flag = false;

        while (true) {
            if (counter > 0 && maze[row][col] == 'S') {
                if(flag){
                    saveToFile(check);
                    return countNumbers(check);
                }
                flag = true;

            }

            check[row][col] = '1'; //Weg

            if(flag){
                fillRight(check,row,col,direction);
                //floodFill(check,row,col);
            }

            switch (direction) {
                case 'o':
                    direction = findDirection(direction, maze[row - 1][col]);
                    row--;
                    break;
                case 'u':
                    direction = findDirection(direction, maze[row + 1][col]);
                    row++;
                    break;
                case 'l':
                    direction = findDirection(direction, maze[row][col - 1]);
                    col--;
                    break;
                case 'r':
                    direction = findDirection(direction, maze[row][col + 1]);
                    col++;
                    break;
            }


            counter++;
        }

    }

    private static void floodFill(char[][] grid, int row, int col) {
        // Check boundaries and whether the cell is fillable (i.e. not a wall)

        if (grid[row][col] == '1' || grid[row][col] == 'b') {
            return;
        }

        // Mark the cell as filled
        grid[row][col] = 'b';

        // Recurse in all four directions
        floodFill(grid, row - 1, col); // up
        floodFill(grid, row + 1, col); // down
        floodFill(grid, row, col - 1); // left
        floodFill(grid, row, col + 1); // right
    }

    private static void fillRight(char [][] maze,int row, int col, char direction){

        switch (direction) {
            case 'o':
                while(true){
                    if(maze[row][col+1] != '1') {
                        maze[row][col+1] = 'a';
                        col++;
                        floodFill(maze,row,col);
                    }else{
                        break;
                    }
                }
                break;
            case 'u':
                while(true){
                    if(maze[row][col-1] != '1') {
                        maze[row][col-1] = 'a';
                        col--;
                        floodFill(maze,row,col);
                    }else{
                        break;
                    }
                }
                break;
            case 'l':
                while(true){
                    if(maze[row-1][col] != '1') {
                        maze[row-1][col] = 'a';
                        row--;
                        floodFill(maze,row,col);
                    }else{
                        break;
                    }
                }
                break;
            case 'r':
                while(true){
                    if(maze[row+1][col] != '1') {
                        maze[row+1][col] = 'a';
                        row++;
                        floodFill(maze,row,col);
                    }else{
                        break;
                    }
                }
                break;
        }





    }

    private static int countNumbers(char[][] maze){
        int counter = 0;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if(maze[i][j] == 'b' || maze[i][j] == 'a'){
                    counter++;
                }
            }
        }
        return counter;
    }

    static char findDirection(char current, char going) {
        char direction = 'u';

        if (current == 'o') {
            if (going == '|') {
                direction = 'o';
            }
            if (going == 'F') {
                direction = 'r';
            }
            if (going == '7') {
                direction = 'l';
            }
        }
        if (current == 'u') {
            if (going == '|') {
                direction = 'u';
            }
            if (going == 'L') {
                direction = 'r';
            }
            if (going == 'J') {
                direction = 'l';
            }
        }
        if (current == 'l') {
            if (going == '-') {
                direction = 'l';
            }
            if (going == 'L') {
                direction = 'o';
            }
            if (going == 'F') {
                direction = 'u';
            }
        }
        if (current == 'r') {
            if (going == '-') {
                direction = 'r';
            }
            if (going == 'J') {
                direction = 'o';
            }
            if (going == '7') {
                direction = 'u';
            }
        }


        return direction;
    }

    private static void saveToFile(char[][] maze) {

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < maze.length; i++)//for each row
        {
            for (int j = 0; j < maze[0].length; j++)//for each column
            {

                if(maze[i][j] == '0'){
                    builder.append(" ");
                }else {
                    builder.append(maze[i][j]);//append to the output string
                }

            }
            builder.append("\n");//append new line at the end of the row
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/Files/outputtest.txt"));
            writer.write(builder.toString());//save the string representation of the board
            writer.close();
        } catch (IOException e) {
            System.out.println("ERROR making file");
        }


    }
}
