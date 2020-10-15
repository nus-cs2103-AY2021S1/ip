package duke.operation;

import duke.exception.DukeOperationException;
import duke.list.ListManager;
import duke.result.Result;
import duke.storage.StorageManager;

/** The operation that exits from Duke. */
public class ExitOperation extends Operation {
    private final StorageManager storageManager;
    private final ListManager listManager;

    /**
     * Constructor method.
     *
     * @param storageManager the <code>StorageManager</code> that allows
     *                       all lists in <code>listManager</code>to be saved into a text file.
     * @param listManager the <code>ListManager</code> that manages all lists in Duke.
     */
    public ExitOperation(StorageManager storageManager, ListManager listManager) {
        this.storageManager = storageManager;
        this.listManager = listManager;
    }

    /**
     * Specifies that this is an <code>ExitOperation</code>.
     *
     * @return <code>true</code>.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Saves the <code>Tasks</code> and <code>Expenses</code> in
     * <code>TaskList</code> and <code>ExpenseList</code> into a text file.
     * If the file cannot be saved, Duke will not exit.
     *
     * @return a goodbye message and an indication if
     * the <code>Tasks</code> or <code>Expenses</code> cannot be saved.
     */
    @Override
    public Result execute() {
        try {
            storageManager.saveStores(
                    listManager.getTaskList(), listManager.getExpenseList());
            String message = "Goodbye. Hope to see you again soon.";
            return new Result(true, message, this.isExit());
        } catch (DukeOperationException exception) {
            String message = "The list of tasks and expenses cannot be saved.\n";
            return new Result(false, message + exception.getMessage(), false);
        }
    }
}
