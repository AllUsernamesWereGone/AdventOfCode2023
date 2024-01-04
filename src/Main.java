import Days.*;

public class Main {
    public static void main(String[] args) {

        long time1 = System.currentTimeMillis();

        //dOnePartOne.start();
        //dTwoPartOne.start();
        //dThreePartOne.start("inputDay3");
        //dFour.start("inputDay4");
        /*
        System.out.println("Richtiger Wert:");
        dFive.start("inputDay5");
        System.out.println("Testwert:");
        dFive.start("inputDay5test");
*/
        //dSix.start("inputDay6");
        dSeven.start("inputDay7");





        getTime(time1);
    }

    private static void getTime(long time1) {
        long time2 = System.currentTimeMillis();
        double result = time2-time1;
        result = result /1000;
        System.out.println("Zeit (sek): "+result);
    }


}