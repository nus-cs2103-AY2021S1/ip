package duke.operation;

import duke.list.StorableList;
import duke.result.Result;

/** The operation that finds <code>Tasks</code> in <code>TaskList</code> associated with a search word. */
public class FindOperation extends Operation {
    private final StorableList storableList;
    private final String searchWord;

    /**
     * Constructor method.
     *
     * @param storableList the <code>StorableList</code> that is to be searched on.
     * @param searchWord the <code>String</code> that <code>StorableList</code> is to be search with.
     */
    public FindOperation(StorableList storableList, String searchWord) {
        this.storableList = storableList;
        this.searchWord = searchWord;
    }

    /**
     * Executes the operation by retrieving all <code>Storables</code> in
     * <code>StorableList</code> containing the search word.
     *
     * @return a <code>Result</code> containing all <code>Storables</code> found.
     */
    @Override
    public Result execute() {
        String found = storableList.search(searchWord);
        String message;
        if (found.equals("")) {
            message = String.format("I have found no tasks that match: %s", searchWord);
        } else {
            message = "Here is what I have found:\n" + found;
        }
        return new Result(true, message, this.isExit());
    }
}
