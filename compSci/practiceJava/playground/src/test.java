public class test {
    public static void main(String[] args) {
        char value = 's'; // Example value (172 in decimal)
        int bitSize = 16;
        // Extract the 3rd bit (counting from 0, right to left)
        for (int bitPosition = bitSize; bitPosition > -1; bitPosition--) {
            int extractedBit = value << bitPosition;
            System.out.println((char)extractedBit);
        }
        System.out.println();
        
        

    }
}