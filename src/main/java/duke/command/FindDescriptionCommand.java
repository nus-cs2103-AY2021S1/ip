package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.Tasks;

/**
 * The type Find description command finds tasks containing the description.
 */
public class FindDescriptionCommand extends FindCommand {
    /**
     * The Description.
     */
    private final String description;

    /**
     * Instantiates a new Find description command.
     *
     * @param description the description to be found.
     */
    public FindDescriptionCommand(String description) {
        this.commandType = CommandType.FIND;
        this.findCommandType = FindCommandType.DESCRIPTION;
        this.description = description;
    }

    /**
     * Find and print tasks with the description.
     *
     * @param tasks   the task list.
     * @param ui      interacts with user.
     * @param storage loads and save tasks.
     */
    public void execute(Tasks tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskList = tasks.findByDescription(this.description);
        ui.printFound(description, taskList);
    }
}
