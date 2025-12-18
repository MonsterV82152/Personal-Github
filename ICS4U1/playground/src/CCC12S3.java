import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Eddy Liang
 * @date December 17, 2025
 * @problem CCC ’12 S3 - Absolutely Acidic
 * @description This program finds the maximum absolute difference between
 *              readings with the highest frequencies.
 * @version 2.0
 */

public class CCC12S3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // number of readings
        int[] freq = new int[1000]; // frequencies of each reading (1-1000)
        for (int i = 0; i < n; i++) {
            freq[Integer.parseInt(br.readLine()) - 1]++; // increment frequency
        }
        int[] highhighMax = { -1, 0 }; // {frequency, reading} - highest frequency, highest reading
        int[] highlowMax = { -1, 0 }; // {frequency, reading} - highest frequency, lowest reading
        int[] lowhighMax = { -1, 0 }; // {frequency, reading} - 2nd highest frequency, highest reading
        int[] lowlowMax = { -1, 0 }; // {frequency, reading} - 2nd highest frequency, lowest reading

        for (int i = 0, a = 0; i < freq.length; a = freq[i++]) { // iterate through frequencies
            if (a == 0) // skip readings that did not occur
                continue;
            if (a > highlowMax[0]) { // new highest frequency, lowest reading
                lowlowMax = highlowMax.clone(); // update second highest
                highlowMax[1] = i;
                highlowMax[0] = a;
            } else if (a > lowlowMax[0]) { // new 2nd highest frequency, lowest reading
                lowlowMax[1] = i;
                lowlowMax[0] = a;
                lowhighMax = lowlowMax.clone(); // update second lowest
            }
            if (a >= highhighMax[0]) { // new highest frequency, highest reading
                highhighMax[1] = i;
                highhighMax[0] = a;
            }
            if (a >= lowhighMax[0]) { // new 2nd highest frequency, highest reading
                lowhighMax[1] = i;
                lowhighMax[0] = a;
            }

        }
        // print max absolute difference
        System.out.println(Math.max(Math.abs(lowhighMax[1] - highlowMax[1]), Math.abs(lowlowMax[1] - highhighMax[1])));
    }
}