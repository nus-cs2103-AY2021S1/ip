package duke;

import duke.command.Command;
import duke.command.ExitCommand;

import java.util.Scanner;

/**
 * Represents a bot that helps to manage task.
 * Each <code>Duke</code> object has its own respective database.
 */
public class Duke {
    private final static Ui ui = new Ui();
    private final Storage storage;

    Duke(Storage storage) {
        this.storage = storage;
    }

    /**
     * Returns a <code>Duke</code> object with the corresponding user database.
     * If database fails to create or load, null is returned.
     *
     * @param filePath FilePath of database
     * @return <code>Duke</code> object
     */
    public static Duke createDuke(String filePath) {
        try {
            Storage storage = Storage.createStorage(filePath);
            storage.load();
            return new Duke(storage);
        } catch (DukeException e) {
            ui.fileCreationError(e.getMessage());
            return null;
        }
    }

    /**
     * Runs the whole program by taking in input commands.
     * Terminates only when an <Code>ExitCommand</Code> is given.
     */
    public void run() {
        Scanner input = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit && input.hasNextLine()) {
            try {
                String commandMessage = input.nextLine();
                Command c = Parser.parse(commandMessage);
                c.execute(commandMessage, storage, ui);
                if (c instanceof ExitCommand) {
                    isExit = true;
                    input.close();
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
