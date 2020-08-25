package duke;

import duke.command.Command;

public class Duke {


    private Ui ui;
    private Storage storage;
    private TaskList taskList;


    /**
     * Creates a Duke object containing a Ui object, Storage object, and TaskList Object.
     * @param filepath filepath where the text file containing the list is stored.
     */
    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        taskList = new TaskList(storage.readFile());
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
                c.execute(taskList, ui, storage);
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
        Duke duke = new Duke("/data/duke.txt");
        duke.run();
    }




}
