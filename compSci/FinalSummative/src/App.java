public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        GameHandler game = GameHandler.getInstance(); // Get the singleton instance of GameHandler
        game.start(); // Start the game

        /*
         * IMPORTANT:
         * S to save the game state to a file
         * L to load the game state from a file
         * Git Link to All Versions: https://github.com/MonsterV82152/Personal-Github
         */
    }
}
