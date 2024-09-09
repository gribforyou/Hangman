package backend.academy;

import lombok.Getter;

public final class Word {
    @Getter
    private final String text;

    @Getter
    private final String category;

    @Getter
    private final String hint;

    @Getter
    private final int difficulty;// easy(1), medium(2) and hard(3)

    public Word(String text, String category, String hint, int difficulty) {
        if (difficulty < 1 || difficulty > 3)
            throw new IllegalArgumentException("Difficulty must be between 1 and 3");
        this.text = text;
        this.category = category;
        this.hint = hint;
        this.difficulty = difficulty;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Word word)) return false;
        if (this.text.equals(word.text)) return true;
        return false;
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
