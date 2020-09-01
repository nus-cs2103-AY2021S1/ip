package duke.commands;

import duke.util.OutputUi;
import duke.util.Storage;
import duke.tasks.Task;
import duke.util.TaskList;
import duke.util.Ui;
import duke.DukeException;

import java.io.IOException;

/**
 * Carries out the deletion of tasks from tasklist, and saves the changes to hard disk.
 */
public class DeleteCommand extends Command {
    private static int taskIndex;

    /**
     * Constructor.
     * @param taskIndex Integer indicating the index of task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Prints output of task deletion as well as delete the task, thereafter saving to hard disk.
     * @param tasks TaskList containing Tasks.
     * @param ui Ui object that handles printing of any necessary output.
     * @param storage Storage object that handles saving Tasks to hard disk.
     * @throws DukeException DukeException.
     * @throws IOException IOException.
     */
    public String execute(TaskList tasks, OutputUi ui, Storage storage) throws DukeException {
        Task t = tasks.deleteTask(taskIndex);

        ui.reset();
        ui.addSentence("pingu delete this task");
        ui.addSentence("\t" + t);
        ui.addSentence("number of tasks: " + tasks.getTasklist().size());

        super.execute(tasks, ui, storage);

        return ui.getResponse();
    }
}
