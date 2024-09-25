package backend.academy.Hangman;

import backend.academy.Dialogs.HangmanDialog;
import backend.academy.Visualizations.HangmanVisualization;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class HangmanGame {
    private Dictionary dictionary;
    private GameSession gameSession;
    private HangmanVisualization hangmanVisualization;
    private HangmanDialog hangmanDialog;
    private final PrintStream printStream = System.out;

    public HangmanGame(Dictionary dictionary, HangmanVisualization hangmanVisualization, HangmanDialog hangmanDialog) {
        if (dictionary.isEmpty()) {
            throw new IllegalArgumentException("Dictionary is empty!");
        }
        this.dictionary = dictionary;
        this.hangmanVisualization = hangmanVisualization;
        this.hangmanDialog = hangmanDialog;
    }

    private void start() {
        printStream.println("+---------------------+\n"
            + "|    Hangman game     |\n"
            + "+---------------------+");

        printStream.println("Enter any string to play...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public void play() throws IOException {
        start();
        do {
            String category = hangmanDialog.getCategory(dictionary.getCategories());
            Difficulty difficulty = hangmanDialog.getDifficulty();
            Word word = dictionary.getWord(category, difficulty);
            gameSession = new GameSession(word, hangmanVisualization, hangmanDialog);
            gameSession.run();
        } while (hangmanDialog.againOptionDialog());

    }
}
