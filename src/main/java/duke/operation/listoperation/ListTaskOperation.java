package duke.operation.listoperation;

import duke.list.TaskList;

/**
 * The operation that lists all the <code>Tasks</code> in <code>TaskList</code>.
 */
public class ListTaskOperation extends ListOperation {

    /**
     * Constructor method.
     *
     * @param taskList the <code>TaskList</code> that is to be printed.
     */
    public ListTaskOperation(TaskList taskList) {
        super(taskList);
    }

    @Override
    protected String getMessage() {
        return "Here are your tasks:\n" + this.list.toString();
    }
}
