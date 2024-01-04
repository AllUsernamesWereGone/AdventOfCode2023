package Days;


public class dSix {
    public static int[] time = new int[]{56, 71, 79, 99};
    public static final int timeNew = 56717999;
    public static final long distanceNew = 334113513502430L;
    public static int[] distance = new int[]{334, 1135, 1350, 2430};


    public static void start(String name) {

        System.out.println(getSolution());
        // 101 too low, 211904 first rätsel


    }

    private static long getSolution() {

        long distanceTravelled = 0;
        long value = 0;

        for (int i = 0; i < timeNew; i++) {
            distanceTravelled = (long) i * (timeNew - i);
            //System.out.println(distanceTravelled);
            if (distanceTravelled > distanceNew) {
                value++;
            }


        }

        return value;
    }


}
