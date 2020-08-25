package duke.command;

import duke.common.CustomException;
import duke.common.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Marks a task as done.
 */
public class DoneCommand extends Command {
    private static int taskindex;

    public DoneCommand(int taskindex) {
        DoneCommand.taskindex = taskindex - 1;
    }

    public void execute (TaskList tasks, Ui ui, Storage storage) throws CustomException {
        try {
            tasks.markAsDone(taskindex);
            Ui.display("Nice! I've marked this task as done:\n   " + tasks.getTask(taskindex));
            Storage.writeToFile(tasks.getList());
        } catch (Exception e) {
            throw new CustomException("This task does not exist!");
        }
    }
}