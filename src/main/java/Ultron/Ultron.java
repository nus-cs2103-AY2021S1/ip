package ultron;

import java.util.ArrayList;

import javafx.application.Platform;
import ultron.commands.Command;
import ultron.exceptions.UltronException;
import ultron.ui.UI;

public final class Ultron {
    /** Store the UI class. */
    private final UI ui;
    /** Store the Storage. */
    private final Storage storage;
    /** Task list to store the tasks. */
    private TaskList taskList;

    /**
     * The Ultron class.
     */
    public Ultron() {

        //Store the path to the data
        String path = "data/data.txt";

        //Create a new storage
        storage = new Storage(path);

        try {

            //Fetch all from storage
            this.taskList = new TaskList(storage.load());

        } catch (UltronException e) {

            //Initialise the blank arraylist
            this.taskList = new TaskList(new ArrayList<>());

        }
        ui = new UI();
    }

    /**
     * GetResponse of Ultron based on input.
     *
     * @param input input from the user.
     * @return String message by Ultron for the user.
     */
    public String getResponse(String input) {

        //Check if it is the command to show the intro message
        if (input == "showIntro") {

            ui.setIntro();
            return ui.getMessage();

        } else {

            //Adapted from CS2103T website.
            try {
                Command c = Parser.parseCommand(input);

                c.execute(taskList, ui, storage);

                if (c.isExit()) {
                    Platform.exit();
                }

            } catch (UltronException e) {
                ui.setMessage(e.getMessage());
            }
        }

        return ui.getMessage();
    }

}
