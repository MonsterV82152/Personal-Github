
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
    static int sum;
    static boolean[][] visited;;
    static int total;
    static int n;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        visited = new boolean[2][n];
        scan.nextLine();
        // Old version of the code made by Eddy
        /*
         * 
         * ArrayList<ArrayList<Integer>> grid = new ArrayList<ArrayList<Integer>>();
         * for (int i = 0; i < 2; i++) {
         * String[] inp = scan.nextLine().split(" ");
         * ArrayList<Integer> row = new ArrayList<Integer>();
         * for (String a : inp) {
         * row.add(Integer.parseInt(a));
         * }
         * grid.add(row);
         * }
         * 
         */

        // More consise version made by ChatGPT using Arrays.stream and List instead of
        // ArrayList.
        List<List<Integer>> grid = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            List<Integer> row = Arrays.stream(scan.nextLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            grid.add(row);
        }

        for (List<Integer> row : grid) {
            for (int value : row) {
                sum += value;
            }
        }
        total = n * 2;
        System.out.println(findChunks(grid, new boolean[2][n], 0, 0, 0, new int[]{0,0}, new boolean[2][n]));
    }

    private static int findChunks(List<List<Integer>> grid, boolean[][] visited, int currentSum, int currentTotal,
            int chunks, int[] pos, boolean[][] tempVisited) {

        if (pos[1] >= 0 && pos[1] < n && !tempVisited[pos[0]][pos[1]] && !visited[pos[0]][pos[1]]) {
            tempVisited[pos[0]][pos[1]] = true;
            currentSum += grid.get(pos[0]).get(pos[1]);
            currentTotal++;
            if (currentSum * total == sum * currentTotal) {
                for (int i = 0; i < 2; i++) {
                    for (int m = 0; m < n; m++) {
                        visited[i][m] = visited[i][m] || tempVisited[i][m];
                    }
                }
                for (int i = 0; i < visited.length; i++) {
                    for (int m = 0; m < visited[i].length; m++) {
                        if (!visited[i][m]) {
                            return findChunks(grid, visited, 0, 0, chunks+1, new int[]{i, m}, new boolean[2][n]);
                        }
                    }
                }
                return chunks+1;
            }
            int[][] neighbors = new int[][]{{pos[0]^1, pos[1]},{pos[0], pos[1]+1},{pos[0], pos[1]-1}};
            int highest = 0;
            for (int[] i : neighbors) {
                int temp = findChunks(grid, visited, currentSum, currentTotal, chunks, i, tempVisited);
                if (temp > highest) {
                    highest = temp;
                }
            }
            return highest;
        }

        return 1;

    }
}
