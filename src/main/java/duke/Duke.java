package duke;

import duke.command.Command;

/**
 * The Duke program is a chat bot which will keep track of
 * tasks from the user input and store the them in a list.
 *
 * @author yuxuan.
 * @version v0.4.
 * @since 2020-08-15.
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for Duke.
     *
     * @param filePath The path of the file which stores the task list.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.setResponse("File can't be loaded.");
            ui.showResponse();
            tasks = new TaskList();
        }
    }

    public String welcomeMessage() {
        return ui.showWelcome();
    }

    public String getResponse(String input) {
        String response;
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            response = ui.showResponse();
        } catch (DukeException e) {
            response = e.getMessage();
        }
        return response;
    }
}
