package duke;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.task.TaskList;
import duke.ui.Response;
import duke.ui.Ui;

import java.io.IOException;

public class Duke {

    private final Ui ui;
    private final Parser parser;
    private final DukeStateManager dukeStateManager;

    public Duke() throws IOException {
        try {
            Storage storage = new Storage();
            TaskList taskList = new TaskList(storage.getTasks());
            DukeState initialState = new DukeState(taskList, storage);
            this.dukeStateManager = new DukeStateManager(initialState);
            this.ui = new Ui();
            this.parser = new Parser();
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    public Response getResponse(String input) {
        try {
            DukeState currentState = dukeStateManager.getCurrentState();
            TaskList taskList = currentState.getTaskList();
            Storage store = currentState.getStorage();
            Command c = parser.parse(input);
            Response r = c.execute(taskList, ui, store, dukeStateManager);
            return r;
        } catch (DukeException e) {
            return new Response(false, e.getFriendlyMessage());
        } catch (IOException e) {
            return new Response(false, "Unable to connect to storage :-(");
        }
    }
}
