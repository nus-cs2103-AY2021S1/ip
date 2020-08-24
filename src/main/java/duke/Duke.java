package duke;

import duke.command.Command;
import duke.command.CommandTypes;
import exception.*;

import java.time.DateTimeException;

import java.util.Scanner;

public class Duke {
    Scanner sc;
    String input;
    TaskList taskList;
    Storage storage;
    Parser parser;
    Ui ui;

    public Duke() {
        sc = new Scanner(System.in);
        ui = new Ui();
        parser = new Parser();
        storage = new Storage();
    }

    public void start() {
        try {
            this.taskList = new TaskList(storage.load());
            ui.showWelcomeMessage();
            handleInteraction();
            ui.showCloseMessage();
        } catch (StorageException ex) {
            ex.printStackTrace();
        }
    }


    // interaction functions
    public void handleInteraction() {
        while (sc.hasNextLine()) {
            input = sc.nextLine();
            // stopping condition
            if (input.equals("bye")) {
                break;
            }

            ui.buildChatFence();

            // handle commands
            try {
                Command command = parser.parseCommand(input);
                command.execute(taskList, ui, storage);
            } catch (InvalidUsageException | UnknownCommandException ex) {
                System.out.println(ex.getMessage());
            } finally {
                ui.buildChatFence();
            }
        }
    }
}
