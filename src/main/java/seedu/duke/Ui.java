package seedu.duke;

import java.util.Scanner;

/**
 * Main class that interacts with user inputs.
 */
public class Ui {
    private static final String LINES = "------------------------------------------------\n";
    private static final String INTRO = "Hello! I'm Duke!\n" + "What can I do for you?\n";
    private Scanner sc;
    private String userInput;
    private Parser parse;
    private boolean isAlive = true;

    /**
     * Initializes an instance of Ui.
     *
     * @param parse Parser to be part of Ui.
     */
    public Ui(Parser parse) {
        this.sc = new Scanner(System.in);
        this.parse = parse;
    }

    public Ui() throws NullPointerException {}

    /**
     * Prints out introduction when Duke start up.
     */
    public void intro() {
        System.out.println(LINES + INTRO + LINES);
    }

    /**
     * Static method to print out divider lines.
     */
    public static void printLines() {
        System.out.println(LINES);
    }

    /**
     * Gets userInput from user.
     */
    public void getNewInput() {
        this.userInput = sc.nextLine();
        parse.read(this.userInput);
        if (this.userInput.equals("bye")) {
            this.disContinue();
        }
    }

    public String getUserInput(String input) {
        if (input.equals("bye")) {
            return Ui.endDuke();
        } else {
            String output = parse.readInput(input);
            return output;
        }
    }

    /**
     * Gets status of Ui.
     *
     * @return false if "bye" command is input by user. Otherwise, true.
     */
    public boolean checkDukeStatus() {
        return this.isAlive;
    }

    /**
     * Sets boolean isAlive to false. s duke program.
     */
    public void disContinue() {
        this.isAlive = false;
    }

    /**
     * Prints out output from Parser.
     *
     * @param input String that is processed by Parser.
     */
    public static void print(String input) {
        Ui.printLines();
        System.out.println(input);
        Ui.printLines();
    }

    /**
     * Method to end Duke Program when user input "Bye".
     */
    public static void bye() {
        Ui.printLines();
        System.out.println("    Bye! Hope to see you again soon." + "\n");
        Ui.printLines();
    }

    public static String endDuke() {
        String goodBye = "Bye! Hope to see you again soon." + "\n";
        String exit = goodBye;
        return exit;
    }
}
