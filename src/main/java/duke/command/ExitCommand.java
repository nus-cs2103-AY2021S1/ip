package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Encapsulates a command to exit duke
 */
public class ExitCommand extends Command {

    /**
     * Executes the command to exit duke.
     *
     * @param storage Storage
     * @param taskList Task list
     * @param archive
     * @param ui Ui
     * @return Output strings displayed on the UI showing exit
     */
    @Override
    public String[] execute(Storage storage, TaskList taskList, TaskList archive, Ui ui) {
        assert storage != null;
        assert taskList != null;
        assert ui != null;

        storage.resetMainDataFile();
        storage.resetArchiveDataFile();
        storage.saveMainTaskList(taskList);
        storage.saveArchive(archive);
        return ui.getExitStrings();
    }
}
