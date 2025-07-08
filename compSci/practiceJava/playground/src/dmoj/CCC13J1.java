package dmoj;
import java.util.Scanner;
public class CCC13J1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        /*
         * This question requires us to find the difference between the first and second variable, and add that difference to the second one.
         * 
         * EX. 13, 15 - Since the ages are an arithmetic sequence, the difference between the third person and the second person 
         * is the same as the difference between the first and second person.
         * 
         * 15 + (15 - 13)
         * This can be simplified into 2 * 15 - 13
         * 
         * If we subsitute the ages for variables, it would be: 2b - a
         * 
         * Since the smaller age is inputed first, we would have to flip this statement
         * -a + 2b
         * 
         * Now, we can directly add this to a print statement using the scanner.
         */
        System.out.println(-scan.nextInt()+scan.nextInt()*2); 
        scan.close();
        
    }
}
