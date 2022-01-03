package duke;

/**
 * Denotes Common Strings used in <code>Duke</code>
 */
public enum CommonString {
    LOGO(" ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n"),
    DUKE_DATETIME_FORMAT("dd MMM yyyy hh:mm:ss a"),
    DUKE_FILE_PATH("data/dukeData.txt");

    private final String value;

    CommonString(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
