package seedu.duke;

import javafx.application.Application;

/**
 * Main part of logic of Duke.
 */
public class Duke {
    /**
     * Holds execution of orders.
     */
    private Executor executor;

    /**
     * Initializes Duke object.
     * Finishes essential settings.
     */
    public Duke() {
        Storage storage = new Storage();
        TaskList taskList = new TaskList(storage);
        Parser parser = new Parser();
        Ui ui = new Ui();
        this.executor = new Executor(parser, ui, taskList);
    }

    /**
     * Gives the task list which contains history tasks.
     * @return A task list with history tasks.
     */
    public TaskList getHistoryList() {
        return executor.getTaskList();
    }

    /**
     * Gives the execution result of userInput.
     * @return Execution result.
     */
    public String run(String userInput) {
        return this.executor.execute(userInput);
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
