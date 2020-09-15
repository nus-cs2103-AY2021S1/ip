package duke;

import java.util.Timer;
import java.util.TimerTask;

import duke.command.Command;
import duke.task.TaskList;
import javafx.application.Platform;

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
     */
    public Duke() {
        ui = new Ui();
        this.saveData = new Storage();
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
                assert fullCommand.length() == 0 : "Not valid command";
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
        new Duke().run();
    }

    String getResponse(String input) {
        if (input.equals("bye")) {
            new Timer().schedule(new TimerTask() {
                public void run () {
                    Platform.exit();
                    System.exit(0);
                }
            }, 3000);
        }
        try {
            Command c = Parser.parse(input);
            return c.execute(list, ui, saveData);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
