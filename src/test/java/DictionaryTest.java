import backend.academy.Hangman.Dictionary;
import backend.academy.Hangman.Difficulty;
import backend.academy.Hangman.Word;
import backend.academy.Loaders.BasicDictionaryLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DictionaryTest {

    Dictionary dictionary;

    DictionaryTest() {
        dictionary = new Dictionary();
        dictionary.add(new BasicDictionaryLoader().loadDictionary());
    }

    @Test
    void testGettingEasyWord(){
        Difficulty difficulty = Difficulty.EASY;
        Word word = dictionary.getWord(difficulty);
        Assertions.assertEquals(difficulty, word.difficulty());
    }

    @Test
    void testGettingMediumWord(){
        Difficulty difficulty = Difficulty.MEDIUM;
        Word word = dictionary.getWord(difficulty);
        Assertions.assertEquals(difficulty, word.difficulty());
    }

    @Test
    void testGettingHardWord(){
        Difficulty difficulty = Difficulty.HARD;
        Word word = dictionary.getWord(difficulty);
        Assertions.assertEquals(difficulty, word.difficulty());
    }

    @Test
    void testGettingByCategory(){
        String category = dictionary.getCategories().getFirst();
        Word word = dictionary.getWord(category);
        Assertions.assertEquals(category, word.category());
    }

    @Test
    void testGettingByNotExistingCategory(){
        String category = dictionary.getCategories().getFirst()+"notexistingcategory";
        Assertions.assertThrows(IllegalArgumentException.class, () -> dictionary.getWord(category));
    }

    @Test
    void testGettingByUppercaseCategory(){
        String category = dictionary.getCategories().getFirst().toUpperCase();
        Word word = dictionary.getWord(category);
        Assertions.assertEquals(category, word.category().toUpperCase());
    }

    @Test
    void testGettingByCategoryAndDifficulty(){
        String category = dictionary.getCategories().getFirst();
        Difficulty difficulty = Difficulty.MEDIUM;
        Word word = null;
        try{
            word = dictionary.getWord(category, difficulty);
        }
        catch(IllegalArgumentException e){
            Assertions.assertThrows(IllegalArgumentException.class, () -> dictionary.getWord(category, difficulty));
        }
        Assertions.assertEquals(difficulty, word.difficulty());
        Assertions.assertEquals(category, word.category());
    }
}
