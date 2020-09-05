package duke.operation;

import duke.list.DukeList;
import duke.result.Result;

/**
 * The operation that finds <code>Tasks</code> in <code>TaskList</code> associated with a search word.
 */
public class FindOperation extends Operation {
    private final DukeList dukeList;
    private final String searchWord;

    /**
     * Constructor method.
     *
     * @param dukeList the <code>DukeList</code> that is to be searched on.
     * @param searchWord the <code>String</code> that <code>DukeList</code> is to be search with.
     */
    public FindOperation(DukeList dukeList, String searchWord) {
        this.dukeList = dukeList;
        this.searchWord = searchWord;
    }

    /**
     * Executes the operation by retrieving all <code>Storables</code> in
     * <code>DukeList</code> containing the search word.
     *
     * @return a <code>Result</code> containing all <code>Storables</code> found.
     */
    @Override
    public Result execute() {
        String found = this.dukeList.search(searchWord);
        String message;
        if (found.equals("")) {
            message = String.format("I have found no tasks that match: %s", this.searchWord);
        } else {
            message = "Here is what I have found:\n" + found;
        }
        return new Result(true, message, this.isExit());
    }
}
