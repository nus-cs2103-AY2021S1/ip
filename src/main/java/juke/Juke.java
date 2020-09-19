package juke;

import juke.command.Command;
import juke.ui.Ui;

/**
 * Represents the Juke Chatbot.
 * Allows users to store a list of Tasks, consisting of three types,
 * namely Todos, Events and Deadlines. Users input, change and delete
 * such items via text commands to the bot.
 */
public class Juke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Builds an instance of the Juke Chatbot.
     */
    public Juke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the Chatbot instance.
     * Chatbot will execute commands based on inputText.
     */
    private String run(String inputText) {
        Command command = ui.parseCommand(inputText);
        return command.executeCommand(tasks, storage);
    }

    /**
     * Returns response text based on user input.
     */
    public String getResponse(String input) {
        return this.run(input);
    }

}

