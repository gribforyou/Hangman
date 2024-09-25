package backend.academy.Hangman;

import lombok.Getter;

public final class Word {
    @Getter
    private final String text;

    @Getter
    private final String category;

    @Getter
    private final String hint;

    @Getter
    private final Difficulty difficulty;

    public Word(String text, String category, String hint, Difficulty difficulty) {
        this.text = text;
        this.category = category;
        this.hint = hint;
        this.difficulty = difficulty;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Word word)) {
            return false;
        }
        return this.text.equals(word.text);
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }

    @Override
    public String toString() {
        return text;
    }
}
