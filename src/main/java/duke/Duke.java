package duke;
import duke.command.Command;

public class Duke {

    public static final String FILE_PATH = "data/duke.txt";
    private Storage storage;
    private TaskList taskItems;
    private Ui ui;


    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskItems = new TaskList(storage.loadTasksFromMemory());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            taskItems = new TaskList();
        }
    }    
    
    public void run() {
        ui.greeting();
        boolean isExit = false;
        while(!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskItems, ui, storage);
                isExit = c.isExit();
            } catch (DukeException duked) {
                ui.showError(duked.getMessage());
            } finally {
            }
        }
    }
    /**
     * main driver function
     * @param args
     */
    public static void main(String[] args) {
        new Duke(FILE_PATH).run(); 
    }
}
