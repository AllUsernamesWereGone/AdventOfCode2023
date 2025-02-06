package Days;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class dayEleven {

    static String ende = """
            ....#........
            .........#...
            #............
            .............
            .............
            ........#....
            .#...........
            ............#
            .............
            .............
            .........#...
            #....#.......
            """;

    static final char empty = '.';
    static final String galaxy = "#";


    public static long start(ArrayList<String> puzzleInput, char[][] charInput) {

        char[][] workArray = expandUniverse(puzzleInput, charInput);


        return -1L;
    }

    private static char[][] expandUniverse(ArrayList<String> puzzleInput, char[][] charInput) {

        int[] counter = new int[2];
        counter [0] = charInput.length;
        counter [1] = charInput[0].length;
        List <Integer> indexRow = new LinkedList<>();
        List <Integer> indexCol = new LinkedList<>();

        //get amount of rows / cols to add
        for (int i = 0; i < puzzleInput.size(); i++) {
            boolean rowEmpty = true;
            boolean colEmpty = true;
            for (int j = 0; j < puzzleInput.get(0).length(); j++) {
                if(rowEmpty && puzzleInput.get(i).contains(galaxy)){
                    rowEmpty = false;
                }
                if (colEmpty && charInput[j][i] == '#'){
                    colEmpty = false;
                }
            }
            if(rowEmpty){
                counter[0]++;
                indexRow.add(i);
            }
            if(colEmpty){
                counter[1]++;
                indexCol.add(i);
            }
        }

        char[][] toReturn = new char[counter[0]][counter[1]];

        for (int i = 0; i < toReturn.length; i++) {
            for (int j = 0; j < toReturn[0].length; j++) {



            }

        }




        return new char[0][];
    }


    public static String makeString(char[] workArray) {

        StringBuilder toReturn = new StringBuilder();
        for (char c : workArray) {
            toReturn.append(c);
        }

        return toReturn.toString();
    }


}
