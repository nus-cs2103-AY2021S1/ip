package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskListHandler;
import duke.Ui;
import duke.task.Task;

/**
 * Marks a provided task as done.
 * Inherits from AbstractModifyTaskCommand which inherits from generic command class.
 */
public class DoneCommand extends AbstractModifyTaskCommand {
    public static final String COMMAND_WORD = "done";
    public static final String COMMAND_USAGE = COMMAND_WORD + ": Completes the task specified by the task number in "
        + "the list.\n"
        + "Fields: " + "[task number] \n"
        + "Example: " + COMMAND_WORD + " 1 ";


    public DoneCommand(Task task) {
        super(task);
    }

    /**
     * Marks given task as done, printing success and saving updated list to save file.
     *
     * @param handler Task list.
     * @param storage Storage instance.
     */
    @Override
    public void execute(TaskListHandler handler, Storage storage, String input) throws DukeException {
        ArrayList<Task> tasks = handler.getTasks();
        if (task.isDone()) {
            throw new DukeException("\u2639 Whoops, The following task has already been completed: \n" + "    " + task);
        }
        task.markAsDone();
        Ui.printSuccess("done", task, tasks);
        handler.printList();
        handler.saveCurrentTaskList(input);
        storage.saveToFile(tasks);
    }
}
