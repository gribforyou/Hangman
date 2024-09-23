package backend.academy;

import java.util.Scanner;
import java.util.List;

public class HangmanGame {
    private Dictionary dictionary;
    private GameSession gameSession;
    private HangmanVisualization hangmanVisualization;
    private HangmanDialog hangmanDialog;

    public HangmanGame(Dictionary dictionary, HangmanVisualization hangmanVisualization, HangmanDialog hangmanDialog) {
        if(dictionary.isEmpty()){
            throw new IllegalArgumentException("Dictionary is empty!");
        }
        this.dictionary = dictionary;
        this.hangmanVisualization = hangmanVisualization;
        this.hangmanDialog = hangmanDialog;
    }

    private void start(){
        System.out.println("+---------------------+\n" +
                           "|    Hangman game     |\n" +
                           "+---------------------+");

        System.out.println("Enter any string to play...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public void play(){
        start();
        do{
            String category = hangmanDialog.getCategory(dictionary.getCategories());
            Difficulty difficulty = hangmanDialog.getDifficulty();
            Word word = dictionary.getWord(category, difficulty);
            gameSession = new GameSession(word, hangmanVisualization, hangmanDialog);
            gameSession.run();
        }while(hangmanDialog.againOptionDialog());

    }
}
