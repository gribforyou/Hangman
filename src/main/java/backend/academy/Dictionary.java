package backend.academy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class Dictionary {
    private List<Word> words;

    public Dictionary() {
        words = new ArrayList<>();
    }

    public Dictionary(List<Word> words) {
        this.words = words;
    }

    public void add(Collection<Word> newWords) {
        words.addAll(newWords);
    }

    public List<String> getCategories() {
        return words.stream()
            .map(Word::category)
            .distinct()
            .collect(Collectors.toList());
    }

    public boolean isEmpty(){
        return words.isEmpty();
    }

    public Word getWord() {
        if (words.isEmpty()) {
            throw new IllegalStateException("Dictionary is empty");
        }
        Random rand = new Random();
        int index = rand.nextInt(words.size());
        return words.get(index);
    }

    public Word getWord(String category) {
        if(category == null){
            return getWord();
        }
        List<Word> goodWords = new ArrayList<>();
        for (Word word : words) {
            if (word.category().toLowerCase().equals(category)) {
                goodWords.add(word);
            }
        }
        if (goodWords.isEmpty()) {
            throw new IllegalArgumentException("Category " + category + " not found");
        }
        return goodWords.get(new Random().nextInt(goodWords.size()));
    }

    public Word getWord(Difficulty difficulty) {
        if(difficulty == Difficulty.ANY) {
            return getWord();
        }
        List<Word> goodWords = new ArrayList<>();
        for (Word word : words) {
            if (word.difficulty() == difficulty) {
                goodWords.add(word);
            }
        }
        if (goodWords.isEmpty()) {
            throw new IllegalArgumentException("Difficulty " + difficulty + " not found");
        }
        return goodWords.get(new Random().nextInt(goodWords.size()));
    }

    public Word getWord(String category, Difficulty difficulty) {
        if(difficulty == Difficulty.ANY) {
            return getWord(category);
        }
        if(category == null){
            return getWord(difficulty);
        }
        List<Word> goodWords = new ArrayList<>();
        for (Word word : words) {
            if (word.difficulty() == difficulty && word.category().equalsIgnoreCase(category)) {
                goodWords.add(word);
            }
        }
        if (goodWords.isEmpty()) {
            throw new IllegalArgumentException("Words with " + difficulty + "level of difficulty not found in category " + category);
        }
        return goodWords.get(new Random().nextInt(goodWords.size()));
    }
}
