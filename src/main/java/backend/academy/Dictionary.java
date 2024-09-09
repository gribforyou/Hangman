package backend.academy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Dictionary {
    private Set<Word> words;

    public Dictionary() {}

    public Dictionary(Set<Word> words) {
        this.words = words;
    }

    public void add(Set<Word> newWords) {
        words.addAll(newWords);
    }

    public Set<String> getCategories() {
        Set<String> categories = new HashSet<>();
        for (Word word : words) {
            categories.add(word.category());
        }
        return categories;
    }

    public Word getWord() {
        if (!words.isEmpty()) {
            int size = words.size();
            int num = new Random().nextInt(size);
            int i = 0;
            for (Word word : words) {
                if (i == num) {
                    return words.iterator().next();
                }
                i++;
            }
        }
        return null;
    }

    public Word getWord(String category) {
        List<Word> goodWords = new ArrayList<>();
        for (Word word : words) {
            if (word.category().equals(category)) {
                goodWords.add(word);
            }
        }
        if (!goodWords.isEmpty()) {
            return goodWords.get(new Random().nextInt(goodWords.size()));
        }
        else {
            return null;
        }
    }

    public Word getWord(int difficulty) {
        List<Word> goodWords = new ArrayList<>();
        for (Word word : words) {
            if (word.difficulty() == difficulty) {
                goodWords.add(word);
            }
        }
        if (!goodWords.isEmpty()) {
            return goodWords.get(new Random().nextInt(goodWords.size()));
        }
        else {
            return null;
        }
    }

    public Word getWord(String category, int difficulty) {
        List<Word> goodWords = new ArrayList<>();
        for(Word word : words) {
            if (word.category().equals(category) && word.difficulty() == difficulty) {
                goodWords.add(word);
            }
        }
        if (!goodWords.isEmpty()) {
            return goodWords.get(new Random().nextInt(goodWords.size()));
        }
        else return null;
    }
}
