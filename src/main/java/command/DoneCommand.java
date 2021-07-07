package command;

import duke.Storage;
import duke.TaskList;
import ui.Ui;

/**
 * Mark a task done
 */
public class DoneCommand extends Command {
    private int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Mark a specific task as done and save it to storage file
     *
     * @param tasks   current task list
     * @param ui      text ui interface
     * @param storage storage file
     */
    @Override
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        // Check that the task number makes sense.
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            tasks.markAsDone(taskNumber);
            storage.save(tasks);
            ui.print("Good job! I've marked this task as done:");
            ui.print(tasks.show(taskNumber));
            return new CommandResult(
                    "Good job! I've marked this task as done: \n"
                            + tasks.show(taskNumber)
            );
        } else {
            ui.print("Sorry, I can't find it in your list!");
            return new CommandResult("Sorry, I can't find it in your list!");
        }

    }
}
