package Ultron;

import Ultron.Exceptions.UltronException;
import Ultron.Commands.Command;

import java.util.ArrayList;

public class Ultron {

    //Task list to store the tasks
    private TaskList taskList;

    //Create a UI class
    private final UI ui;

    //Get the storage
    private final Storage storage;

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

    public static void main(final String[] args) {

        //Create a new duke
        Ultron ultron = new Ultron("data/data.txt");

        //Run the main loop
        ultron.mainLoop();

    }
}
