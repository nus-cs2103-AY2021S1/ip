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


    /**
     * Prints task that is being archived.
     * @param tasks TaskList that is being executed on.
     * @param archives Tasklist that archived tasks is placed in.
     * @param ui Ui to update the user.
     * @param storage Storage to update the text file.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, TaskList archives, Ui ui, Storage storage)
            throws DukeException {
        ui.printString(getExecuteString(tasks, archives, ui, storage));
    }

    /**
     * Archives task. Task is first deleted from the main list, and then added
     * into an archived list. The text files of the main task list and archived list
     * is updated.
     * @param tasks TaskList that is being executed on.
     * @param archives TaskList that contains the archived tasks.
     * @param ui Ui to update the user.
     * @param storage Storage to update the text file.
     * @return String of archived task.
     * @throws DukeException
     */
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
