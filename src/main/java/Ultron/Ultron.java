package ultron;

import java.util.ArrayList;

import ultron.commands.Command;
import ultron.exceptions.UltronException;
import ultron.ui.UI;

public final class Ultron {
    /**
     * Store the UI class.
     */
    private final UI ui;
    /**
     * Store the Storage.
     */
    private final Storage storage;
    /**
     * Task list to store the tasks.
     */
    private TaskList taskList;

    /**
     * The Ultron class.
     */
    public Ultron() {
        String path = "data/data.txt";
        storage = new Storage(path);

        try {

            //Fetch all from storage
            this.taskList = new TaskList(storage.load());

        } catch (UltronException e) {

            //Initialise the blank arraylist
            this.taskList = new TaskList(new ArrayList<>());

        }
        //Create new instance of UI
        ui = new UI();

    }

    /**
     * Main function for the CLI Version of Ultron
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Ultron ultron = new Ultron();
        ultron.mainLoop();
    }

    /**
     * Mainloop to run Ultron.
     */
    public void mainLoop() {

        //Print the intro
        ui.printIntro();

        //Initialise the exit boolean
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getInput();
                ui.showLine();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (UltronException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public String getResponse(String input) {
        try {
            if (input == "showIntro") {
                return ui.getMessage();
            }
            Command c = Parser.parseCommand(input);
            c.execute(taskList, ui, storage);
            return ui.getMessage();
        } catch (UltronException e) {
            return e.getMessage();
        }
    }

}
