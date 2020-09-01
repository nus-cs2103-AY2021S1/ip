package duke;

import duke.command.Command;
import duke.task.TaskList;

/**
 * Main class to run Duke program.
 * Creates and stores your task list.
 */
public class Duke {

    private Storage saveData;
    private TaskList list;
    private Ui ui;

    /**
     * Duke Class constructor. Create a new Ui and TaskList. Load saved data if Storage exist, else create new Storage.
     *
     * @param filePath give the path of the save data
     */
    public Duke(String filePath) {
        ui = new Ui();
        this.saveData = new Storage(filePath);
        list = new TaskList(saveData.loadSavedData());
    }

    /**
     * Method to run Duke.
     */
    public void run() {
        ui.sayHi();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(list, ui, saveData);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.saySomthing(e.getMessage());
            }

        }
    }

    /**
     * main method to run Duke class.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
