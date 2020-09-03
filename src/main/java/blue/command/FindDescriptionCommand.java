package blue.command;

import java.util.ArrayList;

import blue.Storage;
import blue.task.Task;
import blue.task.Tasks;
import blue.ui.Ui;

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
     * Find and returns a response consisting a message with tasks matching the description.
     *
     * @param tasks   the task list.
     * @param ui      interacts with user.
     * @param storage loads and save tasks.
     * @return the response to find description command.
     */
    @Override
    public CommandResponse execute(Tasks tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskList = tasks.findByDescription(this.description);
        return new CommandResponse(ui.getFoundMessage(description, taskList), this.isExit());
    }
}
