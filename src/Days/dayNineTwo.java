package Days;

import java.util.ArrayList;

public class dayNineTwo {


    /**
     * Day Nine Part Two
     * Answer:
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
            solution += line.get(0) - temp;


        }
        return solution;
    }

    private static int findSolution(ArrayList<Integer> line) {
        int sum = 0;
        if (line.size() == 1) {
            return line.get(0);
        }

        ArrayList<Integer> below = new ArrayList<>(line.size() - 1);
        for (int i = 0; i < line.size() - 1; i++) {
            //differenz zwischen den zahlen darüber
            below.add(line.get(i + 1) - line.get(i));
        }

        sum = below.get(0) - findSolution(below);
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
