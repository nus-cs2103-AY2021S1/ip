package luoyi.duke.commands;

import luoyi.duke.common.Message;
import luoyi.duke.data.IDuke;
import luoyi.duke.data.exception.DukeIllegalArgumentException;
import luoyi.duke.data.task.TaskList;
import luoyi.duke.ui.Ui;

import java.util.stream.IntStream;

/**
 * FindCommand class to encapsulate find operation.
 * Find takes in a search string and displays all matching descriptions.
 *
 * A Command must be initiated with a Duke object before executing.
 */
public class FindCommand extends Command {
    private final String searchString;

    private FindCommand(String searchString, IDuke duke) {
        super(-1, duke);
        this.searchString = searchString;
    }

    /**
     * Returns a find command.
     *
     * @param searchString Search string.
     * @return A FindCommand which is not yet initiate with Duke.
     */
    public static FindCommand getFindCommand(String searchString) {
        return new FindCommand(searchString, null);
    }

    /**
     * Executes find command.
     *
     * @return Resultant duke object.
     */
    @Override
    public IDuke execute() {
        if (duke == null) {
            throw new RuntimeException(Message.ERR_DUKE_NOT_INIT.toString());
        }
        handleFind(searchString);
        return duke;
    }

    /**
     * Prints the list of matching tasks.
     *
     * @param searchString Search string.
     */
    private void handleFind(String searchString) {
        if (searchString.length() == 0) {
            throw new DukeIllegalArgumentException(
                    "The keyword of find cannot be empty!");
        }
        TaskList list = duke.getTasks();
        int[] indexes = IntStream
                .range(0, list.size())
                .filter(x -> list.get(x).getDescription().contains(searchString))
                .toArray();
        Ui.displayTasks(list, indexes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command setDuke(IDuke duke) {
        return new FindCommand(searchString, duke);
    }
}
