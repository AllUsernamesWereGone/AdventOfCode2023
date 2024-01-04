package Days;

import Days.dOnePartOne;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class dTwoPartOne {

    public static ArrayList<String> fileNames = new ArrayList<>();

    public static void start() {
        fileReader();
        System.out.println(getCount());

    }

    public static void fileReader() {
        InputStream is = dOnePartOne.class.getResourceAsStream("/Files/inputDay2.txt");
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
        int sum = 0;
        String line = "";

        for (int i = 0; i < fileNames.size(); i++) {//game 1-100; i=0 -> game 1
            line = fileNames.get(i);
            //for (int j = 5; j < line.length(); j++) {

            if (i < 9) {//game 1-9
                sum += countBalls(1, line);
            } else if (i < fileNames.size() - 1) {//game 10-99
                sum += countBalls(2, line);
            } else {//game 100+
                sum += countBalls(3, line);
            }
            //}

        }
        return sum;
    }

    public static int countBalls(int num, String line) {
        int redMax = 0;
        int greenMax = 0;
        int blueMax = 0;


        int iStart = 8;
        if (num == 2) {
            iStart++;
        }
        if (num == 3) {
            iStart += 2;
        }

        int counter = 0;

        for (int i = iStart; i < line.length(); i++) {
            if (line.charAt(i) >= '0' && line.charAt(i) <= '9') {
                if (line.charAt(i + 1) >= '0' && line.charAt(i + 1) <= '9') {
                    counter = Character.getNumericValue(line.charAt(i)) * 10 + Character.getNumericValue(line.charAt(i + 1));
                    i++;
                } else {
                    counter = Character.getNumericValue(line.charAt(i));
                }
                if (line.charAt(i + 2) == 'g') {
                    if (greenMax < counter) {
                        greenMax = counter;
                        counter = 0;
                    }

                } else if (line.charAt(i + 2) == 'r') {
                    if (redMax < counter) {
                        redMax = counter;
                        counter = 0;
                    }
                } else if (line.charAt(i + 2) == 'b') {
                    if (blueMax < counter) {
                        blueMax = counter;
                        counter = 0;
                    }
                }
                i = i + 2;
            }


        }

        return (greenMax * blueMax * redMax);
    }


}
