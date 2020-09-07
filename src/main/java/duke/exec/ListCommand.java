package duke.exec;

import java.util.List;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand implements Executable {

    // constants
    private static final String EMPTY_MESSAGE = "There are currently "
            + "no items in the list.";
    private static final String LIST_MESSAGE_FRONT = "There are currently ";
    private static final String LIST_MESSAGE_END = " tasks in your list.";

    /**
     * When run, enumerates the items in the list of tasks
     *
     * @param tasks the list of tasks
     * @param ui the ui object handling displays
     * @param store the storage object handling i/o
     * @return the output after running this command
     */
    @Override
    public String run(TaskList tasks, Ui ui, Storage store) {
        if (tasks.isEmpty()) {
            return ui.outputString(EMPTY_MESSAGE);
        } else {
            List<String> enumeration = tasks.enumerate();
            enumeration.add(0, LIST_MESSAGE_FRONT + tasks.size() + LIST_MESSAGE_END);
            return ui.outputString(enumeration);
        }
    }
}
