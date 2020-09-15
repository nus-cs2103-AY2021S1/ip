package duke.command;

import duke.task.Task;
import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;
import duke.util.DukeException;

/**
 * The done command is in charge of marking tasks as done when
 * the user indicates via the task number. This command allows for
 * the marking of all tasks as done.
 */
public class DoneCommand implements Command {

    private final int index;

    private final boolean isEntireList;

    /**
     * Constructs the done command which targets one task.
     * @param index the task number to be marked as done.
     */
    public DoneCommand(int index) {
        this.index = index;
        isEntireList = false;
    }

    /**
     * Constructs the done command which targets the entire task list.
     */
    public DoneCommand() {
        this.index = -1;
        isEntireList = true;
    }

    /**
     * Identifies if the command calls for an exit of the program.
     * @return the value of whether the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command and generates the response message.
     * @param tasks the task list.
     * @param ui the ui.
     * @param storage the storage.
     * @return the response message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (isEntireList) {
            for (Task task: tasks.getList()) {
                if (!task.getDone()) {
                    task.markAsDone();
                }
            }
            storage.update(tasks.getList());
            String msg = "Wow, you've done everything??\nNice, I've marked them all!";
            ui.sendMessage(msg);
            return msg;
        }
        try {
            Task task = tasks.markDone(index);
            storage.update(tasks.getList());
            String msg = ui.getTaskSuccessMessage("done", task);
            ui.sendMessage(msg);
            return msg;
        } catch (DukeException e) {
            ui.sendMessage(e.getMessage());
            return e.getMessage();
        }
    }
}
