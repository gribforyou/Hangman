package backend.academy.Visualizations;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class ConsoleHangmanVisualization implements HangmanVisualization {
    private int maxAttempts;
    private final PrintStream printStream = System.out;
    private final String[] hangman = new String[] {
        " +---+\n |   |\n     |\n     |\n     |\n     |\n=========",
        " +---+\n |   |\n O   |\n     |\n     |\n     |\n=========",
        " +---+\n |   |\n O   |\n |   |\n     |\n     |\n=========",
        " +---+\n |   |\n O   |\n/|   |\n     |\n     |\n=========",
        " +---+\n |   |\n O   |\n/|\\  |\n     |\n     |\n=========",
        " +---+\n |   |\n O   |\n/|\\  |\n/    |\n     |\n=========",
        " +---+\n |   |\n O   |\n/|\\  |\n/ \\  |\n     |\n========="
};

    public ConsoleHangmanVisualization() throws IOException {
        FileInputStream fis = new FileInputStream("src/main/resources/configs/config.properties");
        Properties prop = new Properties();
        prop.load(fis);
        this.maxAttempts = Integer.parseInt(prop.getProperty("faultsToLose"));
    }

    @Override
    public void visualizeHangman(int attempts) {
        if (attempts > maxAttempts || attempts < 0) {
            throw new IllegalArgumentException("Invalid attempts");
        }
        int num = (hangman.length - 1) * attempts / maxAttempts;
        printStream.println(hangman[num]);
    }
}
