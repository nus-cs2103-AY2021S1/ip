package botbot;

import botbot.commands.Command;
import botbot.exceptions.BotbotException;

/**
 * Represents the chatbot.
 */
public class Botbot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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

    public String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            return c.execute(storage, tasks, ui);
        } catch (BotbotException e) {
            return ui.printStatus(e.getMessage());
        }
    }
}
