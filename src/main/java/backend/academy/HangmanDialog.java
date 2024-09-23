package backend.academy;

import java.util.Collection;
import java.util.List;

public interface HangmanDialog {
    public String getCategory(Collection<String> categories);
    public Difficulty getDifficulty();
    public Character getSymbol(Collection<Character> triedSymbols);
    public boolean HintDialog();
    public boolean againOptionDialog();
}
