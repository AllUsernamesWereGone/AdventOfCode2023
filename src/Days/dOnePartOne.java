package Days;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class dOnePartOne {
    public static ArrayList<String> fileNames = new ArrayList<>();

    public static void start() {
        fileReader();
        System.out.println(getCount());

    }

    public static void fileReader() {
        InputStream is = dOnePartOne.class.getResourceAsStream("/Files/inputDay1.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        //GETTING TILE NAMES AND COLLISION INFO FROM FILE
        String line;
        try {
            while ((line = br.readLine()) != null) {
                fileNames.add(line);
            }
            br.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }

    public static int getCount() {
        int numLeft = 0;
        int numRight = 0;
        int number = 0;
        int result = 0;


        for (String line : fileNames) {
            for (int j = 0; j < line.length(); j++) { //search from left
                if (line.charAt(j) >= '0' && line.charAt(j) <= '9') {
                    numLeft = Character.getNumericValue(line.charAt(j));
                    break;
                }
                if (checkWrittenNum(j,line) != -1) {
                    numLeft = checkWrittenNum(j,line);
                    break;
                }
            }

            for (int j = line.length() - 1; j >= 0; j--) { //search from right
                if (line.charAt(j) >= '0' && line.charAt(j) <= '9') {
                    numRight = Character.getNumericValue(line.charAt(j));
                    break;
                }
                if (checkWrittenNum(j,line) != -1) {
                    numRight = checkWrittenNum(j,line);
                    break;
                }
            }


            number = numLeft * 10 +numRight;

            result += number;
            numLeft = 0;
            numRight = 0;

        }

        return result;

        //not part two
        //26788 too low
        //82743 too high
    }

    public static int checkWrittenNum(int index, String line) {
        /*
        one     3
        two     3
        six     3

        four    4
        five    4
        nine    4

        three   5
        seven   5
        eight   5

        o,t,f,s,e,n
         */
        if (line.length() - index >= 3) { //one     3    two     3   six     3
            if (line.charAt(index) == 'o' &&
                    line.charAt(index + 1) == 'n' &&
                    line.charAt(index + 2) == 'e') {
                return 1;
            }
            if (line.charAt(index) == 't' &&
                    line.charAt(index + 1) == 'w' &&
                    line.charAt(index + 2) == 'o') {
                return 2;
            }
            if (line.charAt(index) == 's' &&
                    line.charAt(index + 1) == 'i' &&
                    line.charAt(index + 2) == 'x') {
                return 6;
            }
        }
        if (line.length() - index >= 4) {//four    4 five    4   nine    4
            if (line.charAt(index) == 'f' &&
                    line.charAt(index + 1) == 'o' &&
                    line.charAt(index + 2) == 'u' &&
                    line.charAt(index + 3) == 'r') {
                return 4;
            }
            if (line.charAt(index) == 'f' &&
                    line.charAt(index + 1) == 'i' &&
                    line.charAt(index + 2) == 'v' &&
                    line.charAt(index + 3) == 'e') {
                return 5;
            }
            if (line.charAt(index) == 'n' &&
                    line.charAt(index + 1) == 'i' &&
                    line.charAt(index + 2) == 'n' &&
                    line.charAt(index + 3) == 'e') {
                return 9;
            }
        }
        if (line.length() - index >= 5) {//three   5    seven   5   eight   5
            if (line.charAt(index) == 't' &&
                    line.charAt(index + 1) == 'h' &&
                    line.charAt(index + 2) == 'r' &&
                    line.charAt(index + 3) == 'e' &&
                    line.charAt(index + 4) == 'e') {
                return 3;
            }
            if (line.charAt(index) == 's' &&
                    line.charAt(index + 1) == 'e' &&
                    line.charAt(index + 2) == 'v' &&
                    line.charAt(index + 3) == 'e' &&
                    line.charAt(index + 4) == 'n') {
                return 7;
            }
            if (line.charAt(index) == 'e' &&
                    line.charAt(index + 1) == 'i' &&
                    line.charAt(index + 2) == 'g' &&
                    line.charAt(index + 3) == 'h' &&
                    line.charAt(index + 4) == 't') {
                return 8;
            }
        }


        return -1;
    }

}
