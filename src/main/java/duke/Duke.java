package duke;

import duke.command.Command;

public class Duke {


    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private TaskList archiveList;



    /**
     * Creates a Duke object containing a Ui object, Storage object, and TaskList Object.
     * @param filepath filepath where the text file containing the list is stored.
     */
    public Duke(String filepath, String archivePath) {
        ui = new Ui();
        storage = new Storage(filepath, archivePath);
        taskList = new TaskList(storage.readMain());
        archiveList = new TaskList(storage.readArchive());
    }


    /**
     * Runs the main functions of the Duke object.
     */
    public void run() {
        ui.printGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readInput();
                Command c = new Parser().parse(userInput);
                c.execute(taskList, archiveList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printString(e.getMessage());
            }
        }
    }

    /**
     * Runs the main method which makes use of the run method.
     * @param args
     */
    public static void main(String[] args) {
        Duke duke = new Duke("/data/duke.txt", "/data/archive.txt");
        duke.run();
    }


    public String getResponse(String input) {
        try {
            Command c = new Parser().parse(input);
            String output = c.getExecuteString(taskList, archiveList, ui, storage);
            return output;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public String getGreeting() {
        return ui.getGreeting();
    }
}
