package backend.academy;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        GameSession gs = new GameSession(new Word("apple", "Fruit", "Red sphere", 1), new ConsoleHangmanVisualization(6));
        gs.run();
    }
}
