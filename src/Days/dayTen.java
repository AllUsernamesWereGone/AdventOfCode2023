package Days;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class dayTen {

    //first solution 6886
    //Second solution


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
                if (maze[i][j] == 'S') {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        solution = findPath(maze, row, col, 'o'); //start direction specified through looking at the input

        //output to txt file
        saveToFile(maze);

        return solution / 2;
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
            if (maze[row][col] != 'S') {
                maze[row][col] = 'A';
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

    static char findDirection(char current, char going) {
        char direction = 'x';

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
            for (int j = 0; j < maze.length; j++)//for each column
            {
                if(!(maze[i][j] == 'A' || maze[i][j] == 'S')){
                    builder.append(" ");
                }else {
                    builder.append(maze[i][j] + "");//append to the output string
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
