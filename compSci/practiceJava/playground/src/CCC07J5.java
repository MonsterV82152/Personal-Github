/**
 * @author Eddy Liang
 * @date July 18, 2025
 * @problem CCC '07 S5 - Keep on Truckin'
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class CCC07J5 {
    private static int min;
    private static int max;
    private static List<Integer> stops;

    public static void main(String[] args) throws Exception {
        stops = new ArrayList<>(
                Arrays.asList(0, 990, 1010, 1970, 2030, 2940, 3060, 3930, 4060, 4970, 5030, 5990, 6010, 7000));
        Scanner scan = new Scanner(System.in);
        min = scan.nextInt(); // Gets the min distance
        max = scan.nextInt(); // Gets the max distance
        int extraStops = scan.nextInt();
        for (int i = 0; i < extraStops; i++) {
            stops.add(scan.nextInt()); // Adds each extra stop
        }
        stops.sort(null); // Sorts the list from smallest to largest
        scan.close();

        long ways = 0; // Set a counter for the # of possible paths
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < stops.size(); i++) {
            for (int j = i; j < stops.size(); j++) {
                if (stops.get(i) + min <= stops.get(j)) {
                    if (stops.get(i) + max < stops.get(j)) { // breaks if larger to save time
                        break;
                    } else if (!(stops.get(i) == stops.get(j))) {
                        map.computeIfAbsent(i, k -> new ArrayList<>()).add(j); // Add the node if doesn't exist, else, add the connected point
                    }
                }
            }
        }
        Queue<Integer> que = new LinkedList<Integer>(Arrays.asList(0)); // BFS queue
        int size = stops.size() - 1;
        while (!que.isEmpty()) { // Cycles through the queue
            int current = que.poll(); // Gets and removes the first element in the queue
            List<Integer> nextStops = map.get(current);
            if (nextStops == null)
                continue;
            for (int i : nextStops) {
                if (i == size) {
                    ways++; // Adds possible path if reached the end
                    break;
                }
                que.add(i); // Adds possible branches
            }
        }
        System.out.println(ways); // Print the # of ways.

    }
}
