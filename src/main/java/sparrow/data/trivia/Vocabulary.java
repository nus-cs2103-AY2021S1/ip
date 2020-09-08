package sparrow.data.trivia;

public class Vocabulary {
    private final String word;
    private String definition;
    private boolean hasDefinition;

    public static final String DICTIONARY_PREFIX = "https://dictionary.cambridge.org/dictionary/english/";

    public Vocabulary(String word) {
        this.word = word;
        this.hasDefinition = false;
    }

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

    public String define() {
        return hasDefinition ? definition : DICTIONARY_PREFIX + word;
    }
}
