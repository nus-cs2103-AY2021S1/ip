public class Ui {
    private final String NEW_LINE = "\n";
    private final String HORIZONTAL_LINE =
            "    ____________________________________________________________";
    private final String PADDING = "      ";

    private final String MESSAGE_TEMPLATE = HORIZONTAL_LINE + NEW_LINE + PADDING + "%s"
            + NEW_LINE + HORIZONTAL_LINE + NEW_LINE + NEW_LINE;
    private final String MESSAGE_TEMPLATE_VERBAL = HORIZONTAL_LINE + NEW_LINE + PADDING + "Deuk: %s"
            + NEW_LINE + HORIZONTAL_LINE + NEW_LINE + NEW_LINE;
    private final String MESSAGE_TEMPLATE_ERROR = HORIZONTAL_LINE + NEW_LINE + PADDING
            + "☹ OOPS!!! %s"
            + NEW_LINE + HORIZONTAL_LINE + NEW_LINE + NEW_LINE;

    public static void print(String msg) {

    }

    public static void printError(String errMsg) {

    }

    public static void printVerbal(String msg) {

    }
}
