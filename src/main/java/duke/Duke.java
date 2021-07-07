package duke;

import duke.command.Command;
import duke.command.InvalidCommandException;
import duke.component.ActualStorage;
import duke.component.CliUi;
import duke.component.FxmlUi;
import duke.component.Parser;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

/**
 * Is the Main class of this program.
 */
public class Duke {
    private final Storage storage;
    private final TaskList list;
    private final Ui ui;

    /**
     * Creates a running Duke, initialize the list with data in the input file, if input file is not found,
     * initialize the list with an empty list.
     * @param filePath The file path of the data file holding all existing tasks.
     * @param isCliApp true if the app is CLI app, and false if it uses javafx.
     */
    public Duke(String filePath, boolean isCliApp) {
        if (isCliApp) {
            ui = new CliUi();
        } else {
            ui = new FxmlUi();
        }
        storage = new ActualStorage(filePath);
        list = storage.getList();
    }

    /**
     * Runs the Duke program.
     */
    public void run() {
        boolean flag = true;
        ui.greeting();
        while (flag) {
            try {
                String input = ui.readInput();
                Command c = Parser.parse(input);
                c.execute(ui, list, storage);
                flag = !c.isExit();
            } catch (InvalidCommandException e) {
                ui.output(e.getMessage());
            }
        }
        ui.close();
    }

    /**
     * Gets the Ui of the running Duke.
     * @return the ui of this object
     */
    public Ui getUi() {
        return ui;
    }

    /**
     * Gets the list of tasks in the running Duke.
     * @return the task list of this object
     */
    public TaskList getList() {
        return list;
    }

    /**
     * Gets the storage handling object of this running Duke.
     * @return the storage handling object of this object
     */
    public Storage getStorage() {
        return storage;
    }

    /**
     * The running main method of the application that uses data/tasks.txt as the file for storage."
     * @param args nothing input
     */
    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "Desktop", "cs2103", "ip", "data");
        new Duke(path.toString(), true).run();
    }
}
