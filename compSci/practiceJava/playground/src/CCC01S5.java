import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Map;

public class CCC01S5 {
    private static int m;
    private static int n;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        m = scan.nextInt();
        n = scan.nextInt();
        scan.nextLine();
        ArrayList<String> a = new ArrayList<String>();
        ArrayList<String> b = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            a.add(scan.nextLine());
        }
        for (int i = 0; i < n; i++) {
            b.add(scan.nextLine());
        }
        ArrayList<Integer> answer = findSequence(a, b, 0, "", "");
        if (answer.get(0) == 0) {
            System.out.println("No solution.");
        } else {
            for (Integer i : answer) {
                System.out.println(i);
            }
        }
        scan.close();
    }

    private static ArrayList<Integer> findSequence(ArrayList<String> a, ArrayList<String> b, int currentCount,
            String currentStringA, String currentStringB) {
        if (currentStringA.equals(currentStringB) && !currentStringA.equals("")) {
            return new ArrayList<Integer>(List.of(currentCount));
        } else if (currentCount > m) {
            return new ArrayList<Integer>(List.of(0));
        }
        for (int i = 0; i < n; i++) {
            String tempA = currentStringA + a.get(i);
            String tempB = currentStringB + b.get(i);
            int len = Math.min(tempA.length(), tempB.length());
            if (tempA.substring(0, len).equals(tempB.substring(0, len))) {
                ArrayList<Integer> result = findSequence(a, b, currentCount + 1, tempA, tempB);
                if (result.get(0) != 0) {
                    result.add(1, i + 1);
                    return result;
                }
            }
        }
        return new ArrayList<Integer>(List.of(0));
    }
}