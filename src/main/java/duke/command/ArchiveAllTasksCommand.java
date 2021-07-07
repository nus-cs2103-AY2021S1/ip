package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ArchiveAllTasksCommand extends Command {

    /**
     * Executes the command to archive all tasks.
     *
     * @param storage Storage
     * @param taskList Task list
     * @param archive Archive
     * @param ui Ui
     * @return Output strings displayed in the UI showing task archiving
     */
    @Override
    public String[] execute(Storage storage, TaskList taskList, TaskList archive, Ui ui) {
        assert taskList != null;
        assert archive != null;
        assert ui != null;

        archive.addTasks(taskList);
        taskList.deleteAllTasks();
        return ui.getArchiveAllTasksStrings();
    }
}
