package duke;

import java.io.IOException;

import duke.commands.Command;
import duke.tasks.TaskList;
import javafx.application.Platform;


/**
 * Bot with personality which assists and keeps track of user's tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Constructor to create Duke object.
     *
     * <p>
     * Contains a <code>Ui</code>, a <code>Storage</code>,
     * \a <code>Parser</code> and a <code>TaskList</code>
     * which is responsible for user interaction,
     * saving and writing user's tasks to user's local storage and
     * making sense of user's commands and keeping track of user's tasks respectively.
     */
    public Duke() {
        storage = new Storage();
        parser = new Parser();
        try {
            tasks = new TaskList(storage.readFile());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Responsible for receiving user's input and executing program.
     *
     * <p>
     * Communicates with user via Ui, sends user input to parser
     * to parse and find command of user, executes command and
     * sends result to be saved in storage.
     */
    public String getResponse(String input) {
        assert storage != null;
        assert parser != null;
        assert tasks != null;
        try {
            Command command = parser.findCommand(input);
            if (command.isExit()) {
                Platform.exit();
            }
            String response = command.execute(tasks, storage);
            storage.saveToFile(tasks.getList());
            return response;
        } catch (DukeException | IOException e) {
            return (e.getMessage());
        }
    }

    /**
     * Greets the user.
     * @return greeting message.
     */
    public String getGreeting() {
        /*String logo = " ______  ___       __         __        _____\n"
                + "   |    /         /  \\       /  \\     /\n"
                + "   |    \\___     /____\\     /____\\   |\n"
                + "   |        \\   /      \\   /      \\   \\\n"
                + " ------  ___/  /        \\ /        \\    -----\n";*/
        return "Hello! I'm Duke!" + "\nWhat can I do for you?";
    }
}


