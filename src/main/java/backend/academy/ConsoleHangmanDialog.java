package backend.academy;

import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleHangmanDialog implements HangmanDialog {
    private Scanner scanner;

    public ConsoleHangmanDialog() {
        scanner = new Scanner(System.in);
    }


    @Override
    public String getCategory(Collection<String> categories) {
        System.out.println("Please enter category you want to choose or press Enter to skip");
        System.out.println("Available categories:");
        categories = categories.stream()
            .map(String::toLowerCase)
            .collect(Collectors.toList());

        for(String category : categories) {
            System.out.print(category + " ");
        };

        System.out.println();
        String line;
        while(true) {
            line = scanner.nextLine();
            line = line.replaceAll(" ", "");
            line = line.toLowerCase();
            if (line.isEmpty()) {
                return null;
            }
            if (categories.contains(line)) {
                return line;
            }
            System.out.println("Wrong input! Try again!");
        }
    }

    @Override
    public Difficulty getDifficulty() {
        System.out.println("Please enter difficulty(easy, medium or hard) or press Enter to skip:");
        String difficulty;
        while(true){
            difficulty = scanner.nextLine();
            difficulty = difficulty.replaceAll(" ", "");
            if(difficulty.isEmpty()){
                break;
            }
            difficulty = difficulty.toLowerCase();

            if(difficulty.equals("easy") || difficulty.equals("medium") || difficulty.equals("hard")){
                break;
            }

            System.out.println("Wrong input! Try again!");
        }
        switch(difficulty){
            case "easy": return Difficulty.EASY;
            case "medium": return Difficulty.MEDIUM;
            case "hard": return Difficulty.HARD;
            default: return Difficulty.ANY;
        }
    }

    @Override
    public Character getSymbol(Collection<Character> triedSymbols) {
        System.out.println("Please enter next symbol:");
        String line;
        while(true){
            line = scanner.nextLine();
            line = line.replaceAll(" ", "");
            line = line.replaceAll(" ", "");
            if(line.length() != 1){
                System.out.println("Wrong input! Try again!");
                continue;
            }
            if(triedSymbols.contains(line.charAt(0))){
                System.out.println("I have already try this symbol! Enter new symbol:");
                continue;
            }
            if(line.charAt(0) < 'a' || line.charAt(0) > 'z'){
                System.out.println("Wrong symbol! Try again!");
                continue;
            }
            return line.charAt(0);
        }
    }

    @Override
    public boolean HintDialog() {
        System.out.println("If you need a hint enter 'h' or anything else otherwise:");
        int temp = 0;
        String enteredLine = new String();
        while (enteredLine.isEmpty()) {
            enteredLine = scanner.nextLine();
            enteredLine = enteredLine.toLowerCase();
            enteredLine = enteredLine.replaceAll(" ", "");
        }
        if(enteredLine.equals("h")){
            return true;
        }
        return false;
    }

    @Override
    public boolean againOptionDialog() {
        System.out.println("Wanna try again? Write \"yes\" to try again or anything else otherwise:");
        String answer = scanner.nextLine();
        answer = answer.replaceAll(" ", "").toLowerCase();
        if(answer.equals("yes")){
            return true;
        }
        return false;
    }
}
