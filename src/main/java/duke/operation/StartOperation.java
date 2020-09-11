package duke.operation;

import duke.list.ListManager;
import duke.result.Result;
import duke.storage.StorageManager;

/** The operation that initialises Duke. */
public class StartOperation extends Operation {
    private final ListManager listManager;
    private final StorageManager storageManager;

    /**
     * Constructor method.
     *
     * @param listManager the collection of <code>DukeLists</code> for the storage file to be loaded onto.
     * @param storageManager the <code>StorageManager</code> that will read in the storage files.
     */
    public StartOperation(ListManager listManager, StorageManager storageManager) {
        this.listManager = listManager;
        this.storageManager = storageManager;
    }

    /**
     * Executes the operation by loading the storage file into the lists of <code>ListManager</code>.
     *
     * @return a <code>Result</code> containing the status of the loading.
     */
    @Override
    public Result execute() {
        String message = storageManager.loadStores(
                listManager.getTaskList(), listManager.getExpenseList())
                + "\nGreetings, what may I do for you?";
        return new Result(true, message, this.isExit());
    }
}
