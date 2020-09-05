package duke;

import duke.commands.Command;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.util.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * duke.Duke program is a Task Management App. It takes in user command for task manipulations
 * and shows a list of Tasks that is ongoing.
 */
public class Duke {
    private String filePath;
    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    /**
     * Constructor.
     * @param filePath string path of the file for storage purpose.
     */
    public Duke(String filePath) {
        this.filePath = filePath;
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            taskList = storage.init();
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Run start the program.
     * Initialise the relevant objects to handle user commands.
     */
    public void run() {
        ui.printWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String inputLine = ui.readNextLine();
                Command command = Parser.parse(inputLine);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();

            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /** Initialise empty duke.txt if not found, else initialise the taskList based on existing duke.txt **/
    public static void initFile(String filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);

    }
    /** Updates the file in hard disk based on the new taskList. **/
    public static void updateFile(String filePath, ArrayList<Task> taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (int index = 0; index < taskList.size(); index++) {
            Task task = taskList.get(index);
            fileWriter.write(task.fileString() + "\n");
        }
        fileWriter.close();
    }

    /**
     * Main program to run the application.
     * @param args
     */
    public static void main(String[] args) {
        String filePath = "duke/data/duke.txt";
        Duke duke = new Duke(filePath);
        duke.run();
    }
}
