/**
 * Encapsulates the Duke object with a Storage object, 
 * TaskList object, and Ui object. 
 * Supports the method getResponse which takes in an
 * user input and returns a response.
 */
public class Duke {
    /**
     * Represents the storage object which loads and stores data.
     */
    private Storage storage;

    /**
     * Represents the taskList object which maintains the task list.
     */
    private TaskList tasks;

    /**
     * Represents the ui object which displays the response to the user.
     */
    private Ui ui;
    
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    
    public void run() {
//        ui.showWelcome();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand();
//                ui.showLine(); // show the divider line ("_______")
//                Command c = Parser.parse(fullCommand);
//                c.execute(tasks, ui, storage);
//                isExit = c.isExit();
//            } catch (DukeException e) {
//                ui.showError(e.getMessage());
//            } finally {
//                ui.showLine();
//            }
//        }
    }
    
    public static void main(String[] args) {
        new Duke("taskList.txt").run();
    }
}
