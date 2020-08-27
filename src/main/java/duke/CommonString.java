package duke;

// Enum class to denote commonly used, constant String values in duke.Duke
public enum CommonString {
    DELETE("DELETE"),
    DONE("DONE"),

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
