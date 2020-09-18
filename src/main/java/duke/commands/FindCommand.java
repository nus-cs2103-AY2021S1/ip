package duke.commands;

import duke.DukeException;
import duke.tasks.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.util.ArrayList;
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
    public boolean runCLI(TaskList taskList, Storage storage, Ui ui) {
        runGUI(taskList, storage, ui);
        return true;
    }

    @Override
    public String runGUI(TaskList taskList, Storage storage, Ui ui) {
        List<Task> foundTasks = taskList.findTasks(attributes);
        return ui.writeSearch(foundTasks);
    }
}
