package duke;

import duke.command.Command;
import duke.exception.DukeException;


/**
 * Encapsulates the main Duke class
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage("duke.txt");
        try {
            tasks = storage.loadData();
        } catch (DukeException e) {
            ui.printError(e);
            tasks = new TaskList();
        }
    }



    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String output;
        try{
            Command c = Parser.parse(input);
            output = c.execute(tasks,ui,storage);

        } catch (DukeException e) {
            output = e.toString();
        }

        return output;
    }




}
