package duke;

public class Ui {
    public static void printWith4Indent(String message) {
        System.out.println(StringConstants.FOUR_SPACES + message);
    }

    public static void printWith6Indent(String message) {
        System.out.println(StringConstants.SIX_SPACES + message);
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void printMessageBetweenLines(String message) {
        printWith4Indent(StringConstants.LINE);
        printWith6Indent(message);
        printWith4Indent(StringConstants.LINE);
    }

    public static void printMessagesBetweenLines(String[] messages) {
        printWith4Indent(StringConstants.LINE);
        for (String message: messages) {
            printWith6Indent(message);
        }
        printWith4Indent(StringConstants.LINE);
    }

    public static void printExceptionBetweenLines(Exception exception) {
        printMessageBetweenLines("Error: " + exception.getMessage());
    }

    public static void printGreeting() {
        printMessagesBetweenLines(StringConstants.GREETING_MESSAGES);
    }
}
