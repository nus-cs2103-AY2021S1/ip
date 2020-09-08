import java.io.IOException;
import java.util.ArrayList;

import duke.command.DukeException;
import duke.command.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * <h1> DUKE CLASS </h1>
 * The Duke Class contains the method to
 * initialize the DukeChat bot. Different commands will
 * result in different course of action.
 *
 * Currently Duke only supports Todo, Deadline, Event, Done, Delete, List, Find
 * commands as a Task Tracker.
 *
 * @author Lee Penn Han
 * @version 1.0
 * @since 2020-08-25
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;

    /**
     * Instantiate a Duke Object with the filepath as that name
     * of the file which the lists will be saved to.
     */
    public Duke() {
        this.taskList = TaskList.createTaskList();
        this.storage = Storage.createDukeFile("Saved", taskList);
        try {
            ArrayList<String> taskHistory = this.storage.loadFile();
            Parser.processOldTasks(taskHistory, this.taskList);
        } catch (IOException e) {
            System.out.println(Ui.showError(e.getMessage()));
        }
    }

    /**
     * Generates a formatted response according to the User's input
     * on a brand new task
     * @return String
     */
    public String getResponse(String input) {
        boolean inputNewTask = false;
        StringBuilder stringBuilder = new StringBuilder();
        if (!input.equals("bye") && input.length() != 0) {
            try {
                stringBuilder.append(Parser.process(input, this.taskList, inputNewTask));
            } catch (DukeException e) {
                stringBuilder.append(Ui.showError(e.getMessage()));
            }
        } else {
            try {
                stringBuilder.append(Ui.showSaving(this.storage.saveToFile()));
            } catch (IOException e) {
                stringBuilder.append(Ui.showError(e.getMessage()));
            }
            stringBuilder.append(Ui.goodbyeMessage());
        }
        return stringBuilder.toString();
    }
}
