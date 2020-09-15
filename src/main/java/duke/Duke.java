package duke;

import duke.task.TaskList;
import duke.utility.Parser;
import duke.utility.Statistic;
import duke.utility.Storage;
import duke.utility.Ui;

public class Duke {

    /** duke.task.TaskList class that stores and deals with the tasks **/
    private TaskList taskList;
    /** duke.utility.Parser class that parse and deal with the commands given **/
    private Parser parser;
    /** duke.utility.Storage class that handles loads and saves the duke.task from/to hard drive **/
    private Storage storage;
    /** UI class that is responsible for the interaction with the user **/
    private Ui ui;
    private Statistic statistic;

    /**
     *Class constructor
     */
    public Duke() {
        statistic = new Statistic();
        taskList = new TaskList(statistic);
        parser = new Parser(taskList);
        ui = new Ui();

        try {
            storage = new Storage(taskList, statistic);
        } catch (DukeException e) {
            ui.showLoadingError();
        }
    }

    /**
     * Initiate the stop to the program
     * Save the stored tasks into the hard drive
     */
    public void stop() {
        try {
            storage.saveTaskFile();
        } catch (DukeException e) {
            ui.showSavingError();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return parser.parseCommand(splitCommand(input));
    }

    public String[] splitCommand(String input){
        return input.trim().split(" ", 2);
    }

}