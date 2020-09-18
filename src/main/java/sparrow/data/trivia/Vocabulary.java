package sparrow.data.trivia;

public class Vocabulary {
    public static final String DICTIONARY_PREFIX = "https://dictionary.cambridge.org/dictionary/english/";

    private final String word;
    private String definition;
    private boolean hasDefinition;


    /**
     * Creates a Vocabulary object without a definition.
     */
    public Vocabulary(String word) {
        this.word = word;
        this.hasDefinition = false;
    }

    /**
     * Creates a Vocabulary object with a definition.
     */
    public Vocabulary(String word, String definition) {
        this.word = word;
        this.definition = definition;
        this.hasDefinition = true;
    }

    public String getWord() {
        return word;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
        this.hasDefinition = true;
    }

    public boolean hasDefinition() {
        return hasDefinition;
    }

    public String define() {
        return hasDefinition ? definition : DICTIONARY_PREFIX + word;
    }

    @Override
    public String toString() {
        return word;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Vocabulary
                && word.equals(((Vocabulary) other).word)
                && define().equals(((Vocabulary) other).define()));
    }
}
