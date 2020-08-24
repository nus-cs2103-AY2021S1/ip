package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;
import duke.task.Task;

public class DeleteCommand extends Command {
    private int taskNumber;
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.deleteTaskFromList(taskNumber);
            ui.printDeletedTask(task, tasks.size());
            storage.writeToFile(tasks.getTasks());
        } catch (DukeException e) {
            throw e;
        }
    }
}
