/**
 * @author Eddy
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;


public class CCC24S5 {
    static int sum = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        scan.nextLine();
        // Old version of the code made by Eddy
        /*

        ArrayList<ArrayList<Integer>> grid = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < 2; i++) {
        String[] inp = scan.nextLine().split(" ");
        ArrayList<Integer> row = new ArrayList<Integer>();
        for (String a : inp) {
        row.add(Integer.parseInt(a));
        }
        grid.add(row);
        }

        */
        

        // More consise version made by ChatGPT using Arrays.stream and List instead of ArrayList.
        List<List<Integer>> grid = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            List<Integer> row = Arrays.stream(scan.nextLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            grid.add(row);
        }

        boolean[][] visited = { new boolean[n], new boolean[n] };
        
        Queue<int[]> queue = new LinkedList<>();

        while (!queue.isEmpty()) {

        }
    }
}
