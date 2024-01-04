package Days;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class dFour {


    public static ArrayList<String> fileNames = new ArrayList<>();

    //start index each line = 9; zeichen 10
    // trennzeichen index = 40
    public static void start(String name) {
        fileReader(name);
        System.out.println(getCount());

    }

    public static void fileReader(String name) {
        InputStream is = dOnePartOne.class.getResourceAsStream("/Files/" + name + ".txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        //GETTING INFO FROM FILE
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

    private static int getCount() {
        int value;
        int matchingNums;
        String line;
        int[] numbers = new int[10];
        int index;
        int[] scratchCards = new int[fileNames.size()];
        Arrays.fill(scratchCards, 1);

        for (int row = 0; row < fileNames.size(); row++) {
            line = fileNames.get(row);
            index = 0;
            matchingNums = 0;

            //left side numbers before | ....
            for (int i = 10; i < 39; i += 3) {//index 40 ist trennzeichen; 10 ist startpunkt
                numbers[index] = getNum(line, i);
                index++;
            }
            for (int i = 42; i < line.length(); i += 3) {
                //System.out.println("zeichen: " + line.charAt(i) + line.charAt(i+1));
                value = getNum(line, i);
                for (int number : numbers) {
                    if (number == value) {
                        matchingNums++;
                    }
                }
            }

            //ab hier passts nicht
            if (matchingNums > 0) {
                for (int j = 0; j < scratchCards[row]; j++) {
                    for (int i = 0; i < matchingNums; i++) {
                        if (row + 1 + i < scratchCards.length) {
                            scratchCards[row + 1 + i]++;
                        }
                    }
                }
            }
        }


        return getSum(scratchCards); //980 too low
    }

    private static int getSum(int[] cards) {
        int sum = 0;
        for (int card : cards) {
            sum += card;
        }
        return sum;
    }

    private static int getNum(String line, int i) {
        if (line.charAt(i) >= '0' && line.charAt(i) <= '9') {
            return Character.getNumericValue(line.charAt(i)) * 10 + Character.getNumericValue(line.charAt(i + 1));
        } else {
            return Character.getNumericValue(line.charAt(i + 1));
        }
    }

}
