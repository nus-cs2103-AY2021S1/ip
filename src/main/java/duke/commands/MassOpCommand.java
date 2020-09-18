package duke.commands;

import duke.DukeException;
import duke.tasks.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.util.List;

/**
 * Command sub-type to define performing mass
 * operations (operations on multiple tasks at once).
 */
public class MassOpCommand extends Command {

    public static final String DONE_OP = "marked done";
    public static final String DELETE_OP = "deleted";

    /**
     * Create MassOpCommand object.
     *
     * @param attributes input attributes from user
     */
    public MassOpCommand(String attributes) {
        this.attributes = attributes;
    }

    /**
     * CLI (terminal) version of the command.
     *
     * @param taskList TaskList object handling the current list
     * @param storage Storage object to read/write from disk
     * @param ui Ui object to handle user interface interactions
     * @return true, continue program loop
     * @throws DukeException Duke-related exception while performing the operation on the tasks found
     */
    @Override
    public boolean runCLI(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        runGUI(taskList, storage, ui);
        return true;
    }

    /**
     * Find all tasks that match search key and perform given operation on them.
     *
     * @param taskList TaskList object handling the current list
     * @param storage Storage object to read/write from disk
     * @param ui Ui object to handle user interface interactions
     * @return Duke output to GUI
     * @throws DukeException Duke-related exception while performing the operation on the tasks found
     */
    @Override
    public String runGUI(TaskList taskList, Storage storage, Ui ui) throws DukeException{
        String[] attrSplit = attributes.split("/find");
        if (attrSplit.length != 2) {
            throw new DukeException("Invalid mass operation format");
        }
        attrSplit[0] = attrSplit[0].strip();
        attrSplit[1] = attrSplit[1].strip();

        String op;
        if (attrSplit[0].equals("done")) {
            op = DONE_OP;
        } else if (attrSplit[0].equals("delete")) {
            op = DELETE_OP;
        } else {
            System.out.println(attrSplit[0]);
            throw new DukeException("Invalid operation specified");
        }

        List<Task> foundTasks = taskList.findTasks(attrSplit[1]);
        if (op.equals(DONE_OP)) {
            taskList.massMarkDone(foundTasks);
        } else {
            taskList.massDelete(foundTasks);
        }
        return ui.writeMassOp(foundTasks, op);
    }
}
