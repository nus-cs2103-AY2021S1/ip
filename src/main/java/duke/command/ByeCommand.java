package duke.command;

import duke.main.Storage;
import duke.main.TaskList;

/**
 * Abstraction for the bye command.
 * This class saves the TaskList into a file before sending a farewell message.
 */
public class ByeCommand extends Command {
    private static final String BYE = "All changes saved, hope to see you again!";

    private final Storage storage;

    /**
     * Constructs a new ByeCommand object.
     *
     * @param storage Storage to write to a file with.
     * @param taskList TaskList to be operated on.
     */
    public ByeCommand(Storage storage, TaskList taskList) {
        super(taskList);
        this.storage = storage;
    }

    /**
     * Saves the TaskList to a file and returns a farewell message.
     *
     * @return Farewell message to be displayed on the GUI.
     */
    @Override
    public String execute() {
        storage.writeToFile(taskList);
        return BYE;
    }
}
