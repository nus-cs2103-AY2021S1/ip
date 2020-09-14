package duke;

import duke.lists.TaskList;

/**
 * Duke chat bot 
 */
public class Duke {
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;
    private final Parser parser;

    /**
     * Initializes a new ui, storage, parser and taskList
     */
    public Duke () {
        ui = new Ui();
        storage = new Storage("data/data.txt");
        parser = new Parser();
        taskList = new TaskList(storage.loadTasks());
    }

    /**
     * Returns the welcome message for duke 
     * 
     * @return welcome message
     */
    public String getWelcome() {
        return ui.getOutputMessage();
    }

    /**
     * Takes in input from the user, processes the input and returns the appropriate response to the user
     * 
     * @param input input from the user
     * @return message to show to the user
     */
    public String getResponse(String input) {
        parser.sortInput(input, taskList, storage, ui);
        return ui.getOutputMessage();
    }
}
