package backend.academy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameSession {
    private final int MAX_NUMBER_OF_ATTEMPTS = 6;
    private int attemptsCount;
    private Word word;
    private List<Character> attempts;
    private HangmanVisualization hangmanVisualization;
    private boolean isRunning;

    public GameSession(Word word, HangmanVisualization hangmanVisualization) {
        this.word = word;
        this.attemptsCount = 0;
        this.attempts = new ArrayList<>();
        this.hangmanVisualization = hangmanVisualization;
        this.isRunning = false;
    }

    public void run(){
        isRunning = true;
        while(isRunning){
            makeAttempt();
            render();
        }

    }

    private void render(){
        System.out.println("Total attempts: " + attemptsCount);
        System.out.println("Category: " + word.category());
        wordPrint();
        hangmanVisualization.visualizeHangman(attemptsCount);
    }

    private void wordPrint(){
        String wordText = word.text();
        for(Character ch : wordText.toCharArray()){
            if(attempts.contains(ch)){
                System.out.print(ch);
            }
            else{
                System.out.print('_');
            }
        }
        System.out.println();
    }

    private void makeAttempt(){

        Scanner sc = new Scanner(System.in);
        boolean b = false;
        String line;
        do {
            System.out.println("Give character:");
            line = sc.nextLine();
            if(isInputCorrect(line)){
                b = true;
            }
        }while(!b);
        for(int i = 0; i<line.length(); i++){
            char ch = line.charAt(i);
            if(ch != ' '){
                attempts.add(ch);
            }
        }
        if(!word.text().contains(attempts.getLast().toString())) attemptsCount++;
        if(attemptsCount >= MAX_NUMBER_OF_ATTEMPTS){
            isRunning = false;
        }
    }

    private boolean isInputCorrect(String input){
        input = input.toLowerCase();
        String[] words = input.split(" ");
        if(words.length != 1) return false;
        if(words[0].length() != 1) return false;
        if(words[0].charAt(0)<'a' || words[0].charAt(0)>'z') return false;
        return true;
    }
}
