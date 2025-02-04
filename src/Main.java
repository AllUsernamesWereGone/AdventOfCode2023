
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Main {

    static String current = "inputDayx";


    public static void main(String[] args) {

        long time1 = System.currentTimeMillis();

        //get input
        ArrayList<String> puzzleInput = readFile(current);

        System.out.println("Solution: " + Days.dayTen.start(puzzleInput)); //changeeeeeeeeeeeeeeee days

        getTime(time1);




        /*
        dOnePartOne.start();
        dTwoPartOne.start();
        dThreePartOne.start("inputDay3");
        dFour.start("inputDay4");
        System.out.println("Richtiger Wert:");
        dFive.start("inputDay5");
        System.out.println("Testwert:");
        dFive.start("inputDay5test");
        dSix.start("inputDay6");
        dSeven.start("inputDay7");
        System.out.println("Kein Ergebnis Day 7, weiter Day 8:");
        dEight.start("inputDay8");
        System.out.println("Vom Ergebnis das kleinste gemeinsame Vielfache berechnen.");
        */
    }

    private static void getTime(long time1) {
        long time2 = System.currentTimeMillis();
        double result = time2-time1;
        result = result /1000;
        System.out.println("Zeit (sek): "+result);
    }

    private static ArrayList <String> readFile(String name){

        ArrayList<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("src/Files/" + name +".txt"))) {
            // Read each line of the file.
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + name);
            e.printStackTrace();
        }
        return lines;
    }


}