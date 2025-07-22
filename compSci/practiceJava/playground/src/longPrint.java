/**
 * @author Me
 * @date Jul 15
 * 
 */
import java.lang.reflect.Executable;
import java.util.Scanner;

import javax.management.timer.Timer;
/**
 * @author me
 */
public class longPrint {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        for (String input = ""; !input.equals("quit"); input = scan.nextLine()) {
            String current = "";
            for (char i : input.toCharArray()) {
                for (char n = '0'; n < 'z'; n++) {
                    System.out.println(current+n);
                    Thread.sleep(10);
                    if (n == i) {
                        current = current + n;
                        break;
                    }
                }
            }
        }
    }
}