package duke;

import java.io.IOException;

import duke.command.Command;
import duke.command.CommandParser;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Message;
import duke.ui.Ui;

/**
 * Represents a chatbot that takes in and executes commands from the user.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Class constructor.
     *
     * @param filepath directory and name of the file to save the user's tasks to
     */
    Duke(String filepath) {
        try {
            this.storage = new Storage(filepath);
            this.storage.initialize();
            this.taskList = this.storage.readTasks();
            this.ui = new Ui();
        } catch (IOException e) {
            System.out.println("Problem reading file.");
        }
    }

    public Duke() {
        this("/data/taskList.txt");
    }

    /**
     * Starts the chatbot so that it takes in commands from the user.
     */
    public void run() {
        this.ui.showMessage(Message.getWelcome());
        boolean isExit = false;
        while (!isExit) {
            Message message = runCommand(this.ui.readCommand());
            if (message == null) {
                isExit = true;
            } else {
                this.ui.showMessage(message);
            }
        }
    }

    /**
     * Carries out what the user tells it to do
     *
     * @param input the user's input
     * @return the chatbot's response to the input as a <code>String</code>
     * or null if the program is exited
     */
    public String getResponse(String input) {
        Message message = runCommand(input);
        if (message == null) {
            return null;
        }
        return message.toString();
    }

    /**
     * Carries out what the user tells it to do
     *
     * @param input the user's input
     * @return the chatbot's response to the input as a <code>Message</code>
     * or null if the program is exited
     */
    public Message runCommand(String input) {
        Message response;
        try {
            Command command = CommandParser.parse(input);
            assert command != null;
            Message message = command.execute(this.taskList, this.storage);
            if (command.isDone()) {
                return null;
            }
            response = message;
        } catch (IOException | DukeException e) {
            response = new Message(e.getMessage());
        }
        return response;
    }
}
