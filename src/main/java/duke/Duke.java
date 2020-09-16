package duke;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import command.Command;
import controller.MainWindow;
import exception.DukeException;
import storage.CommandStorage;
import storage.TaskStorage;
import task.Task;

/**
 * A Duke object represents the chat bot which responds to users' inputs.
 *
 * @author ameliatjy
 * @version 2.0
 * @since 2020-09-01
 */
public class Duke {

    private final TaskStorage taskStorage;
    private final CommandStorage commandStorage;
    private TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
     * Public constructor of Duke to create a Duke object to deal with user inputs.
     *
     * @param filePath Path where user commands and tasks can be loaded from and saved to.
     */
    public Duke(String filePath) {
        ui = new Ui();
        taskStorage = new TaskStorage(filePath + "duke.txt");
        commandStorage = new CommandStorage(filePath + "commands.txt", null);
        try {
            ArrayList<Task> currList = taskStorage.load();
            tasks = new TaskList(currList);
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
            taskStorage.writeToFile(tasks);
            commandStorage.reset();
            MainWindow.endDuke();
            return ui.endDuke();
        }

        String output;
        try {
            Command command = parser.processMsg(input);
            output = command.execute(input, tasks, ui, commandStorage);
        } catch (DukeException | ParseException e) {
            return e.getMessage();
        }
        commandStorage.writeToFile(input);
        return output;
    }
}
