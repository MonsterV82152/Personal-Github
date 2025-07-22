public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        GameHandler game = GameHandler.getInstance();
        game.start();
    }
}
