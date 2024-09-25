package backend.academy.Loaders;

import backend.academy.Hangman.Word;
import java.util.Collection;
import java.util.List;
import static backend.academy.Hangman.Difficulty.EASY;
import static backend.academy.Hangman.Difficulty.HARD;
import static backend.academy.Hangman.Difficulty.MEDIUM;

public class BasicDictionaryLoader implements DictionaryLoader {

    @Override
    public Collection<Word> loadDictionary() {
        String java = "Java";
        String nature = "Nature";
        return List.of(
            new Word("Method", java, "Function of object", MEDIUM),
            new Word("Implementation", java, "Using interface in your class", HARD),
            new Word("Object", java, "Class instance", HARD),
            new Word("Class", java, "OOP basic one", EASY),
            new Word("Apple", nature, "Red sphere fruit", EASY),
            new Word("Lemon", nature, "Yellow fruit", EASY),
            new Word("Tlower", nature, "Beautiful plant", HARD),
            new Word("Tree", nature, "Plant and data structure", MEDIUM),
            new Word("Grass", nature, "Green plant that growth on earth", EASY),
            new Word("Algorythm", java, "You should know it to optimize your code", HARD),
            new Word("Hashset", java, " The most used set implementation", MEDIUM),
            new Word("Arraylist", java, "The most used list implementation", MEDIUM)
        );
    }
}
