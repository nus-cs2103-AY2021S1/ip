package duke.exec;

import java.util.List;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Find implements Executable {

    // constants
    private final String FIND_MESSAGE = "Here are the tasks that match "
            + "the keyword given";

    // instance variables
    private String keyword;

    // constructor
    public Find(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Returns
     *
     * @param tasks the list of tasks to append to
     * @param ui the ui object handling displays
     * @param store the storage object handling i/o
     */
    @Override
    public void run(TaskList tasks, Ui ui, Storage store) {
        if (tasks.isEmpty()) {
            ui.display("The list is empty");
        } else {
            List<String> enumeration = tasks.filter(keyword).enumerate();
            enumeration.add(0, FIND_MESSAGE);
            ui.display(enumeration);
        }
    }
}
