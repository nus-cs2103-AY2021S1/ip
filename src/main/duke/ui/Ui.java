package duke.ui;

import duke.task.Task;

/**
 * Utility class containing String formatting and printing
 */
public final class Ui {

    private final static String STRING_TOP_LINE = "_____________________________________________________DUKE___";
    private final static String STRING_BOTTOM_LINE = "------------------------------------------------------------";
    private final static String STRING_GREETING = "Hello, this is Duke.";
    private final static String STRING_EXIT = "Goodbye.";

    public static void printStartLine() {
        System.out.println(STRING_TOP_LINE);
    }

    public static void printEndLine() {
        System.out.println(STRING_BOTTOM_LINE);
    }

    /**
     * Prints text between the start and end lines.
     *
     * @param text the text to be printed
     */
    public static void wrapText(String text) {
        printStartLine();
        System.out.println(text.trim());
        printEndLine();
    }

    public static void showWelcome() {
        wrapText(STRING_GREETING);
    }

    public static void showExit() {
        wrapText(STRING_EXIT);
    }

    public static void showTaskAddition(Task task) {
        wrapText(String.format("Added: %s", task.toString()));
    }

    public static void showTaskDeletion(Task task) {
        wrapText(String.format("Deleted: %s", task.toString()));
    }

    public static void showTaskCompletion(Task task) {
        wrapText(String.format("Completed: %s", task.toString()));
    }

}
