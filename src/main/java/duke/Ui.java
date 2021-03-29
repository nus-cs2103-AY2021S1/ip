package duke;

import duke.io.InputHandler;
import duke.io.OutputHandler;
import duke.task.Task;

/** Handles IO between Duke and user */
public class Ui {

    /** <code>InputHandler</code> object manages inputs from user */
    private InputHandler inputHandler;

    /** <code>OutputHandler</code> object manages outputs to display */
    private OutputHandler outputHandler;

    /**
     * Constructs <code>Ui</code> object given an <code>InputHandler</code> and an <code>OutputHandler</code>
     *
     * @param inputHandler <code>InputHandler</code> for receiving inputs
     * @param outputHandler <code>OutputHandler</code> for displaying outputs
     */
    public Ui(InputHandler inputHandler, OutputHandler outputHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
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

        this.displayNow("Hello from\n" + logo);
    }

    /**
     * Prints farewell message to display.
     */
    public void displayGoodbye() {
        this.displayNow("Bye. Hope to see you again soon!");
    }

    /**
     * Passes a string to <code>outputHandler</code>, to be printed when flushed.
     * @param output String to be printed by <code>inputHandler</code>
     */
    public void queueMessageToDisplay(String output) {
        this.outputHandler.storeOutput(output);
    }

    /**
     * Prints all messages stored in <code>outputHandler</code> buffer.
     * Does nothing if no messages in buffer.
     */
    public void displayQueuedMessages() {
        if (!this.outputHandler.isEmpty()) {
            this.outputHandler.flush();
        }

        assert this.outputHandler.isEmpty();
    }

    /**
     * Immediately prints a given string using <code>outputHandler</code>.
     * Buffer of <code>outputHandler</code> does not change.
     *
     * @param output String to be printed.
     */
    public void displayNow(String output) {
        this.outputHandler.printNow(output);
    }

    /**
     * Prints error message of <code>Exception</code> using <code>Exception.getMessage()</code>.
     * Depending on implementation, <code>outputHandler</code> may handle a warning differently from a normal output.
     * @param e <code>Exception</code> whose error message is to be printed.
     */
    public void displayException(Exception e) {
        this.outputHandler.printWarning(e.getMessage());
    }

    /**
     * Prints status message after adding new task.
     *
     * @param task <code>Task</code> that was just added into Duke.
     * @param taskManagerSize Number of tasks already in Duke.
     */
    public void displayAfterAddTaskMessage(Task task, int taskManagerSize) {
        this.queueMessageToDisplay("Successfully added a new task:");
        this.queueMessageToDisplay("\t" + task.toString());
        this.queueMessageToDisplay("Now you have " + taskManagerSize + " tasks in the list.");
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
