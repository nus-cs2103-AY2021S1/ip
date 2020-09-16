package duke;

import duke.parser.Parser;
import duke.ui.Ui;
import duke.tasks.TaskList;
import duke.storage.Storage;

/**
 * Duke can take in command and manage tasks.
 */
public class Duke {

    public Ui ui;

    public Duke(){
        ui = new Ui();
        TaskList taskList = Storage.read();
        Parser.setTaskList(taskList);
    }

    /**
     * The main method in Duke class.
     * Create an Ui object to print the lists of tasks.
     * @param args Unused.
     */
    public static void main(String[] args) {

        TaskList taskList = Storage.read();
//        System.out.println(taskList.toString());
        Parser.setTaskList(taskList);
        Ui ui = new Ui();
        ui.run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            return Parser.processCommand(input);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
