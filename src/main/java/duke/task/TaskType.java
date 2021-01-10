package duke.task;

/**
 * Represents the different task types available.
 */
public enum TaskType {
    TODO("", 3, 3), DEADLINE("/by", 4, 5),
    EVENT("/at", 4, 5);

    private final String delimiter;
    private final int minLength;
    private final int maxLength;

    TaskType(String delimiter, int minLength, int maxLength) {
        this.delimiter = delimiter;
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public int getMinLength() {
        return minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

}
