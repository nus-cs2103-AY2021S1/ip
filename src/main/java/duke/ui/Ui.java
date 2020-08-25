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

	public void showWelcome() {
		System.out.println(OPENING);
		printMessage("Hello! I'm Duke.\nHow can I help you today?");
	}

	public void printMessage(String message) {
		showLine();
		String[] splitByNewLine = message.split("\\r?\\n");
		for (String str : splitByNewLine) {
			indent();
			System.out.println(str);
		}
		showLine();
	}

	public String readCommand() {
		String command = scanner.nextLine();
		return command;
	}

	public void showError(String errorMessage) {
		showLine();
		String[] splitByNewLine = errorMessage.split("\\r?\\n");
		for (String str : splitByNewLine) {
			indent();
			System.out.println("!!! " + str + " !!!");
		}
		showLine();
	}

	public void showLine() {
		indent();
		System.out.println(LINE);
	}

	void indent() {
		System.out.print(INDENTATION);
	}


}
