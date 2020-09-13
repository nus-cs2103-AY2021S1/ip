package duke.command;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskType;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class AddEventCommand extends AddCommand {

    private final String description;

    public AddEventCommand(String description) {
        this.description = description;
    }

    /**
     * Adds an {@code Event} task into the {@code TaskList}.
     *
     * @param tasks Task List object.
     * @param ui User Interface object.
     * @param storage Storage object.
     * @throws DukeException If input format is wrong.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] resultArr = Parser.parseTaskDescription(description, TaskType.EVENT);
        String taskDetails = resultArr[0];
        String timeFrame = resultArr[1];
        return addTask(new Event(taskDetails, timeFrame), tasks, ui, storage);
    }
}
