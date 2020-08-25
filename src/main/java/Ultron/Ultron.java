package ultron;

import ultron.exceptions.UltronException;
import ultron.commands.Command;

import java.util.ArrayList;

public class Ultron {

    /**
     * Task list to store the tasks.
     */
    private TaskList taskList;

    /**
     * Store the UI class.
     */
    private final UI ui;

    /**
     * Store the Storage.
     */
    private final Storage storage;

    /**
     * The Ultron class.
     * @param path  path to the datafile which stores the tasks
     */
    public Ultron(String path){

        //Create the Storage object
        storage = new Storage(path);

        try {

            //Fetch all from storage
            this.taskList = new TaskList(storage.load());

        }catch (UltronException e){

            //Initialise the blank arraylist
            this.taskList = new TaskList(new ArrayList<>());

        }
      
        //Create new instance of UI
        ui = new UI();

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

    /**
     * Entry point of the code.
     * @param args Command line arguments provided
     */
    public static void main(final String[] args) {

        //Create a new duke
        Ultron ultron = new Ultron("data/data.txt");

        //Run the main loop
        ultron.mainLoop();

    }
}
