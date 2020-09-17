package juke;

import juke.command.Command;

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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        return this.run(input);
    }

}

