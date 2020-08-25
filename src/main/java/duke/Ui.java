package duke;

import duke.io.InputHandler;
import duke.io.OutputHandler;

/** Handles IO between app and user */
public class Ui {

    /** <code>InputHandler</code> object manages inputs from user */
    private InputHandler inputHandler;

    /** <code>OutputHandler</code> object manages outputs to display */
    private OutputHandler outputHandler;

    /**
     * Constructs <code>Ui</code> object given an <code>InputHandler</code> and an <code>OutputHandler</code>
     *
     * @param in <code>InputHandler</code> for receiving inputs
     * @param out <code>OutputHandler</code> for displaying outputs
     */
    public Ui(InputHandler in, OutputHandler out) {
        this.inputHandler = in;
        this.outputHandler = out;
    }

    /**
     * Prints welcome message to display.
     */
    public void displayGreet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        this.display("Hello from\n" + logo);
    }

    /**
     * Prints farewell message to display.
     */
    public void displayGoodbye() {
        this.display("Bye. Hope to see you again soon!");
    }

    /**
     * Passes a string to <code>outputHandler</code>.
     * @param output String to be printed by <code>inputHandler</code>
     */
    public void display(String output) {
        this.outputHandler.print(output);
    }

    /**
     * Prints error message of <code>Exception</code> using <code>Exception.getMessage()</code>.
     * @param e <code>Exception</code> whose error message is to be printed.
     */
    public void displayException(Exception e) {
        this.display(e.getMessage());
    }

    /**
     * Reads input by user from <code>inputHandler</code>.
     *
     * @return user input as <code>String</code>.
     */
    public String readCommand() {
        return this.inputHandler.input();
    }

}
