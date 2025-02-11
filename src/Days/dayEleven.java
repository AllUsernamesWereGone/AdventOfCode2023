package Days;

import java.util.*;

public class dayEleven {

    static String example = """
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
    static String[] exmpl = example.split("\n");
    static final char empty = '.';
    static final char galaxy = '#';
    static final String galaxyString = "#";

    static final long SIZE_UP = 1000000-1;


    public static long start(ArrayList<String> puzzleInput, char[][] charInput) {

        char[][] workArray = expandUniverse(puzzleInput, charInput);

        /*
        //test for example array
        for (int i = 0; i < workArray.length; i++) {
            if(!ende2[i].equals(String.copyValueOf(workArray[i])))
                return -1L;
            System.out.println("L:" + ende2[i] + "\n" + "C:" + String.copyValueOf(workArray[i])+ "\n");
        }*/
        int amount = galaxyAmount(workArray);
        int pairs = (amount * (amount -1)) / 2;
        System.out.println("Galaxy amount: " + amount);
        System.out.println("Galaxy pairs: " + pairs);

        long solution1 = getSum(charInput); //workArray für 1
        // 517257274989 too high
        // 12131997 too low vergessen auf mio zu stellen
        // 44273337 too low vergessen auf mio zu stellen
        // 357134560737 korrekt





        return solution1;
    }

    private static long getSum2(char[][] workArray) {
        List<int[]> positions = new ArrayList<>();
        List<Integer> emptyRow = new ArrayList<>();
        List<Integer> emptyCol = new ArrayList<>();

        // Positionen der Galaxien finden
        for (int i = 0; i < workArray.length; i++) {
            for (int j = 0; j < workArray[i].length; j++) {
                if (workArray[i][j] == galaxy) {
                    positions.add(new int[]{i, j});
                }
            }
        }

        // Leere Zeilen & Spalten finden
        boolean[] rowHasGalaxy = new boolean[workArray.length];
        boolean[] colHasGalaxy = new boolean[workArray[0].length];

        for (int[] pos : positions) {
            rowHasGalaxy[pos[0]] = true;
            colHasGalaxy[pos[1]] = true;
        }

        for (int i = 0; i < rowHasGalaxy.length; i++) {
            if (!rowHasGalaxy[i]) emptyRow.add(i);
        }
        for (int i = 0; i < colHasGalaxy.length; i++) {
            if (!colHasGalaxy[i]) emptyCol.add(i);
        }

        // Berechnung der Gesamt-Distanz
        long totalSum = 0;
        for (int i = 0; i < positions.size(); i++) {
            for (int j = i + 1; j < positions.size(); j++) {
                int[] p1 = positions.get(i);
                int[] p2 = positions.get(j);
                int x1 = p1[0], x2 = p2[0];
                int y1 = p1[1], y2 = p2[1];

                int manhattanDistance = Math.abs(x1 - x2) + Math.abs(y1 - y2);

                // Zusätzliche Distanz durch leere Zeilen berechnen
                long extraRowDistance = 0;
                for (int row : emptyRow) {
                    if (row > Math.min(x1, x2) && row < Math.max(x1, x2)) {
                        extraRowDistance += SIZE_UP - 1;  // -1 weil die Manhattan-Distanz bereits +1 zählt
                    }
                }

                // Zusätzliche Distanz durch leere Spalten berechnen
                long extraColDistance = 0;
                for (int col : emptyCol) {
                    if (col > Math.min(y1, y2) && col < Math.max(y1, y2)) {
                        extraColDistance += SIZE_UP - 1;
                    }
                }

                long pairSum = manhattanDistance + extraRowDistance + extraColDistance;

                System.out.println("Paar: (" + x1 + "," + y1 + ") -> (" + x2 + "," + y2 + ")");
                System.out.println("Manhattan Distanz: " + manhattanDistance);
                System.out.println("Zusätzliche Zeilen-Distanz: " + extraRowDistance);
                System.out.println("Zusätzliche Spalten-Distanz: " + extraColDistance);
                System.out.println("Gesamt-Distanz für dieses Paar: " + pairSum);
                System.out.println("------------------------");

                totalSum += pairSum;
            }
        }

        return totalSum;
    }


    private static long getSum(char[][] workArray) {

        List<int[]> positions = new ArrayList<>();

        //Make a positions list
        for (int i = 0; i < workArray.length; i++) {
            for (int j = 0; j < workArray[i].length; j++) {
                if(workArray[i][j] == galaxy){
                    positions.add(new int[]{i,j});
                }
            }
        }

        List<Integer> emptyRow = new LinkedList<>();
        List<Integer> emptyCol = new LinkedList<>();

        //get amount of rows / cols which are empty
        for (int i = 0; i < workArray.length; i++) {
            boolean rowEmpty = true;
            for (int j = 0; j < workArray[i].length; j++) {
                if (workArray[i][j] == galaxy) {
                    rowEmpty = false;
                    break;
                }
            }
            if (rowEmpty) {
                emptyRow.add(i);
            }
        }
        for (int i = 0; i < workArray[0].length; i++) {
            boolean colEmpty = true;
            for (int j = 0; j < workArray.length; j++) {
                if (workArray[j][i] == galaxy) {
                    colEmpty = false;
                    break;
                }
            }

            if (colEmpty) {
                emptyCol.add(i);
            }
        }


        //manhattan distanz berechnen
        long totalSum = 0;
        for (int i = 0; i < positions.size(); i++) {
            for (int j = i + 1; j < positions.size(); j++) {
                int[] p1 = positions.get(i);
                int[] p2 = positions.get(j);
                int x1 = p1[0];
                int x2 = p2[0];
                int y1 = p1[1];
                int y2 = p2[1];
                int distance = Math.abs(x1 - x2) + Math.abs(y1 - y2);

                //how much 1000000 to add
                //calculate x1 to x2 - cols
                long xCounter = 0;
                for (int k = Math.min(x1,x2); k < Math.max(x1,x2); k++) {
                    if(emptyRow.contains(k)){
                        xCounter++;
                    }
                }

                //calculate y1 to y2 -  rows
                long yCounter = 0;
                for (int k = Math.min(y1,y2); k < Math.max(y1,y2); k++) {
                    if(emptyCol.contains(k)){
                        yCounter++;
                    }
                }
                //totalSum += distance;
                totalSum += distance + (xCounter * SIZE_UP) + (yCounter * SIZE_UP);
            }
        }

        return totalSum;

    }

    private static int galaxyAmount(char[][] workArray) {

        int counter = 0;
        for (char[] chars : workArray) {
            for (char aChar : chars) {
                if (aChar == galaxy) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private static char[][] expandUniverse(ArrayList<String> puzzleInput, char[][] charInput) {

        int[] counter = new int[2];
        counter[0] = charInput.length;
        counter[1] = charInput[0].length;
        List<Integer> indexRow = new LinkedList<>();
        List<Integer> indexCol = new LinkedList<>();

        //get amount of rows / cols to add
        for (int i = 0; i < puzzleInput.size(); i++) {
            boolean rowEmpty = true;
            boolean colEmpty = true;
            for (int j = 0; j < puzzleInput.get(0).length(); j++) {
                if (rowEmpty && puzzleInput.get(i).contains(galaxyString)) {
                    rowEmpty = false;
                }
                if (colEmpty && charInput[j][i] == galaxy) {
                    colEmpty = false;
                }
            }
            if (rowEmpty) {
                counter[0]++;
                indexRow.add(i);
            }
            if (colEmpty) {
                counter[1]++;
                indexCol.add(i);
            }
        }

        char[][] toReturn = new char[counter[0]][counter[1]];

        //make new 2D array
        int offsetRow = 0;
        int offsetCol = 0;

        for (int i = 0; i < toReturn.length; i++) {
            offsetCol = 0;
            if (offsetRow < indexRow.size() && indexRow.get(offsetRow) == i-offsetRow) {
                //fill row
                for (int k = 0; k < toReturn[0].length; k++) {
                    toReturn[i][k] = empty;
                }
                offsetRow++;
                i++;
            }

            for (int j = 0; j < toReturn[0].length; j++) {
                if (offsetCol < indexCol.size() && indexCol.get(offsetCol) == j-offsetCol) {
                    //fill column
                    for (int k = 0; k < toReturn.length; k++) {
                        toReturn[k][j] = empty;
                    }
                    offsetCol++;
                    j++;
                }
                toReturn[i][j] = charInput[i - offsetRow][j - offsetCol];
            }
        }
        return toReturn;
    }



}
