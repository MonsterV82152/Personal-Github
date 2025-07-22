/**
 * @author Eddy Liang
 * @date July 18, 2025
 * @problem CCC '07 S5 - Keep on Truckin'
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class CCC07J51 {

    public static void main(String[] args) throws Exception {
        List<Integer> stops = new ArrayList<>(
                Arrays.asList(0, 990, 1010, 1970, 2030, 2940, 3060, 3930, 4060, 4970, 5030, 5990, 6010, 7000));
        Scanner scan = new Scanner(System.in);
        int min = scan.nextInt(); // Input the minimum distance
        int max = scan.nextInt(); // Input the maximum distance
        int extraStops = scan.nextInt(); // Determine how many extra stops there are.
        for (int i = 0; i < extraStops; i++) {
            stops.add(scan.nextInt()); // Add the extra stops
        }
        stops.sort(null); // Sorts the list from smallest to largest
        scan.close();
        int ways = 0; // Amount of ways to 7000
        Queue<Integer> que = new LinkedList<Integer>(Arrays.asList(0)); // BFS queue
        while (!que.isEmpty()) { // Cycles throught the queue
            int current = que.poll();
            int far = current + max; // Calculate min/max
            int close = current + min;
            for (int i : stops) {
                if (i >= close) {
                    if (i > far) { // If its larger, break to save resources
                        break;
                    } else if (i == 7000) { // If its the end, add to ways and break
                        ways++;
                        break;
                    }
                    que.add(i); // Add to queue if branch exists

                }
            }
        }
        System.out.println(ways);

    }
}
