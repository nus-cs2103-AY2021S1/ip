package duke;

import duke.command.Command;
import duke.command.CommandTypes;
import exception.*;

import java.time.DateTimeException;

import java.util.Scanner;

public class Duke {
    TaskList taskList;
    Storage storage;
    Parser parser;
    Ui ui;

    public Duke() {
        ui = new Ui();
        parser = new Parser();
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
                    Command command = parser.parseCommand(input);
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
