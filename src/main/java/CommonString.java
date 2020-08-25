
// Enum class to denote commonly used, constant String values in Duke
public enum CommonString {
    LINE("____________________________________________________________"), // Single Line for divider
    LOGO(" ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n"),
    DUKE_DATETIME_FORMAT("dd MMM yyyy hh:mm:ss a");

    private final String value;

    CommonString(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
