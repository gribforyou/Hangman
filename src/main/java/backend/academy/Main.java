package backend.academy;

import lombok.experimental.UtilityClass;
import java.util.HashSet;
import java.util.Set;
import static backend.academy.Difficulty.*;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        Dictionary dic = new Dictionary();
        Set<Word> words = new HashSet<>();
        words.add(new Word("apple", "nature", "Red sphere", EASY));
        words.add(new Word("class", "Java", "Basic specific word", EASY));
        words.add(new Word("object", "Java", "Ob", HARD));
        words.add(new Word("lemon", "nature", "yellow fruit", EASY));
        words.add(new Word("flower", "nature", "beautiful plant", HARD));
        words.add(new Word("tree", "nature", "plant and data structure", MEDIUM));
        words.add(new Word("implementation", "Java", "using interface in your class", HARD));
        words.add(new Word("method", "Java", "function of object", MEDIUM));
        dic.add(words);

        HangmanGame hg = new HangmanGame(dic, new ConsoleHangmanVisualization(6), new ConsoleHangmanDialog());
        hg.play();


    }
}
