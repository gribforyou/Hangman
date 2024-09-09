package backend.academy;

public class ConsoleHangmanVisualization implements HangmanVisualization {
    private int maxAttempts;
    private final String[] HANGMAN = new String[] {
        " +---+\n |   |\n     |\n     |\n     |\n     |\n=========",
        " +---+\n |   |\n O   |\n     |\n     |\n     |\n=========",
        " +---+\n |   |\n O   |\n |   |\n     |\n     |\n=========",
        " +---+\n |   |\n O   |\n/|   |\n     |\n     |\n=========",
        " +---+\n |   |\n O   |\n/|\\  |\n     |\n     |\n=========",
        " +---+\n |   |\n O   |\n/|\\  |\n/    |\n     |\n=========",
        " +---+\n |   |\n O   |\n/|\\  |\n/ \\  |\n     |\n========="
};

    public ConsoleHangmanVisualization(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    @Override
    public void visualizeHangman(int attempts) {
        if (attempts > maxAttempts || attempts < 0) {
            throw new IllegalArgumentException("Invalid attempts");
        }
        int num = (HANGMAN.length-1)*attempts/maxAttempts;
        System.out.println(HANGMAN[num]);
    }
}
