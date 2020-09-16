package botbot;

import botbot.commands.Command;
import botbot.tasks.TaskList;
import botbot.ui.Ui;
import botbot.utils.Parser;
import botbot.utils.Storage;

/**
 * Represents the chatbot.
 */
public class Botbot {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Creates a chatbot.
     *
     * @param filePath Filepath where data of the chat is stored.
     */
    public Botbot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Returns the response after the execution of the user's command.
     *
     * @param input User input.
     * @return Response of execution.
     */
    public String getResponse(String input) {
        Command c = Parser.parseCommand(input);
        return c.execute(storage, tasks, ui);
    }
}
