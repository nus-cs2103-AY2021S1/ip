package duke;

import duke.command.Command;
import duke.response.Response;

/**
 * The main program
 */
public class Duke {
    private final Storage storage;
    private final TaskList taskList;
    private final Response initResponse;

    /**
     * The main program's constructor.
     *
     * @param filePath The path to the data file.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        TaskList taskList;
        Response initResponse;

        try {
            taskList = new TaskList(this.storage.load());
            initResponse = new Response();
        } catch (DukeException e) {
            taskList = new TaskList();
            initResponse = new Response(e.getMessage(), true, false);
        }

        this.taskList = taskList;
        this.initResponse = initResponse;
    }

    public String getWelcome() {
        return "Hello! I'm Duke\n"
                + "What can I do for you?\n";
    }

    public Response getInitResponse() {
        return this.initResponse;
    }

    public Response getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(this.taskList, this.storage);
        } catch (DukeException e) {
            return new Response(e.getMessage(), true, false);
        }
    }
}
