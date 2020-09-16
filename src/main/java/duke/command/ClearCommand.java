package duke.command;

import duke.exception.DukeException;
import duke.main.Directory;
import duke.main.Statement;
import duke.storage.DukeFileEditor;

/**
 * Represents a clear command.
 */
public class ClearCommand implements Command {
    @Override
    public Response process() throws DukeException {
        DukeFileEditor deleteAll = new DukeFileEditor(Directory.FILEDIRECTORY);
        deleteAll.clearFile();

        return new Response(Statement.CLEAR.toString());
    }
}
