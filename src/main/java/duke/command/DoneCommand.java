package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.InvalidUsageException;

/**
 * Mark a task done
 */
public class DoneCommand extends Command {
    int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Mark a specific task as done and save it to storage file
     *
     * @param taskList current task list
     * @param ui       text ui interface
     * @param storage  storage file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // Check that the task number makes sense.
        if (taskNumber >= 0 && taskNumber < taskList.size()) {
            taskList.markAsDone(taskNumber);
            storage.save(taskList);
            ui.print("Good job! I've marked this task as done:");
            ui.print(taskList.show(taskNumber));
        } else {
            ui.print("Sorry, I can't find it in your list!");
        }

    }
}