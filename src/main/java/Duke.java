/**
 * Represents a Duke object. Handles all the logic running
 * of Duke
 */

public class Duke {

    private String fileLocation;
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public Duke(String fileLocation) {
        this.fileLocation = fileLocation;
        this.ui = new Ui();
        this.storage = new Storage(fileLocation);
        try {
            this.taskList = new TaskList(this.storage.readFile());
        } catch (InvalidSaveFileException e) {
            ui.showError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Driving method of Duke, handles all the running process
     *
     */
    public void run() {
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
