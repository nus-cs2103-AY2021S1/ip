package duke;

import java.io.IOException;

import duke.command.Command;
import duke.command.CommandParser;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Message;

/**
 * Represents a chatbot that takes in and executes commands from the user.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;

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
        } catch (IOException e) {
            System.out.println("Problem reading file.");
        }
    }

    public Duke() {
        this("/data/taskList.txt");
    }

    /**
     * Carries out what the user tells it to do
     *
     * @param input the user's input
     * @return the chatbot's response to the input as a <code>Message</code>
     * or null if the program is exited
     */
    public String getResponse(String input) {
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
        return response.toString();
    }
}
