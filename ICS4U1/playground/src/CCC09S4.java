import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class CCC09S4 {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        String line = read.readLine();

        int number = Integer.parseInt(read.readLine());

        String[] parts = read.readLine().split(" ");

        read.close();
    }
}
