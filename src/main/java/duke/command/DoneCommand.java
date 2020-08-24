package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;
import duke.task.Task;

public class DoneCommand extends Command {

    private int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.markTaskDoneInList(taskNumber);
            ui.printMarkedTask(task);
            storage.writeToFile(tasks.getTasks());
        } catch (DukeException e) {
            throw e;
        }
    }
}
