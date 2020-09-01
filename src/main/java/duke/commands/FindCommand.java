package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.util.List;

/**
 * Command sub-type to define finding Tasks.
 */
public class FindCommand extends Command {

    /**
     * Creates FindCommand object.
     *
     * @param attributes input attributes from user
     */
    public FindCommand(String attributes) {
        this.attributes = attributes;
    }

    @Override
    public boolean run(TaskList taskList, Storage storage, Ui ui) {
        List<String> foundTasks = taskList.findTasks(attributes);
        ui.writeSearch(foundTasks);
        return true;
    }
}
