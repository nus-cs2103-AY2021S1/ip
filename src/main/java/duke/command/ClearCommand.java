package duke.command;

import duke.exception.DukeException;
import duke.main.Directory;
import duke.main.Statement;
import duke.storage.DukeFileEditor;

public class ClearCommand implements Command {
    protected static final String FUNCTION = "[" + CommandString.CLEAR + "]";

    @Override
    public Response process() throws DukeException {
        DukeFileEditor deleteAll = new DukeFileEditor(Directory.FILEDIRECTORY);
        deleteAll.clearFile();

        return new Response(Statement.CLEAR.toString());
    }
}
