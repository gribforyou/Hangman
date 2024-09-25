package backend.academy.Dialogs;

import backend.academy.Hangman.Difficulty;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Scanner;

public class ConsoleHangmanDialog implements HangmanDialog {
    private final Scanner scanner;
    private final PrintStream printStream;
    private final String wrongInputMessage = "Wrong input! Try again!";

    public ConsoleHangmanDialog() {
        scanner = new Scanner(System.in);
        printStream = System.out;
    }


    @Override
    public String getCategory(Collection<String> categories) {
        printStream.println("Please enter category you want to choose or press Enter to skip");
        printStream.println("Available categories:");
        for (String category : categories) {
            printStream.print(category + " ");
        }
        Collection<String> proccesedCategories = categories.stream()
            .map(String::toLowerCase)
            .toList();

        printStream.println();
        String line;

        while (true) {
            line = scanner.nextLine();
            line = line.replaceAll(" ", "");
            line = line.toLowerCase();
            if (line.isEmpty()) {
                return null;
            }
            if (proccesedCategories.contains(line)) {
                return line;
            }
            printStream.println(wrongInputMessage);
        }
    }

    @Override
    public Difficulty getDifficulty() {
        printStream.println("Please enter difficulty(easy, medium or hard) or press Enter to skip:");
        String difficulty;
        while (true) {
            difficulty = scanner.nextLine();
            difficulty = difficulty.replaceAll(" ", "");
            if (difficulty.isEmpty()) {
                break;
            }
            difficulty = difficulty.toLowerCase();

            switch (difficulty) {
                case "easy": return Difficulty.EASY;
                case "medium": return Difficulty.MEDIUM;
                case "hard": return Difficulty.HARD;
                default:
            }

            printStream.println(wrongInputMessage);
        }
        return Difficulty.ANY;
    }

    @Override
    public Character getSymbol(Collection<Character> triedSymbols) {
        printStream.println("Please enter next symbol:");
        String line;
        while (true) {
            line = scanner.nextLine();
            line = line.replaceAll(" ", "");
            line = line.replaceAll(" ", "");
            if (line.length() != 1) {
                printStream.println(wrongInputMessage);
                continue;
            }
            if (triedSymbols.contains(line.charAt(0))) {
                printStream.println("I have already try this symbol! Enter new symbol:");
                continue;
            }
            if (line.charAt(0) < 'a' || line.charAt(0) > 'z') {
                printStream.println("Wrong symbol! Try again!");
                continue;
            }
            printStream.println();
            return line.charAt(0);
        }
    }

    @Override
    public boolean hintDialog() {
        printStream.println("If you need a hint enter 'h' or anything else otherwise:");
        int temp = 0;
        String enteredLine = "";
        while (enteredLine.isEmpty()) {
            enteredLine = scanner.nextLine();
            enteredLine = enteredLine.toLowerCase();
            enteredLine = enteredLine.replaceAll(" ", "");
        }
        return enteredLine.equals("h");
    }

    @Override
    public boolean againOptionDialog() {
        printStream.println("Wanna try again? Write \"yes\" to try again or anything else otherwise:");
        String answer = scanner.nextLine();
        answer = answer.replaceAll(" ", "").toLowerCase();
        printStream.println();
        return answer.equals("yes");
    }
}
