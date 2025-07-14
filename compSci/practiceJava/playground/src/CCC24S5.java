import java.util.Scanner;
public class CCC24S5 {
    static int sum = 0;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        scan.nextLine();
        ArrayList<char[]> grid = new ArrayList<char[]>();
        grid.add(scan.nextLine().split(" "));
        grid.add(scan.nextLine().split(" "));
        for (char[] a : grid) {
            for (char b : a) {
                sum += (int) b - 48;
            }
        }



        
    }
}
