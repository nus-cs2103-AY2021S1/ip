package seedu.duke;

import java.util.Scanner;
public class Ui {
    private final static String LINES = "------------------------------------------------\n";
    private final static String INTRO = "Hello! I'm Duke!\n" + "What can I do for you?\n";
    private Scanner sc;
    private String userInput;
    private Parser parse;
    private boolean cont = true;

    public Ui(Parser parse) {
        this.sc = new Scanner(System.in);
        this.parse = parse;
    }

    public void intro() {
        System.out.println(LINES + INTRO + LINES);
    }
    public static void printLines() {
        System.out.println(LINES);
    }
    public void getNewInput() {
        this.userInput = sc.nextLine();
        parse.read(this.userInput);
        if (this.userInput.equals("bye")) {
            this.disContinue();
        }
    }

    public boolean getContinue() {
        return this.cont;
    }

    public void disContinue() {
        this.cont = false;
    }

    public static void print(String input) {
        Ui.printLines();
        System.out.println(input);
        Ui.printLines();
    }

    public static void bye() {
        Ui.printLines();
        System.out.println("    Bye! Hope to see you again soon." + "\n");
        Ui.printLines();
    }
}
