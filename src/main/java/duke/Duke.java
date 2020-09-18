package duke;

import duke.task.TaskList;
import duke.utility.Parser;
import duke.utility.Statistic;
import duke.utility.Storage;
import javafx.application.Application;

public class Duke {

    /** duke.task.TaskList class that stores and deals with the tasks **/
    public TaskList taskList;
    /** duke.utility.Parser class that parse and deal with the commands given **/
    private Parser parser;
    /** duke.utility.Storage class that handles loads and saves the duke.task from/to hard drive **/
    private Storage storage;

    private Statistic statistic;

    /**
     *Class constructor
     */
    public Duke() {
        statistic = new Statistic();
        taskList = new TaskList(statistic);
        parser = new Parser(taskList);

        try {
            storage = new Storage(taskList, statistic);
        } catch (DukeException e) {
            System.out.println("unable to load file");
        }

    }

    /**
     * Initiate the stop to the program
     * Save the stored tasks into the hard drive
     */
    public void save() {
        try {
            storage.saveTaskFile();
        } catch (DukeException e) {
            System.out.println(e.toString());
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