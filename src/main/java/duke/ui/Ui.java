package duke.ui;

import java.util.Scanner;

public class Ui {

	static String INDENTATION = "    ";

	static String LINE = "-------------------------------------------------------------";

	static String LOGO = " ____        _        \n"
			+ "|  _ \\ _   _| | _____ \n"
			+ "| | | | | | | |/ / _ \\\n"
			+ "| |_| | |_| |   <  __/\n"
			+ "|____/ \\__,_|_|\\_\\___|\n";

	static String OPENING = "Hello from\n" + LOGO;

	Scanner scanner = new Scanner(System.in);

	/**
	 * Prints the entire opening message.
	 */
	public void showWelcome() {
		System.out.println(OPENING);
		printMessage("Hello! I'm Duke.\nHow can I help you today?");
	}

	/**
	 * Prints the input string on the console between two lines.
	 */
	public void printMessage(String message) {
		showLine();
		String[] splitByNewLine = message.split("\\r?\\n");
		for (String str : splitByNewLine) {
			indent();
			System.out.println(str);
		}
		showLine();
	}

	/**
	 * Reads a line of command from the CLI and returns it as a String.
	 *
	 * @return The String of the input in CLI.
	 */
	public String readCommand() {
		String command = scanner.nextLine();
		return command;
	}

	/**
	 * Prints the input String with exclamation marks at the front and end of
	 * the String to signify an error message.
	 *
	 * @param errorMessage The String of the error message to be printed.
	 */
	public void showError(String errorMessage) {
		showLine();
		String[] splitByNewLine = errorMessage.split("\\r?\\n");
		for (String str : splitByNewLine) {
			indent();
			System.out.println("!!! " + str + " !!!");
		}
		showLine();
	}

	/**
	 * Prints a line in the CLI.
	 */
	public void showLine() {
		indent();
		System.out.println(LINE);
	}

	void indent() {
		System.out.print(INDENTATION);
	}


}
