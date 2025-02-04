package Days;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class dayNine {

    /**
     * Day Nine
     * Answer: 511547253 too low
     * Answer: 959335581 too low
     * Answer: 1595888728 too low
     * Answer: 2035292138 no
     * Answer: 105114507242 no; type long
     * Answer: 2043677056 correct int geht auch
     *
     *
     */
    static int test = 0;
    public static int start(ArrayList<String> input) {

        int solution = 0;

        int temp = 0;
        ArrayList<Integer> line;
        for (int i = 0; i < input.size(); i++) {
            line = convertToInt(input.get(i));

            //methode zum lösung finden
            temp = findSolution(line);
            solution += line.get(line.size()-1) + temp;


        }
        return solution;
    }

    private static int findSolution(ArrayList<Integer> line) {
        int sum = 0;
        if(line.size() == 1){
            return line.get(0);
        }

        ArrayList <Integer> below = new ArrayList<>(line.size()-1);
        for (int i = 0; i < line.size()-1; i++) {
            //differenz zwischen den zahlen darüber
            below.add(line.get(i+1) - line.get(i));
        }

        sum = below.get(below.size()-1) + findSolution(below);
        return sum;

    }


    private static ArrayList<Integer> convertToInt(String line) {

        ArrayList<Integer> numbers = new ArrayList<>();
        // Assuming the numbers on each line are separated by whitespace.
        String[] tokens = line.split("\\s+");
        for (String token : tokens) {
            // Check if the token is not empty (extra precaution)
            if (!token.isEmpty()) {
                try {
                    int number = Integer.parseInt(token);
                    numbers.add(number);
                } catch (NumberFormatException e) {
                    System.err.println("Unable to convert token to int: " + token);
                }
            }

        }
        return numbers;

    }


}
