package duke;

import duke.command.Command;
import exception.InvalidUsageException;
import exception.StorageException;
import exception.UnknownCommandException;

public class Duke {
    TaskList taskList;
    Storage storage;
    Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
    }

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
