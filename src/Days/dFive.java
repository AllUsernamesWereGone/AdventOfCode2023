package Days;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class dFive {


    private static final ArrayList<String> fileNames = new ArrayList<>();
    private static long[] seeds;
    private static boolean[] seedStatus;
    private static final long[] mapsValues = new long[5];

    //start index each line = 9; zeichen 10
    // trennzeichen index = 40
    public static void start(String name) {
        fileReader(name);
        System.out.println(getSolution());
    }

    public static void fileReader(String name) {
        InputStream is = dOnePartOne.class.getResourceAsStream("/Files/" + name + ".txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        //GETTING INFO FROM FILE
        String line;
        try {
            fileNames.clear();
            while ((line = br.readLine()) != null) {
                fileNames.add(line);
            }
            br.close();
        } catch (
                IOException e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }

    }

    private static long getSolution() {
        String line;
        int map = 0;

        for (int row = 0; row < fileNames.size(); row++) {
            line = fileNames.get(row);
            if (row == 0) {
                seeds = new long[getSeedCount(line)];
                seedStatus = new boolean[getSeedCount(line)];
                getSeeds(line, seeds);
                System.out.println("Anzahl Versuche: " + getAmount());

            } else {
                if (line.isEmpty()) {
                    row++;
                    Arrays.fill(seedStatus, false);
                } else {
                    fillMapValues(line);

                    for (int i = 0; i < seeds.length; i += 2) {

                        if (seeds[i] >= mapsValues[2] && seeds[i] <= mapsValues[3] && !seedStatus[i]) {
                            //Werte ändern
                            seeds[i] = seeds[i] - (mapsValues[2] - mapsValues[0]);
                            seedStatus[i] = true;
                            if (seeds[i] > mapsValues[1]) {
                                System.out.println("da passt was net");
                            }
                        }
                    }
                }
            }
        }
        return lowestNumber();//496560890 too low;
        //zu testen: 510109797 = richtig
    }

    private static long lowestNumber() {
        long value = Long.MAX_VALUE;
        for (int i = 0; i < seeds.length; i++) {
            if (seeds[i] < value) {
                value = seeds[i];
            }
        }

        return value;
    }

    private static int getSeedCount(String line) {
        int counter = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ' ') {
                counter++;
            }
        }
        return counter;
    }

    private static void getSeeds(String line, long[] seeds) {
        int j = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ' ') {
                seeds[j++] = getNumber(line, i + 1);
            }
        }
    }

    private static long getNumber(String line, int i) {
        long value = 0;

        for (int j = i; j < line.length(); j++) {
            if (line.charAt(j) != ' ') {
                value = value * 10 + Character.getNumericValue(line.charAt(j));
            } else {
                break;
            }
        }
        return value;
    }

    private static void fillMapValues(String line) {
        long num1, num2, num3;
        int counter = 0;
        num2 = -1;
        num3 = -1;

        num1 = getNumber(line, 0);

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ' ') {
                if (counter == 0) {
                    num2 = getNumber(line, i + 1);
                    counter++;
                } else {
                    num3 = getNumber(line, i + 1);
                }
            }
        }

        mapsValues[0] = num1;
        mapsValues[2] = num2;
        mapsValues[4] = num3;
        mapsValues[1] = num1 + num3 - 1;
        mapsValues[3] = num2 + num3 - 1;

        if (num2 == -1 || num3 == -1) {
            System.out.println("ERROR");
        }
    }

    private static long getAmount() {
        long sum = 0;
        for (int i = 1; i < seeds.length; i += 2) {
            sum += seeds[i];
        }
        return sum;
    }

    private static boolean isNum(String line, int index) {
        return line.charAt(index) >= '0' && line.charAt(index) <= '9';
    }
}
