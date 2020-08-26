package ui;

import java.util.Scanner;

import parser.Parser;

import duke.DukeException;

public class Ui {
    private final String LINE_FORMAT = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private final String OUTPUT_FORMAT = "  %s\n";
    private boolean isOngoing;
    private final Parser parser;

    public Ui(Parser parser) {
        isOngoing = false;
        this.parser = parser;
    }

    public void run() {
        isOngoing = true;
        greeting();
        Scanner scanner = new Scanner(System.in);
        while (isOngoing) {
            String input = scanner.nextLine();
            systemOutput(input);
        }
    }

    public void systemOutput(String input) {
        System.out.println(LINE_FORMAT + "\n");
        try {
            String output = parser.scenarios(input);
            if (output.equals("bye")) {
                isOngoing = false;
                goodBye();
            } else {
                System.out.println(output);
            }
        } catch (DukeException e) {
            System.out.printf(OUTPUT_FORMAT, e.getMessage());
        }
        System.out.println("\n" + LINE_FORMAT);
    }

    public void greeting() {
        System.out.println(LINE_FORMAT + "\n         (^v^)");
        System.out.printf(OUTPUT_FORMAT, "Hey there! I'm JavaDuke");
        System.out.printf(OUTPUT_FORMAT, "What can I do for you?\n" + LINE_FORMAT);
    }

    public void goodBye() {
        System.out.printf(OUTPUT_FORMAT, "          *(^v^)");
        System.out.printf(OUTPUT_FORMAT, "Bye. Hope to see you again soon!");
    }

    public void showLoadingError() {
        System.out.println("An error occurred while loading the task list. Please rerun the program again.");
    }
}
