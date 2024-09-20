package backend.academy;

import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class HangmanGame implements Runnable {
    private Dictionary dictionary;
    public HangmanGame(Dictionary dictionary) {
        if(dictionary.getCategories().isEmpty()){
            throw new IllegalArgumentException("Dictionary is empty!");
        }
        this.dictionary = dictionary;
    }

    public void run(){
        Start();
        System.out.println(chooseCategory());
    }

    private void Start(){
        System.out.println(
            "------------------\n"+
            "|  Hangman Game  |\n"+
            "------------------\n"
        );
    }

    private String chooseCategory(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a category:");
        for( String category : dictionary.getCategories()){
            System.out.print(category+" ");
        };
        System.out.println();
        System.out.println("or Press Enter to skip...");
        String temp;
        while(true){
            temp = scanner.nextLine();
            if(temp.isEmpty()){
                int i = new Random().nextInt(dictionary.getCategories().size());
                for( String category : dictionary.getCategories()){
                    i--;
                    if(i == 0){
                        return category;
                    }
                }
            }
            else if(dictionary.getCategories().contains(temp)){
                return temp;
            }
            System.out.println("There are not this category... Please try again!");
        }
    }
}
