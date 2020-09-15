package duke;

import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;
import javafx.application.Platform;

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
            try {
                Command command = Parser.parse(this.ui.readCommand());
                if (command != null) {
                    Message message = command.execute(this.taskList, this.storage);
                    assert message != null;
                    this.ui.showMessage(message);
                    isExit = command.isDone();
                }
            } catch (IOException | DukeException e) {
                this.ui.showMessage(new Message(e.getMessage()));
            }
        }
    }

    /**
     * Carries out what the user tells it to do
     *
     * @param input the user's input
     * @return the chatbot's response to the input
     */
    String getResponse(String input) {
        String response;
        try {
            Command command = Parser.parse(input);
            assert command != null;
            Message message = command.execute(this.taskList, this.storage);
            if (command.isDone()) {
                Platform.exit();
            }
            response = message.getText();
        } catch (IOException | DukeException e) {
            response = e.getMessage();
        }
        return response;
    }
}
