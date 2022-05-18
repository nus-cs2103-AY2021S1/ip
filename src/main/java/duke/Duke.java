package duke;

import java.io.IOException;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.DukeState;
import duke.storage.DukeStateManager;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.ErrorResponse;
import duke.ui.Response;
import duke.ui.Ui;

public class Duke {

    private final Ui ui;
    private final Parser parser;
    private final DukeStateManager dukeStateManager;

    /**
     * Constructs a new Duke object
     *
     * @throws IOException if error accessing storage file
     */
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

    /**
     * Creates a Response for the GUI given a user input String
     *
     * @param input user entered input into GUI
     * @return Response containing data for GUI
     */
    public Response getResponse(String input) {
        try {
            DukeState currentState = dukeStateManager.getCurrentState();
            TaskList taskList = currentState.getTaskList();
            Storage store = currentState.getStorage();
            Command c = parser.parse(input);
            Response r = c.execute(taskList, ui, store, dukeStateManager);
            return r;
        } catch (DukeException e) {
            return new ErrorResponse(false, e.getFriendlyMessage());
        } catch (IOException e) {
            return new Response(false, "Unable to connect to storage :-(");
        }
    }
}
