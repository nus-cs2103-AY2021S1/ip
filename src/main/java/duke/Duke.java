package duke;

import duke.command.Command;
import duke.command.CommandParser;

import duke.storage.Storage;

import duke.util.TaskList;

/**
 * Duke is a Personal Assistant Chatbot that helps a user keep track of various things.
 */
public class Duke {

    protected TaskList taskList;
    protected Storage storage;
    protected Ui ui;

    public Duke() {
        storage = Storage.init();
        taskList = storage.readStoredData();
        ui = new Ui();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();

    }

    protected void run() {
        ui.startup();

        boolean isExit = false;
        while (!isExit) {
            try {
                Command command = CommandParser.parse(ui.getCommand());
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.outputMessage(e.getMessage());
            }
        }

    }


}
