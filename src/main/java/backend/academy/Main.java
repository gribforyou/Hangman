package backend.academy;

import backend.academy.Dialogs.ConsoleHangmanDialog;
import backend.academy.Hangman.Dictionary;
import backend.academy.Hangman.HangmanGame;
import backend.academy.Loaders.BasicDictionaryLoader;
import backend.academy.Loaders.DictionaryLoader;
import backend.academy.Visualizations.ConsoleHangmanVisualization;
import java.io.IOException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) throws IOException {
        Dictionary dic = new Dictionary();
        DictionaryLoader dicLoader = new BasicDictionaryLoader();
        dic.add(dicLoader.loadDictionary());
        HangmanGame hg = new HangmanGame(dic, new ConsoleHangmanVisualization(), new ConsoleHangmanDialog());
        hg.play();
    }
}
