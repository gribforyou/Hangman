package backend.academy;

import java.util.TreeSet;
import java.util.Scanner;
import java.util.Set;

public class GameSession implements Runnable{
    private final int  MAX_FAILURE_COUNT = 6;
    private final int FAIL_COUNT_TO_GET_HINT = 4;
    private final Word word;
    private int failCount;
    private final HangmanVisualization hangmanVisualization;
    private Set<Character> triedList;
    private boolean getHint;
    private HangmanDialog hangmanDialog;

    public GameSession(Word word, HangmanVisualization hangmanVisualization, HangmanDialog hangmanDialog) {
        this.word = word;
        this.hangmanVisualization = hangmanVisualization;
        this.hangmanDialog = hangmanDialog;
        failCount = 0;
        getHint = false;
        triedList = new TreeSet<>();
    }

    private void showGuessedWord(){
        System.out.print("Guessed word:\n");
        String wordText = word.text();
        for(Character ch : wordText.toCharArray()){
            if(triedList.contains(ch)){
                System.out.print((char)(ch+'A'-'a') + " ");
            }
            else {
                System.out.print("_ ");
            }
        }
        System.out.println(" ");
        System.out.println(" ");
    }

    private void showCategory(){
        System.out.println("Category:\n" + word.category());
        System.out.println();
    }

    private void showHint(){
        System.out.println("Hint:\n" + word.hint());
        System.out.println();
    }

    private void showTriedSymbols(){
        if(!triedList.isEmpty()){
            System.out.print("Tried symbols:\n");
            for(Character tried : triedList){
                System.out.print(tried + " ");
            }
            System.out.println();
            System.out.println();
        }
    }

    private void showHangman(){
        hangmanVisualization.visualizeHangman(failCount);
        System.out.println();
    }

    private void showFailsToLose(){
        System.out.println("It's " + (MAX_FAILURE_COUNT - failCount) + " fails to lose!");
        System.out.println();
    }

    private boolean isAlived(){
        return failCount < MAX_FAILURE_COUNT;
    }

    private boolean isWin(){
        for(Character ch : word.text().toCharArray()){
            if(!triedList.contains(ch)){
                return false;
            }
        }
        return true;
    }

    private void nextStep(){
        Character ch = hangmanDialog.getSymbol(triedList);
        if(!word.text().contains(ch.toString())){
            System.out.println("There isn't this symbol(");
            failCount++;
            showHangman();
            if(isAlived()){
                showFailsToLose();
                if(failCount >= FAIL_COUNT_TO_GET_HINT && !getHint){
                    getHint = hangmanDialog.HintDialog();
                }
            }
        }
        else{
            System.out.println("You got it!");
        }
        triedList.add(ch);
        System.out.println("==================================");
        System.out.println();
    }

    private void congrates(){
        System.out.println("Congratulations! You win!");
        System.out.println("Guessed word:\n" + word.text().toUpperCase());
        System.out.println();
    }

    private void lose(){
        System.out.println("You lose!");
        System.out.println("Guessed word:\n" + word.text().toUpperCase());
        System.out.println();
    }

    @Override
    public void run() {
        while(isAlived()){
            showGuessedWord();
            showCategory();
            if(getHint){
                showHint();
            }
            showTriedSymbols();
            nextStep();
            if(isWin()){
                congrates();
                return;
            }
        }
        lose();
    }
}
