package duke.command;

import duke.DukeList;
import duke.Ui;
import duke.Storage;

/**
 * A command dealing with listing entries from the Duke-list.
 */
public class ListCommand extends Command {
    /** The type of the ListCommand. */
    private CommandType commandType;

    /**
     * Constructs a new ListCommand object of the specified CommandType.
     *
     * @param commandType The type of the ListCommand.
     */
    public ListCommand(CommandType commandType) {
        this.commandType = commandType;
    }


    @Override
    public void execute(Ui ui, Storage storage) throws FileException {
        DukeList<?> entries;
        if (commandType.equals(CommandType.LIST)) {
            entries = storage.getTasks();
        } else {
            entries = storage.getFinances();
        }
        String[] lines = entries.getEntries();
        if (lines.length > 0) {
            ui.listNumberedEntries(lines);
        } else {
            ui.setNoEntriesMessage();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return commandType.toString();
    }
}

