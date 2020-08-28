package duke;

import duke.command.Command;
import duke.command.InvalidCommandException;
import duke.component.ActualStorage;
import duke.component.Parser;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.CliUi;
import duke.component.Ui;

/**
 * Is the Main class of this program.
 */
public class Duke {
    private Storage storage;
    private TaskList list;
    private CliUi ui;

    /**
     * Creates a running Duke, initialize the list with data in the input file, if input file is not found,
     * initialize the list with an empty list.
     * @param filePath The file path of the data file holding all existing tasks.
     */
    public Duke(String filePath, boolean isCliApp) {
        if (isCliApp) {
            ui = new CliUi();
        }
        try {
            storage = new ActualStorage(filePath);
            list = storage.getList();
        } catch (Exception e) {
            ui.output(e.getMessage());
            list = new TaskList();
        }
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
