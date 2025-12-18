public class main {
    public static void main(String[] args) throws Exception {
        SignedText st = new SignedText("John", "Doe");
        String text1 = "Hello World";
        String text2 = "J-DoeHello World";
        int a = 2;
        
        System.out.println(a+"-"+a);
        System.out.println(st.addSignature(text1));
        System.out.println(st.addSignature(text2));
        for (; text1.length() < 20; System.out.print(text1), text1 += "!", System.out.println(text2))
            ;
    } // how to get a string value of an integer
}
