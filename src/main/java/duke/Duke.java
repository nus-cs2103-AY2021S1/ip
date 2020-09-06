package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Duke can take in command and manage tasks.
 */
public class Duke {
    /**
     * The main method in Duke class.
     * Create an Ui object to print the lists of tasks.
     * @param args Unused.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {

        TaskList taskList = Storage.read();
        Parser.setTaskList(taskList);
//        System.out.println(Parser.taskList);
        Ui ui = new Ui();
        ui.run();
    }
}
