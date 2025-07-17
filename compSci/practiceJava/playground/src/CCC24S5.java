
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
    static int total;
    static int n;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
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
        System.out.println(findChunks(grid, new boolean[2][n], 0, 0, 0, new int[] { 0, 0 }, new boolean[2][n]));
    }

    class State {
        boolean[][] visited;
        int[][] position;
        int sum;
        int total;

        State(boolean[][] visited, int[][] position, int currentSum, int currentTotal) {
            this.visited = visited;
            this.position = position;
            this.sum = currentSum;
            this.total = currentTotal;
        }
    }

    private static int findChunks(List<List<Integer>> grid, boolean[][] visited,
            int chunks, int[][] start) {
        Queue<State> que = new LinkedList<>();
        que.add(new State(new boolean[2][n], start, 0, 0));
        List<Integer> solutions = new ArrayList<Integer>();
        while (!que.isEmpty()) {
            State currentState = que.poll();
            if (currentState.sum * total == currentState.total * sum) {
                boolean[][] vis = new boolean[2][n];
                bool full = true;
                for (int i = 0; i < visited.length; i++) {
                    for (int j = 0; j < visited[i].length; j++) {
                        vis[i][j] = visited[i][j] || currentState.visited[i][j];
                    }
                }
                for (int i = 0; i < vis.length; i++) {
                    for (int j = 0; j < vis[i].length; j++) {
                        if (!vis[i][j]) {
                            full = false;
                            solutions.add(findChunks(grid, vis, chunks + 1, new int[] { i, j }));
                            break;
                        }
                    }
                }
                if (full) {
                    int highest = 1;
                    for (int i : solutions) {
                        if (i > highest) {
                            highest = i;
                        }
                    }
                    return highest;
                }
            } else {
                for (int[] pos : currentState.position) {
                    int[][] neighbors = { { pos[0] ^ 1, pos[1] }, { pos[0] - 1, pos[1] }, { pos[0], pos[1] + 1 } };
                    List<int[]> canVisit = new ArrayList<>();
                    for (int[] i : neighbors) {
                        if (i[1] >= 0 && i[1] < n && !visited[i[0]][i[1]] && !currentState.visited[i[0]][i[1]]) {
                            canVisit.add(i);
                        }
                    }
                    for (int i = 0; i < canVisit.size(); i++) {
                        boolean[][] vis = currentState.visited;
                        vis[canVisit.get(i)[0]][canVisit.get(i)[1]] = true;
                        que.add(new State(vis, new int[][]{canVisit.get(i)}, currentState.sum+grid.get(canVisit.get(i)[0]).get(canVisit.get(i)[1]), currentState.total+1));
                        for (int j = i + 1; j < canVisit.size(); j++) {
                            boolean[][] vis2 = vis;
                            vis2[canVisit.get(j)[0]][canVisit.get(j)[1]] = true;

                        }
                    }
                }
            }

        }

        return 1;

    }
}
