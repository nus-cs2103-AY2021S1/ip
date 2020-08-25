package duke.ui;

import duke.task.Task;

/**
 * Utility class containing String formatting and printing
 */
public final class Ui {

	private final static String LINE1 = "_____________________________________________________DUKE___";
	private final static String LINE2 = "------------------------------------------------------------";
	private final static String WELCOME = "Hello, this is Duke.";
	private final static String EXIT = "Goodbye.";

	public static void printStartLine() {
		System.out.println(LINE1);
	}

	public static void printEndLine() {
		System.out.println(LINE2);
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
		wrapText(WELCOME);
	}

	public static void showExit() {
		wrapText(EXIT);
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
