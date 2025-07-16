import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.lang.Exception;

public class CCC07J52 {
    public static void main(String[] args) throws Exception{
        List<Integer> stops = new ArrayList<>(
                Arrays.asList(0, 990, 1010, 1970, 2030, 2940, 3060, 3930, 4060, 4970, 5030, 5990, 6010, 7000));
        Scanner scan = new Scanner(System.in);
        int min = scan.nextInt();
        int max = scan.nextInt();
        int extraStops = scan.nextInt();
        for (int i = 0; i < extraStops; i++) {
            stops.add(scan.nextInt());
        }
        stops.sort(null);
        scan.close();
        long[] ways = new long[stops.size()];
        ways[0] = 1;
        for (int i = 0; i < stops.size(); i++) {
            for (int j = i; j < stops.size(); j++) {
                if (stops.get(i) + min <= stops.get(j)) {
                    if (stops.get(i) + max < stops.get(j)) {
                        break;
                    } else if (!(stops.get(i) == stops.get(j))) {
                        ways[j] += ways[i];
                    }
                }
            }
        }
        System.out.println(ways[ways.length-1]);
    }

}
