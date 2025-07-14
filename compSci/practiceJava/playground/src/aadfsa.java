import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;

public class aadfsa {
    public static void main(String[] args) {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(-Integer.parseInt(read.readLine())+2*Integer.parseInt(read.readLine()));
    
    }

}
