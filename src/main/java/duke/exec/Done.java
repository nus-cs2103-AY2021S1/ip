package duke.exec;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class Done implements Executable {

    // constants
    private static final String TOGGLE_MESSAGE = "Nice! "
            + "I've marked this task as done:";

    // instance variables
    private int index; // zero-based

    // constructor
    public Done(int index) {
        this.index = index;
    }

    /**
     * Marks the task at a specified index (0-based) as done/finished.
     *
     * @param tasks the list of tasks
     * @param ui the ui object handling displays
     * @param store the storage object handling i/o
     */
    @Override
    public void run(TaskList tasks, Ui ui, Storage store) throws DukeException {
        Task toToggle = tasks.get(index);

        // if it is done, there is no need to set as done
        if (toToggle.isDone()) {
            throw DukeException.alreadyDone(toToggle);
        }

        toToggle.setTaskAsDone();
        ui.display(TOGGLE_MESSAGE, "\t" + toToggle.toString());
        store.write(tasks);
    }
}
