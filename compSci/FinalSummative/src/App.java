public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        GameHandler game = GameHandler.getInstance(); // Get the singleton instance of GameHandler
        game.start(); // Start the game
    }
}
