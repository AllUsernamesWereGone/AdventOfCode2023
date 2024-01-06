package Days;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class dEight {
    private static final ArrayList<String> fileNames = new ArrayList<>();
    private static char[] rightLeftArray;

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

    private static int getSolution() {

        generateSeparateLists();


        System.out.println("test");

        return -1;
    }

    private static void generateSeparateLists() {
        generateRightLeftArray();

        for (int i = 2; i < fileNames.size(); i++) {//start at index 2
            fileNames.get(i).toCharArray();
            fileNames.contains("AAA");
        }


    }

    private static void generateRightLeftArray() {
        rightLeftArray = new char[(fileNames.get(0)).length()];
        rightLeftArray = (fileNames.get(0)).toCharArray();
    }


}
