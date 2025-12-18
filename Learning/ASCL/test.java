import java.util.Scanner;
import java.util.ArrayList;

public class test {
    static ArrayList<ArrayList<Integer>> travel;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // int numLogs = scan.nextInt();
        // int numWood = scan.nextInt();
        // ArrayList<Integer> logs = new ArrayList<Integer>();
        // for (int i = 0; i < numLogs; i++) {
        // logs.add(scan.nextInt());
        // }
        // scan.close();

        int n = scan.nextInt();
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = scan.nextInt();
            arr[i][1] = scan.nextInt();
        }
        scan.close();
        travel = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++) {
            travel.add(new ArrayList<Integer>());
            for (int j = 0; j < n; j++) {
                if (arr[i][0] >= arr[j][1]) {
                    travel.get(i).add(j);
                }
            }
        }
        int[] dp = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            dp = findClasses(i, dp);
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);

    }
    public static int[] findClasses(int start, int[] dp) {
        if (dp[start] != 0) {
            return dp;
        }
        dp[start] = 1;
        for (int i : travel.get(start)) {
            dp = findClasses(i, dp);
            dp[start] = Math.max(dp[start], dp[i] + 1);
        }
        return dp;
    } 

}