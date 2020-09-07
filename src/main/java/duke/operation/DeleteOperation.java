package duke.operation;

import duke.list.StorableList;
import duke.result.Result;
import duke.storage.Storable;

/**
 * The operation that deletes a <code>Task</code> from the <code>TaskList</code>.
 */
public class DeleteOperation extends Operation {
    private final StorableList storableList;
    private final int index;

    /**
     * Constructor method.
     *
     * @param storableList the <code>StorableList</code> containing the
     *                 <code>Storable</code> that is to be removed.
     * @param index the associated index of the <code>Storable</code>.
     */
    public DeleteOperation(StorableList storableList, int index) {
        this.storableList = storableList;
        this.index = index;
    }

    /**
     * Removes the given <code>Storable</code> from the <code>StorableList</code>.
     *
     * @return a <code>Result</code> indicating the task that has been removed.
     */
    @Override
    public Result execute() {
        if (!storableList.isValidIndex(index)) {
            String message = "The index you have passed in cannot be found in the list of tasks.";
            return new Result(false, message, this.isExit());
        }
        Storable removed = storableList.remove(this.index);
        String message = "Noted. I've removed:\n"
                + removed + "\n"
                + String.format("You now have %d tasks in the list", storableList.getCurrCapacity());
        return new Result(true, message, this.isExit());
    }
}
