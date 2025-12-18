import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author Eddy Liang
 * @date November 24, 2025
 * @problem CCC '10 J5 - Knight Hop
 * @description This program calculates the minimum number of moves a knight
 *              using a simple BFS algorithm
 * @version 2.0
 */
public class CCC05J5 {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Input using System.in.read()
        int startX = System.in.read() - '1';
        System.in.read(); // skip space
        int startY = System.in.read() - '1';
        System.in.read(); // skip newline
        int endX = System.in.read() - '1';
        System.in.read(); // skip space
        int endY = System.in.read() - '1';

        // Exit if start and end are the same
        if (startX == endX && startY == endY) {
            System.out.println(0);
            return;
        }
        // Variables
        Queue<int[]> que = new ArrayDeque<>(); // x, y, steps, distanceX, distanceY
        int[][] target = { { -2, 1 }, { 1, -2 }, { 2, 1 }, { 1, 2 }, { -1, 2 }, { 2, -1 }, { -2, -1 }, { -1, -2 }, };
        boolean[][] visited = new boolean[8][8];

        // BFS implementation
        visited[startX][startY] = true; // mark start as visited
        que.add(new int[] { startX, startY, 0, Math.abs(startX - endX), Math.abs(startY - endY) }); // add start to
                                                                                                    // queue
        // Run BFS While Loop
        while (!que.isEmpty()) {
            int[] cur = que.poll(); // current position
            int steps = cur[2]; // steps taken to reach current position
            for (int[] a : target) { // iterate through all knight moves
                int bx = cur[0] + a[0], by = cur[1] + a[1]; // calculate new position
                if (bx < 0 || bx > 7 || by < 0 || by > 7) // out of bounds
                    continue;
                if (visited[bx][by]) // already visited
                    continue;
                if (bx == endX && by == endY) { // reached end
                    System.out.println(steps + 1); // print steps taken
                    return;
                }
                int cx = Math.abs(bx - endX); // distance in x direction
                int cy = Math.abs(by - endY); // distance in y direction
                if (cx + cy > cur[3] + cur[4] && cx > 5 && cy > 5) // heuristic to skip unlikely paths
                    continue;

                visited[bx][by] = true; // mark new position as visited
                que.add(new int[] { bx, by, steps + 1, cx, cy }); // add new position to queue
            }
        }

    }
}