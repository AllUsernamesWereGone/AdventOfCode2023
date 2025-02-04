package Days;

import java.util.ArrayList;
import java.util.Arrays;

public class dayTen {


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
                if(maze[i][j] == 'S'){
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        //rekursive methode ab startpunkt schauen wohin
        //immer wieder aufrufen bis man wieder bei anfang ist
        //wert halbieren = lösung
        //4 fälle jeweils

        solution = findPath(maze, row, col, 0);


        return solution;
    }

    private static int findPath(char[][] maze, int row, int col, int counter) {

        if(counter != 0 && maze[row][col] == 'S'){
            return counter;
        }

        //an der stelle wo ich schon war X setzen

        //suchen in welche richtung man kann

        // bewegen

        //rekursion





        if(row > 0 && (maze[row-1][col] == '|' || maze[row-1][col] == '7')){ // nach oben gehen
            counter = findPath(maze, row-1, col, counter++);
        }
        if(row < maze.length && (maze[row+1][col] == '|' || maze[row+1][col] == '7')){//nach unten gehen

        }
        if(col > 0 && (maze[row-1][col] == '|' || maze[row-1][col] == '7')){//nach links gehen

        }
        if(maze[0].length && (maze[row-1][col] == '|' || maze[row-1][col] == '7')){//nach rechts gehen

        }




        return counter;
    }

}
