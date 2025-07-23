import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Map;

public class CCC01S5 {
    // Initalizing the input variables
    private static int m; // Max length of combination
    private static int n; // Length of each list

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); // Init Scanner
        m = scan.nextInt(); // Scans the inputs
        n = scan.nextInt();
        scan.nextLine();
        ArrayList<String> a = new ArrayList<String>();
        ArrayList<String> b = new ArrayList<String>();
        for (int i = 0; i < n; i++) { // runs a for loop for both to add the inputs
            a.add(scan.nextLine());
        }
        for (int i = 0; i < n; i++) {
            b.add(scan.nextLine());
        }
        ArrayList<Integer> answer = findSequence(a, b, 0, "", ""); // Run the DFS calculation loop
        if (answer.get(0) == 0) { // If it returns a list with only 0, it means that it failed and print "No solution."
            System.out.println("No solution.");
        } else {
            for (Integer i : answer) { // Print the sequence of numbers in the arrayList
                System.out.println(i);
            }
        }
        scan.close();
    }

    private static ArrayList<Integer> findSequence(ArrayList<String> a, ArrayList<String> b, int currentCount,
            String currentStringA, String currentStringB) { // Returns the order of the solution.
        if (currentStringA.equals(currentStringB) && !currentStringA.equals("")) { // if the Strings are the same, a solution is found and return the count of steps
            return new ArrayList<Integer>(List.of(currentCount));
        } else if (currentCount > m) { // If exceeded the limit, return 0 meaning this branch failed and continue searching
            return new ArrayList<Integer>(List.of(0));
        }
        for (int i = 0; i < n; i++) { // Checks each index for both lists
            String tempA = currentStringA + a.get(i); // Creates temporary variables to compare adding the index to the current String
            String tempB = currentStringB + b.get(i);
            int len = Math.min(tempA.length(), tempB.length()); // Finds the smallest string's length
            if (tempA.substring(0, len).equals(tempB.substring(0, len))) { // If everything matches - possible combination, else - already invalid, skip
                ArrayList<Integer> result = findSequence(a, b, currentCount + 1, tempA, tempB); // Keeps the DFS loop running and enters the possible string and the current count + 1
                if (result.get(0) != 0) { // If it does not returns 0, that means there is a solution, return the result and add the succeeded index
                    result.add(1, i + 1);
                    return result;
                }
            }
        }
        return new ArrayList<Integer>(List.of(0)); // If no possible solution works in this branch, return 0 to continue searching
    }
}