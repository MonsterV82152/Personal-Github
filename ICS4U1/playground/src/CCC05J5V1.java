import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author Eddy Liang
 * @date November 17, 2025
 * @problem CCC '10 J5 - Knight Hop
 * @description This program calculates the minimum number of moves a knight
 *              using a simple BFS algorithm
 * @version 1.0
 */

public class CCC05J5V1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); // Initalize Scanner
        int[] pos = { scan.nextInt(), scan.nextInt(), 0 }, endPos = { scan.nextInt(), scan.nextInt() }; // Read Input
        Queue<int[]> que = new LinkedList<>(); // x, y, steps
        ArrayList<int[]> travelled = new ArrayList<>(); // x, y
        int[][] target = { { 2, 1 }, { 1, 2 }, { -1, 2 }, { -2, 1 }, { 1, -2 }, { 2, -1 }, { -2, -1 }, { -1, -2 } }; // Moves
        que.add(pos); // Add Start Position to Queue
        while (que.size() > 0) { // BFS While Loop
            int[] cur = que.poll(); // Current Position
            int[] curPos = { cur[0], cur[1] }; // Current x, y
            travelled.add(curPos); // Mark Current Position as Travelled
            if (curPos[0] == endPos[0] && curPos[1] == endPos[1]) { // Reached End Position
                System.out.println(cur[2]); // Print Steps Taken
                break;
            }
            for (int[] a : target) { // Iterate Through All Knight Moves
                int[] targetPos = { curPos[0] + a[0], curPos[1] + a[1], cur[2] + 1 }; // Target x, y, steps
                int[] b = { curPos[0] + a[0], curPos[1] + a[1] }; // Target x, y
                if (b[0] < 9 && b[0] > 0 && b[1] < 9 && b[1] > 0 && !travelled.contains(b)) { // Valid Move
                    que.add(targetPos); // Add Target Position to Queue
                }
            }
        }
    }
}