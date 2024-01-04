package Days;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class dEight {
    private static final ArrayList<String> fileNames = new ArrayList<>();

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

    private static int getSolution(){

        return -1;
    }



}
