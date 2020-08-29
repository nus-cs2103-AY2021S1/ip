import java.util.List;
import java.util.Scanner;

import duke.*;
import duke.command.Command;
import duke.command.ExitCommand;
import duke.task.Task;

/**
 * Represents a bot that helps to manage task.
 * Each <code>Duke</code> object has its own respective database.
 */
public class Duke {
    protected static final Ui ui = new Ui();
    protected final Storage storage;
    protected final TaskList taskList;

    Duke(Storage storage, List<Task> taskList) {
        this.storage = storage;
        this.taskList = new TaskList(taskList);
    }

    /**
     * Returns a <code>Duke</code> object with the corresponding user database.
     * If database fails to create or load, null is returned.
     *
     * @param filePath FilePath of database
     * @return <code>Duke</code> object
     */
    public static Duke createDuke(String filePath) throws DukeException {
        try {
            Storage storage = Storage.createStorage(filePath);
            if (storage.isNew()) {
                ui.print(ui.fileCreationSuccess());
            } else {
                ui.print(ui.welcome());
            }
            List<Task> taskList = storage.load();
            return new Duke(storage, taskList);
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
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
                String s = c.execute(commandMessage, storage, ui, taskList);
                ui.print(s);
                if (c instanceof ExitCommand) {
                    isExit = true;
                    input.close();
                }
            } catch (DukeException e) {
                ui.print(e.getMessage());
            }
        }
    }

    /**
     * Creates a <code>Duke</code> object.
     *
     * @param args array for command-line arguments
     */
    public static void main(String[] args) {
        try {
            Duke duke = createDuke("data/duke.txt");
            duke.run();
        } catch (DukeException e) {
            ui.print(e.getMessage());
        }

    }

}
