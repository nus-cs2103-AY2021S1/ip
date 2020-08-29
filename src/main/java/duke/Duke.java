package duke;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.task.TaskList;
import duke.ui.Response;
import duke.ui.Ui;

import java.io.IOException;

public class Duke {

    private Storage store;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    private Duke(Storage store, TaskList taskList, Ui ui, Parser parser) {
        this.store = store;
        this.taskList = taskList;
        this.ui = ui;
        this.parser = parser;
    }

    public static Duke startDuke() {
        Storage store = new Storage();
        TaskList taskList = new TaskList();
        Ui ui =  new Ui();
        Parser parser = new Parser();
        try {
            store.initializeStorage();
            taskList = new TaskList(store.getTasks());
        } catch (IOException e) {
            System.out.println("Error connecting to storage, actions made will not be saved");
        }
        return new Duke(store, taskList, ui, parser);
    }

    public Response getResponse(String input) {
        try {
            Command c = parser.parse(input);
            Response r = c.execute(taskList, ui, store);
            return r;
        } catch (DukeException e) {
            return new Response(false, e.getFriendlyMessage());
        }
    }

    public static void main(String[] args) {
    }
}
