package backend.academy.Dialogs;

import backend.academy.Hangman.Difficulty;
import java.util.Collection;

public interface HangmanDialog {
    String getCategory(Collection<String> categories);

    Difficulty getDifficulty();

    Character getSymbol(Collection<Character> triedSymbols);

    boolean hintDialog();

    boolean againOptionDialog();
}
