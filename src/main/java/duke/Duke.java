package duke;

import duke.command.Command;
import duke.exceptions.DukeStorageException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

public class Duke {

    private Storage storage;
    private TaskList taskList;

    public Duke(boolean useHistory) {
        this(Storage.FILE_PATH, useHistory);
    }

    public Duke(String filePath, boolean useHistory) throws DukeStorageException {
        if (useHistory) {
            try {
                storage = new Storage(filePath);
                taskList = new TaskList(storage.load());
            } catch (DukeStorageException e) {
                throw new DukeStorageException("I think I lost my memory... Let me start afresh.");
            }
        } else {
            storage = new Storage(filePath);
            taskList = new TaskList();
        }
    }

    public Response getResponse(String input) {
        Command c = Parser.parse(input);
        c.execute(taskList, storage);
        return new Response(c.isExit(), c.feedback());
    }
}
