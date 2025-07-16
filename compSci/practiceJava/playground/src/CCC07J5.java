import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
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
        min = scan.nextInt();
        max = scan.nextInt();
        int extraStops = scan.nextInt();
        for (int i = 0; i < extraStops; i++) {
            stops.add(scan.nextInt());
        }
        stops.sort(null);
        scan.close();
        int ways = 0;
        Queue<Integer> que = new LinkedList<Integer>(Arrays.asList(0));
        while (!que.isEmpty()) {
            int current = que.poll();
            int far = current + max;
            int close = current + min;
            for (int i : stops) {
                if (i >= close) {
                    if (i > far) {
                        break;
                    } else if (i == 7000) {
                        ways++;
                        break;
                    }
                    que.add(i);

                }
            }
        }
        System.out.println(ways);

    }
}
