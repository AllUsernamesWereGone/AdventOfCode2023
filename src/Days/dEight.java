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
    private static final ArrayList<String> leftList = new ArrayList<>();
    private static final ArrayList<String> rightList = new ArrayList<>();
    private static final ArrayList<String> startList = new ArrayList<>();
    private static final int[] indexArrayStart = new int[6];
    private static final int[] indexArrayEnd = new int[6];
    private static int[] counterArray = new int[6];
    private static int result;

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

    private static String getSolution() {
        int counter = 0;
        int k = 0;
        generateSeparateLists();
        getArrayIndex("A", indexArrayStart);
        getArrayIndex("Z", indexArrayEnd);
        startList.clear();
        leftList.clear();
        rightList.clear();
        generateSeparateListsThree();


        for (int i = 0; i < 6; i++) {

            int index = indexArrayStart[i];

            while (check(index)) {
                if (rightLeftArray[k] == 'L') {
                    index = startList.indexOf(leftList.get(index));
                } else {
                    index = startList.indexOf(rightList.get(index));
                }
                counter++;
                k = counter % rightLeftArray.length;
            }
            counterArray[i] = counter;
            counter = 0;
            k = 0;
        }
        //Davon manuell kleinstes gemeinsames vielfaches berechnen
        return Arrays.toString(counterArray);





        /*
        while(check()){
            for (int i = 0; i < indexArrayStart.length; i++) {
                if (rightLeftArray[k] == 'L') {
                    indexArrayStart[i] = startList.indexOf(leftList.get(indexArrayStart[i]));
                } else {
                    indexArrayStart[i] = startList.indexOf(rightList.get(indexArrayStart[i]));
                }
                System.out.print(startList.get(indexArrayStart[i]));
                System.out.println(startList.get(indexArrayEnd[i]));
            }
            k = counter % rightLeftArray.length;
            counter++;
        }
        return Arrays.toString(counterArray);

//------------------------------------------------------------
        for (int i = 0; i < 6; i++) {

            int index = indexArrayStart[i];

            while (check(index)) {
                if (rightLeftArray[k] == 'L') {
                    index = startList.indexOf(leftList.get(index));
                } else {
                    index = startList.indexOf(rightList.get(index));
                }
                counter++;
                k = counter % rightLeftArray.length;
            }
            if(result < counter){
                result = counter;
            }
            counter = 0;
            k = 0;
        }
        return result;*/
    }

    private static void generateSeparateListsThree() {
        String line;
        for (int i = 2; i < fileNames.size(); i++) {//start at index 2
            line = fileNames.get(i);
            generateThreeListsThree(line, i - 2);
        }
    }

    private static void generateThreeListsThree(String line, int i) {

        startList.add("" + line.charAt(0) + line.charAt(1) + line.charAt(2));
        leftList.add("" + line.charAt(7) + line.charAt(8) + line.charAt(9));
        rightList.add("" + line.charAt(12) + line.charAt(13) + line.charAt(14));
    }

    private static boolean check(int j) {

        if (j == indexArrayEnd[0] || j == indexArrayEnd[1] || j == indexArrayEnd[2] ||
                j == indexArrayEnd[3] || j == indexArrayEnd[4] || j == indexArrayEnd[5]) {
            return false;
        }
        return true;
    }

    private static void getArrayIndex(String letter, int[] indexArray) {
        int j = 0;
        for (int i = 0; i < startList.size(); i++) {
            if (startList.get(i).equals(letter)) {
                indexArray[j] = i;
                j++;
            }
        }
    }

    private static void generateSeparateLists() {
        generateRightLeftArray();

        String line;
        for (int i = 2; i < fileNames.size(); i++) {//start at index 2
            line = fileNames.get(i);
            generateThreeLists(line, i - 2);
        }
    }

    private static void generateThreeLists(String line, int i) {

        startList.add("" + line.charAt(2));
        leftList.add("" + line.charAt(9));
        rightList.add("" + line.charAt(14));

    }

    private static void generateRightLeftArray() {
        rightLeftArray = new char[(fileNames.get(0)).length()];
        rightLeftArray = (fileNames.get(0)).toCharArray();
    }


}
