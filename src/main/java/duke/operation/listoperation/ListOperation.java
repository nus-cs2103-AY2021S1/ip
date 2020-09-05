package duke.operation.listoperation;

import duke.list.DukeList;
import duke.operation.Operation;
import duke.result.Result;

public abstract class ListOperation extends Operation {
    protected final DukeList list;

    public ListOperation(DukeList list) {
        this.list = list;
    }

    protected abstract String getMessage();

    /**
     * Executes the operation by retrieving all <code>Tasks</code> in <code>TaskList</code>.
     *
     * @return a <code>Result</code> containing all <code>Tasks</code> stored in <code>TaskList</code>.
     */
    @Override
    public Result execute() {
        return new Result(true, getMessage(), this.isExit());
    }
}
