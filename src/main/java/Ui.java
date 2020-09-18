import java.io.IOException;

public class Ui {
    public static final String NEW_LINE = "\n";
    public static final String HORIZONTAL_LINE =
            "    ____________________________________________________________";
    public static final String PADDING = "      ";

    private static final String MESSAGE_TEMPLATE = HORIZONTAL_LINE + NEW_LINE + PADDING + "%s"
            + NEW_LINE + HORIZONTAL_LINE + NEW_LINE + NEW_LINE;
    private static final String MESSAGE_TEMPLATE_VERBAL = HORIZONTAL_LINE + NEW_LINE + PADDING + "Deuk: %s"
            + NEW_LINE + HORIZONTAL_LINE + NEW_LINE + NEW_LINE;
    private static final String MESSAGE_TEMPLATE_ERROR = HORIZONTAL_LINE + NEW_LINE + PADDING
            + "☹ OOPS!!! %s"
            + NEW_LINE + HORIZONTAL_LINE + NEW_LINE + NEW_LINE;

    public static void print(String msg) {
        System.out.printf(MESSAGE_TEMPLATE, msg);
    }

    public static void printError(String errMsg) {
        System.out.printf(MESSAGE_TEMPLATE_ERROR, errMsg);
    }

    public static void printVerbal(String msg) {
        System.out.printf(MESSAGE_TEMPLATE_VERBAL, msg);
    }

    public static void sayHello(String logo, String introMessage) {
        System.out.printf(logo + NEW_LINE + MESSAGE_TEMPLATE_VERBAL, introMessage);
    }

    public static void sayGoodbye(Storage storage, TaskList tasks) {
        try {
            storage.saveTasksToDisk(tasks);
        } catch (IOException ex) {
            Ui.printError(ex.getMessage());
        }
        Ui.printVerbal("Goodbye, hope to see you again!");
    }
}
