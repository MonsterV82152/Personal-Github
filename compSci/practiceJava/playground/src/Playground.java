import java.util.Scanner;

public class Playground {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter r/c+NUM:");
        char[] b = scan.nextLine().toCharArray();
        int num = 0;
        for (int i = 1; i < b.length; i++) {
            num += ((int) b[i] - 48) * Math.pow(10, b.length - i - 1);
        }
        if (b[0] == 'r') {
            System.out.println(num * 4);
        } else if (b[0] == 'c') {
            System.out.printf("%.3f \n", num * 2 * Math.PI);
        }
        scan.close();
    }
}