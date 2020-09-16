package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to shut down chat bot.
 */
public class ByeCommand extends Command {

    /**
     * Class constructor.
     * @param command String parsed by Parser object.
     */
    public ByeCommand(String command) {
        super(command, true);
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui)
            throws IOException {
        assert storage != null : "Storage object cannot be null";
        assert tasks != null : "TaskList object cannot be null";
        assert ui != null : "Ui object cannot be null";
        storage.saveTasks(tasks);
        return ui.printSave() + "\n" + ui.printFarewell();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ByeCommand;
    }
}
