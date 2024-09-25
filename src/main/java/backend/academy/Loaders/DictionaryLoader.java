package backend.academy.Loaders;

import backend.academy.Hangman.Word;
import java.util.Collection;

public interface DictionaryLoader {
    Collection<Word> loadDictionary();
}
