package duke.command;

import duke.component.DukeException;
import duke.component.Storage;
import duke.component.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {
    String bodyCommand;

    public FindCommand(String fullCommand) throws DukeException {
        try {
            this.bodyCommand = fullCommand.split(" ")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("please enter a term to search for");
        }
    }

    /**
     * Main logic executed to perform search
     * @param tasks list of tasks.
     * @param ui instance of Ui to deal with user interface.
     * @param storage to read / write to storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> listOfTasksFound = new ArrayList<>();
        for (Task task : tasks.getList()) {
            if (task.isContain(this.bodyCommand)) {
                listOfTasksFound.add(task);
            }
        }
        ui.findMessage(listOfTasksFound);
    }
}
