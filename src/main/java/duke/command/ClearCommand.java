package duke.command;

import duke.exception.DukeException;
import duke.main.Directory;
import duke.main.Statement;
import duke.storage.DukeFileEditor;

/**
 * Represents a clear command.
 */
public class ClearCommand implements Command {
    private final String command;

    public ClearCommand(String command) {
        this.command = command;
    }

    public ClearCommand() {
        this.command = "all";
    }

    @Override
    public Response process() throws DukeException {
        DukeFileEditor clear = new DukeFileEditor(Directory.FILEDIRECTORY);

        if (command.equals(CommandString.CLEAR_ALL)) {
            clear.clearFile();
        } else {
            clear.clearDoneTask();
        }

        return new Response(Statement.CLEAR.toString());
    }
}
