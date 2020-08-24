package duke;

import duke.command.Command;
import exception.*;

/**
 * Main class for the Duke application
 */
public class Duke {
    // current task list 
    TaskList taskList;
    // storage file
    Storage storage;
    // text ui interface
    Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
    }

    /**
     * Main method to start the application
     */
    public void start() {
        try {
            this.taskList = new TaskList(storage.load());
            ui.showWelcomeMessage();
            boolean isExit = false;
            
            while (!isExit) {
                String input = ui.readCommand();
                ui.buildChatFence();

                // handle commands
                try {
                    Command command = Parser.parseCommand(input);
                    command.execute(taskList, ui, storage);
                    isExit = command.isExit();
                } catch (InvalidUsageException | UnknownCommandException ex) {
                    ui.print(ex.getMessage());
                } finally {
                    ui.buildChatFence();
                }
            }
        } catch (StorageException ex) {
            ex.printStackTrace();
        }
    }
}
