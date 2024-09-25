package backend.academy.Hangman;

import backend.academy.Dialogs.HangmanDialog;
import backend.academy.Visualizations.HangmanVisualization;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

public class GameSession implements Runnable {
    private final int maxFailureCount;
    private final int failCountToGetHint;
    private final int sleepTime;
    private final Word word;
    private int failCount;
    private final HangmanVisualization hangmanVisualization;
    private Set<Character> triedList;
    private boolean getHint;
    private HangmanDialog hangmanDialog;
    private final PrintStream printStream = System.out;

    public GameSession(Word word, HangmanVisualization hangmanVisualization, HangmanDialog hangmanDialog)
        throws IOException {
        this.word = word;
        this.hangmanVisualization = hangmanVisualization;
        this.hangmanDialog = hangmanDialog;
        failCount = 0;
        getHint = false;
        triedList = new TreeSet<>();

        FileInputStream fis = new FileInputStream("src/main/resources/configs/config.properties");
        Properties prop = new Properties();
        prop.load(fis);

        maxFailureCount = Integer.parseInt(prop.getProperty("faultsToLose"));
        failCountToGetHint = Integer.parseInt(prop.getProperty("faultsToShowHintDialog"));
        sleepTime = Integer.parseInt(prop.getProperty("sleepTime"));
    }

    private void showGuessedWord() {
        printStream.print("Guessed word:\n");
        String wordText = word.text();
        for (Character ch : wordText.toLowerCase().toCharArray()) {
            if (triedList.contains(ch)) {
                printStream.print((char) (ch + 'A' - 'a') + " ");
            } else {
                printStream.print("_ ");
            }
        }
        printStream.println();
        printStream.println();
    }

    private void showCategory() {
        printStream.println("Category:\n" + word.category());
        printStream.println();
    }

    private void showHint() {
        printStream.println("Hint:\n" + word.hint());
        printStream.println();
    }

    private void showTriedSymbols() {
        if (!triedList.isEmpty()) {
            printStream.print("Tried symbols:\n");
            for (Character tried : triedList) {
                printStream.print(tried + " ");
            }
            printStream.println();
            printStream.println();
        }
    }

    private void showHangman() {
        hangmanVisualization.visualizeHangman(failCount);
        printStream.println();
    }

    private void showFailsToLose() {
        printStream.println("It's " + (maxFailureCount - failCount) + " fails to lose!");
        printStream.println();
    }

    private boolean isAlived() {
        return failCount < maxFailureCount;
    }

    private boolean isWin() {
        for (Character ch : word.text().toLowerCase().toCharArray()) {
            if (!triedList.contains(ch)) {
                return false;
            }
        }
        return true;
    }

    private void nextStep() {
        Character ch = hangmanDialog.getSymbol(triedList);
        if (!word.text().toLowerCase().contains(ch.toString())) {
            printStream.println("There isn't this symbol(");
            failCount++;
            showHangman();
            if (isAlived()) {
                showFailsToLose();
                if (failCount >= failCountToGetHint && !getHint) {
                    getHint = hangmanDialog.hintDialog();
                }
            }
        } else {
            printStream.println("You got it!");
        }
        triedList.add(ch);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            printStream.println(e.getMessage());
        }
        printStream.println("==================================\n\n\n\n\n\n\n\n\n\n");
    }

    private void congrates() {
        printStream.println("Congratulations! You win!\nGuessed word:\n" + word.text().toUpperCase());
        printStream.println();
    }

    private void lose() {
        printStream.println("You lose!\nGuessed word:\n" + word.text().toUpperCase());
        printStream.println();
    }

    @Override
    public void run() {
        while (isAlived()) {
            showGuessedWord();
            showCategory();
            if (getHint) {
                showHint();
            }
            showTriedSymbols();
            nextStep();
            if (isWin()) {
                congrates();
                return;
            }
        }
        lose();
    }
}
