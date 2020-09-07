package duke.operation;

import duke.list.StorableList;
import duke.result.Result;

/** Represents the list <code>Operation</code>. */
public class ListOperation extends Operation {
    protected final StorableList list;

    /**
     * Constructor method.
     *
     * @param list the list to be displayed.
     */
    public ListOperation(StorableList list) {
        this.list = list;
    }

    /**
     * Executes the operation by retrieving all <code>Tasks</code> in <code>TaskList</code>.
     *
     * @return a <code>Result</code> containing all <code>Tasks</code> stored in <code>TaskList</code>.
     */
    @Override
    public Result execute() {
        String msg = "Here is what I have found:\n" + list.toString();
        return new Result(true, msg, this.isExit());
    }
}
