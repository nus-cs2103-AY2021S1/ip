package seedu.duke;

import javafx.application.Platform;
import seedu.duke.command.Command;

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
        } catch (DukeException e) {
            System.out.println(e.getMessage());
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
                    this.ui.showMessage(message);
                    isExit = command.isDone();
                }
            } catch (DukeException e) {
                this.ui.showMessage(new Message(e.getMessage()));
            }
        }
    }

    String getResponse(String input) {
        String response = "";
        try {
            Command command = Parser.parse(input);
            if (command != null) {
                Message message = command.execute(this.taskList, this.storage);
                if (command.isDone()) {
                    Platform.exit();
                }
                response = message.getText();
            }
        } catch (DukeException e) {
            response = e.getMessage();
        }
        return response;
    }
}
