package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.Tasks;

import java.util.ArrayList;

public class FindDescriptionCommand extends FindCommand {
    private final CommandType commandType;
    private final String description;

    public FindDescriptionCommand(String description) {
        this.commandType = CommandType.FIND;
        this.findCommandType = FindCommandType.DESCRIPTION;
        this.description = description;
    }

    public void execute(Tasks tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskList = tasks.findByDescription(this.description);
        ui.printFound(description, taskList);
    }
}
