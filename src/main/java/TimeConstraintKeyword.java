public enum TimeConstraintKeyword {
    EVENT_KEYWORD(" /at "),
    DEADLINE_KEYWORD(" /by ");

    private final String keyWord;

    TimeConstraintKeyword(String key) {
        this.keyWord = key;
    }

    public String getKeyWord() {
        return this.keyWord;
    }
}
