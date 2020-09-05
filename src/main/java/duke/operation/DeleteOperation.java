package duke.operation;

import duke.list.DukeList;
import duke.result.Result;
import duke.storage.Storable;

/**
 * The operation that deletes a <code>Task</code> from the <code>TaskList</code>.
 */
public class DeleteOperation extends Operation {
    private final DukeList dukeList;
    private final int index;

    /**
     * Constructor method.
     *
     * @param dukeList the <code>DukeList</code> containing the
     *                 <code>Storable</code> that is to be removed.
     * @param index the associated index of the <code>Storable</code>.
     */
    public DeleteOperation(DukeList dukeList, int index) {
        this.dukeList = dukeList;
        this.index = index;
    }

    /**
     * Removes the given <code>Storable</code> from the <code>DukeList</code>.
     *
     * @return a <code>Result</code> indicating the task that has been removed.
     */
    @Override
    public Result execute() {
        if (!this.dukeList.isValidIndex(index)) {
            String message = "The index you have passed in cannot be found in the list of tasks.";
            return new Result(false, message, this.isExit());
        }
        Storable removed = this.dukeList.remove(this.index);
        String message = "Noted. I've removed:\n"
                + removed + "\n"
                + String.format("You now have %d tasks in the list", this.dukeList.getCurrCapacity());
        return new Result(true, message, this.isExit());
    }
}
