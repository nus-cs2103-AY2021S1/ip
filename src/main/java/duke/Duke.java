package duke;

import duke.command.Command;
import duke.command.CommandParser;

import duke.storage.Storage;

import duke.util.TaskList;

/**
 * Duke is a Personal Assistant Chatbot that helps a user keep track of various things.
 */
public class Duke {

    /** list of tasks to keep track of */
    private TaskList taskList;
    /** storage object handling saving and editing of the save file in the hard drive */
    private Storage storage;
    /** ui object in charge of direct interaction with the user */
    private Ui ui;

    /**
     * Standard constructor initialising the Duke bot.
     * It will attempt to search for a pre-saved save file of a previous task list.
     * If found, it loads up that task list.
     * Otherwise it creates its own which will be saved and able to be opened by future Duke bots.
     */
    public Duke() {
        storage = Storage.init();
        taskList = storage.readStoredData();
        ui = new Ui();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Runs the duke bot.
     * It continuously queries the ui for the next user response and responds appropriately.
     */
    public void run() {
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
