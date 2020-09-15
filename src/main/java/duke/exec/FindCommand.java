package duke.exec;

import java.util.List;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand implements Executable {

    // constants
    private static final String FIND_MESSAGE = "Here are the task(s) that match "
            + "the keyword given";

    // instance variables
    private String keyword;

    // constructor
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * When run, finds tasks that contain the desired
     * keyword
     *
     * @param tasks the list of tasks to append to
     * @param ui the ui object handling displays
     * @param store the storage object handling i/o
     * @return output after running this command
     */
    @Override
    public String run(TaskList tasks, Ui ui, Storage store) {
        if (tasks.isEmpty()) {
            return ui.outputString("The list is empty");
        } else {
            List<String> enumeration = tasks.filter(keyword).enumerate();
            enumeration.add(0, FIND_MESSAGE);
            return ui.outputString(enumeration);
        }
    }
}
