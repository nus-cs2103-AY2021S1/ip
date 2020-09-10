package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class ArchiveCommand extends Command {
    /** Task to be archived */
    private int taskNumber;

    public ArchiveCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }



    @Override
    public void execute(TaskList tasks, TaskList archives, Ui ui, Storage storage)
            throws DukeException {
        ui.printString(getExecuteString(tasks, archives, ui, storage));
    }

    @Override
    public String getExecuteString(TaskList tasks, TaskList archives, Ui ui, Storage storage)
            throws DukeException {
        try {
            Task task = tasks.deleteTaskFromList(taskNumber);
            archives.addTask(task);
            storage.writeToMain(tasks.getTasks());
            storage.writeToArchive(archives.getTasks());
            return ui.getArchivedTaskString(task);
        } catch (DukeException e) {
            throw e;
        }
    }
}
