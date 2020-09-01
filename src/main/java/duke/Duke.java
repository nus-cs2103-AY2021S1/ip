package duke;

import java.io.IOException;
import java.text.ParseException;

import command.Command;
import controller.MainWindow;
import exception.DukeException;

/**
 * A Duke object represents the chat bot which responds to users' inputs.
 *
 * @author amelia
 * @version 2.0
 * @since 2020-09-01
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Public constructor of Duke to create a Duke object to deal with user inputs.
     *
     * @param filePath Path where file containing past and new tasks can be loaded from and saved to.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException | ParseException e) {
            tasks = new TaskList();
        }
        parser = new Parser(tasks);
    }

    /**
     * Gets the response of Duke based on user's input.
     *
     * @param input User's input message to the chat bot.
     * @return String message of Duke's response.
     * @throws IOException If user's input message is invalid.
     */
    public String getResponse(String input) throws IOException {
        if (input.equals("bye")) {
            storage.writeToFile(tasks);
            MainWindow.endDuke();
        }
        Command command = parser.processMsg(input);
        try {
            String response = command.execute(input, tasks, ui);
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
