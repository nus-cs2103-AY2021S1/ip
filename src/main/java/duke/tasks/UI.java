package duke.tasks;

import java.util.Scanner;

/**
 * Represents the UI to be displayed on the console.
 * Prints out the format for the divider lines.
 */
public class UI {
    protected Scanner myObj;

    /**
     * Constructor for the UI object that initialises a scanner for
     * user inputs.
     */
    public UI() {
        this.myObj = new Scanner(System.in);
    }

    /**
     * Takes in the user's next input and returns it as a string.
     * @return string format of user input.
     */
    public String nextInput() {
        return this.myObj.nextLine();
    }

    /**
     * Prints the formatting line before and after a list of task is displayed.
     */
    public static void dividerLine() {
        System.out.println("______________________________________________________");
    }

    /**
     * Duke's intro when a user starts up Duke.
     */
    public static void intro() {
        //Intro
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }
}
