package duke.commands;

import duke.*;
import duke.tasks.Task;
import duke.util.OutputUi;
import duke.util.Storage;
import duke.util.TaskList;

import java.io.IOException;

/**
 * Command invoked when a task is called to be marked as done.
 */
public class DoneCommand extends Command {
    private static int taskIndex;

    /**
     * Constructor.
     * @param taskIndex Integer indicating the index of task to be marked as done.
     */
    public DoneCommand(int taskIndex) {
        assert taskIndex > 0;
        this.taskIndex = taskIndex;
    }

    /**
     * Marks task as done, prints output, saves changes to hard disk.
     * @param tasks TaskList containing Tasks.
     * @param ui Ui object that handles printing of any necessary output.
     * @param storage Storage object that handles saving Tasks to hard disk.
     * @throws DukeException DukeException.
     * @throws IOException IOException.
     */
    public String execute(TaskList tasks, OutputUi ui, Storage storage) throws DukeException {
        Task t = tasks.doneTask(taskIndex);

        ui.reset();
        ui.addSentence("pingu mark this task as done");
        ui.addSentence("\t" + t);

        super.execute(tasks, ui, storage);

        return ui.getResponse();
    }
}
