package Days;

import java.io.*;
import java.util.ArrayList;

import static java.io.FileReader.*;

public class dThreePartOne {

    public static ArrayList<String> fileNames = new ArrayList<>();

    public static void start(String name) {
        fileReader(name);
        //System.out.println(getCount());
        System.out.println(getGearRatio());
        //34.505.599 too low; 57.740.332 too low; 75.643.405 too low; 81.164.581 not correct; 87.231.072 not correct; 87.207.801 not correct
    }

    private static long getGearRatio() {
        String line;
        long sum = 0;
        int firstNum = 0;
        int secondNum = 0;
        int counter = 0;
        boolean check = false;
        int value = 0;
        int starCounter = 0;

        for (int row = 0; row < fileNames.size(); row++) {
            line = fileNames.get(row);
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '*') {
                    starCounter++;
                    if (hasNumberLeft(line, i)) {//i > 0 &&
                        counter++;
                        firstNum = getNumber(line, i - 3);
                    }
                    if (hasNumberRight(line, i)) { //i < line.length()-1  &&
                        counter++;
                        if (firstNum == 0) {
                            firstNum = getNumber(line, i + 1);
                        } else {
                            secondNum = getNumber(line, i + 1);
                        }
                    }

                    if (firstNum == 0 || secondNum == 0) {


                        if (row == 0) {//first row
                            String lineDown = fileNames.get(row + 1);
                            if (hasNumberTopOrBottom(lineDown, i)) {
                                counter++;
                                if (checkTwoNums(lineDown, i)) {
                                    value = 1;
                                    check = true;
                                }
                                if (firstNum == 0) {
                                    firstNum = getNumber(lineDown, i, value);
                                } else {
                                    secondNum = getNumber(lineDown, i, value);
                                }
                            }
                        } else if (row == fileNames.size() - 1) {//last row
                            String lineUp = fileNames.get(row - 1);
                            if (hasNumberTopOrBottom(lineUp, i)) {
                                counter++;
                                if (checkTwoNums(lineUp, i)) {
                                    value = 1;
                                    check = true;
                                }
                                if (firstNum == 0) {
                                    firstNum = getNumber(lineUp, i, value);
                                } else {
                                    secondNum = getNumber(lineUp, i, value);
                                }
                            }
                        } else {
                            String lineDown = fileNames.get(row + 1);
                            String lineUp = fileNames.get(row - 1);
                            if (hasNumberTopOrBottom(lineDown, i)) {
                                counter++;
                                if (checkTwoNums(lineDown, i)) {
                                    value = 1;
                                    check = true;
                                }
                                if (firstNum == 0) {
                                    firstNum = getNumber(lineDown, i, value);
                                } else {
                                    secondNum = getNumber(lineDown, i, value);
                                }
                            }
                            if (hasNumberTopOrBottom(lineUp, i)) {
                                counter++;
                                if (checkTwoNums(lineUp, i)) {
                                    value = 1;
                                    check = true;
                                }
                                if (firstNum == 0) {
                                    firstNum = getNumber(lineUp, i, value);
                                } else {
                                    secondNum = getNumber(lineUp, i, value);
                                }
                            }
                        }
                    }
                    if (firstNum != 0 && secondNum != 0) {
                        sum = sum + (long) firstNum * secondNum;
                    } else if (check && (firstNum != 0 || secondNum != 0)) {
                        sum += firstNum + secondNum;

                    }
                }
                if (counter > 2) {
                    System.out.println("ERROR");
                }
                firstNum = 0;
                secondNum = 0;
                counter = 0;
                value = 0;
                check = false;
            }
        }


        return sum;
    }

    private static boolean checkTwoNums(String line, int i) {
        if(!isNum(line, i)){
            if(isNum(line, i-1) && isNum(line, i+1)){
                return true;
            }
        }
        return false;
    }

    private static boolean isNum(String line, int index) {
        return line.charAt(index) >= '0' && line.charAt(index) <= '9';
    }

    private static boolean hasNumberLeft(String line, int index) {
        return isNum(line, index - 1);
    }

    private static boolean hasNumberRight(String line, int index) {
        return isNum(line, index + 1);
    }

    private static boolean hasNumberTopOrBottom(String line, int index) {
        for (int i = 0; i < 3; i++) {
            if (isNum(line, index - 1 + i)) {
                return true;
            }
        }
        return false;
    }

    private static int getNumber(String line, int i) {
        int counter = 0;

        if (isNum(line, i)) {//0-9
            counter = Character.getNumericValue(line.charAt(i));
            if (isNum(line, i + 1)) {//10-99
                counter = counter * 10 + Character.getNumericValue(line.charAt(i + 1));
                if (isNum(line, i + 2)) {//100-999
                    counter = counter * 10 + Character.getNumericValue(line.charAt(i + 2));
                }
            }
        }else if(isNum(line, i + 1)) {
            counter = Character.getNumericValue(line.charAt(i + 1));
            if (isNum(line, i + 2)) {
                counter = counter * 10 + Character.getNumericValue(line.charAt(i + 2));
            }
        }else if(isNum(line, i + 2)) {
            counter = Character.getNumericValue(line.charAt(i + 2));
        }

        return counter;
    }

    private static int getNumber(String line, int index, int value) {
        int ergebnis = 0;
        if (isNum(line, index)) {
            if (index - 1 >= 0 && !(isNum(line, index - 1))) { //links keine nummer -1
                ergebnis = getNumber(line, index);
            } else if (index - 2 >= 0 && !(isNum(line, index - 2))) {//links keine nummer -2
                ergebnis = getNumber(line, index - 1);
            } else {
                ergebnis = getNumber(line, index - 2);
            }
        } else if (index - 3 >= 0 && isNum(line, index - 3) && isNum(line, index - 2) && isNum(line, index - 1)) {//nummer ganz links -3
            ergebnis = getNumber(line, index - 3);
        } else if (index - 2 >= 0 && isNum(line, index - 2) && isNum(line, index - 1)) {
            ergebnis = getNumber(line, index - 2);
        } else if (index - 1 >= 0 && isNum(line, index - 1)) {
            ergebnis = getNumber(line, index - 1);
        } else if (index + 1 < line.length() - 1 && isNum(line, index + 1)) {
            ergebnis = getNumber(line, index + 1);
        }
        if (value == 1) {
            ergebnis *= getNumber(line, index + 1);
        }


        return ergebnis;
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

    public static int getCount() {
        //too low 441812, 552061
        String line;
        int linesum = 0;
        int counter = 0;
        int digits = 0;
        int sum = 0;
        boolean leftSpace = false;
        boolean rightSpace = false;
        boolean valid = false;
        for (int row = 0; row < fileNames.size(); row++) {//game 1-100; i=0 -> game 1
            line = fileNames.get(row);
            linesum += line.length();

            for (int i = 0; i < line.length(); i++) {

                if (line.charAt(i) >= '0' && line.charAt(i) <= '9') {//0-9
                    counter = Character.getNumericValue(line.charAt(i));
                    digits = 1;
                    if (line.charAt(i + 1) >= '0' && line.charAt(i + 1) <= '9') {//10-99
                        counter = Character.getNumericValue(line.charAt(i)) * 10 + Character.getNumericValue(line.charAt(i + 1));

                        digits = 2;
                        if (line.charAt(i + 2) >= '0' && line.charAt(i + 2) <= '9') {//100-999
                            counter = Character.getNumericValue(line.charAt(i)) * 100 +
                                    Character.getNumericValue(line.charAt(i + 1)) * 10 +
                                    Character.getNumericValue(line.charAt(i + 2));
                            digits = 3;
                        }
                    }
                }
                if (counter != 0) {
                    //left side of number
                    if (i > 0) {
                        leftSpace = true;
                        if (line.charAt(i - 1) != '.') {
                            valid = true;
                        }
                    }
                    //right side of number
                    if (!(i + digits >= line.length()) && !valid) {
                        rightSpace = true;
                        if (line.charAt(i + digits) != '.') {
                            valid = true;
                        }
                    }

                    int start = 0;
                    int end = i + digits;
                    if (leftSpace) {
                        start = i - 1;
                    }
                    if (rightSpace) {
                        end = i + digits + 1;
                    }

                    if (!valid) {
                        if (row == 0) {//first row
                            String line1 = fileNames.get(row + 1);
                            for (; start < end; start++) {
                                if (line1.charAt(start) != '.') {
                                    valid = true;
                                    break;
                                }
                            }
                        } else if (row == fileNames.size() - 1) {//last row
                            String line2 = fileNames.get(row - 1);
                            for (; start < end; start++) {
                                if (line2.charAt(start) != '.') {
                                    valid = true;
                                    break;
                                }
                            }
                        } else {
                            String line1 = fileNames.get(row + 1);
                            String line2 = fileNames.get(row - 1);
                            for (; start < end; start++) {
                                if (line1.charAt(start) != '.') {
                                    valid = true;
                                    break;
                                }
                                if (line2.charAt(start) != '.') {
                                    valid = true;
                                    break;
                                }
                            }
                        }
                    }


                    if (valid) {
                        sum += counter;
                    }
                    if (digits == 2) {
                        i++;
                    } else if (digits == 3) {
                        i += 2;
                    }
                }
                valid = false;
                leftSpace = false;
                rightSpace = false;
                counter = 0;
                digits = 0;
            }
        }
        System.out.println("Summe Zeichen: " + linesum);
        return sum;
    }
}
