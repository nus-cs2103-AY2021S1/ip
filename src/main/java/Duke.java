import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents the main Duke software.
 */
public class Duke {
    private DukeStorage storage;
    private Ui ui;
    private TaskList taskList;
    private String filePath = "data/duke.txt";

    /**
     * Constructor for the Duke software.
     * Loads the tasklist with the file content.
     */
    public Duke() {
        storage = new DukeStorage(filePath);
        ui = new Ui();
        taskList = new TaskList();
        // try to open the duke file
        try {
            storage.reloadStorage(taskList.getTasks());
        } catch (FileNotFoundException ex) {
            System.out.println("Duke data do not exist!");
        } catch (DukeException ex) {
            ui.format("Its a duke exception!");
        }
    }

    public Ui getUi() {
        return this.ui;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            saveData(taskList, storage);
            return c.execute(taskList, ui, storage);
        } catch (DukeException ex) {
            return ui.throwDukeException(ex);
        }
    }


    /**
     * Main driver code for running main.
     */
    public void run() {
        ui.greet();
        boolean isCompleted = false;
        while (!isCompleted) {
            try {
                String input = ui.getInput();
                Command c = Parser.parse(input);
                c.execute(taskList, ui, storage);
                saveData(taskList, storage);
                isCompleted = c.isCompleted();
            } catch (DukeException ex) {
                ui.throwDukeException(ex);
            }
        }
        ui.getScanner().close();
    }

    /**
     * Helper method to save the data in the file upon every Command.
     * @param taskList tasklist to store tasks.
     * @param storage storage of tasks backend.
     */
    public void saveData(TaskList taskList, DukeStorage storage) {
        try {
            storage.saveStorage(taskList.getTasks());
        } catch (IOException ex) {
            System.out.println("Error in saving!");
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

}
