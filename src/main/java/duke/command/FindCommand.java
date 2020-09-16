package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.storage.Storage;
import duke.util.TaskList;

/**
 * Command that searches Tasks with the given string and sends the appropriate update.
 */
public class FindCommand extends Command {

    /** Default message to be sent when messages are found */
    protected static final String FOUND_MESSAGE = "Here are the matching tasks in your list:";
    /** Default message to be sent when no messages are found */
    protected static final String NONE_FOUND_MESSAGE = "It seems there were no matches!";

    /** Reference string to search for */
    private final String searchString;

    /** Private constructor unable to parse the direct user input. */
    private FindCommand(String searchString) {
        this.searchString = searchString;
    }

    /**
     * Returns an FindCommand made from the appropriate string put.
     * This is a static factory method which handles invalid formats as well.
     *
     * @param command String input of the form "find {search string}".
     * @return command object that finds the tasks containing the search string when executed.
     * @throws DukeException if the command given does not provide a string to search for.
     */
    public static FindCommand parse(String command) throws DukeException {
        String[] details = command.split(" ", 2);
        if (details.length == 1) {
            throw new DukeException("Please specify a keyword/keyphrase to search!");
        }
        return new FindCommand(details[1]);
    }

    /**
     * Finds the tasks whose description contains the search string.
     * The method then broadcasts an update through the UI.
     *
     * @param taskList List of Tasks to work with.
     * @param ui UI element to be used.
     * @param storage Storage element to be used.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList matchingTasks = getMatchingTasks(searchString, taskList);
        ui.outputMessage(createFindMessage(matchingTasks));
    }

    /**
     * Returns a list of tasks all of which have their descriptions containing the search string.
     *
     * @param searchString string to be matched.
     * @param taskList list of tasks to be searched.
     * @return list of tasks which contain the string in their description.
     */
    protected TaskList getMatchingTasks(String searchString, TaskList taskList) {
        TaskList matchingList = new TaskList();
        taskList.stream()
                .filter(task -> task.getTaskDescription().contains(searchString))
                .forEach(task -> matchingList.add(task));
        return matchingList;
    }

    /**
     * Returns a formatted message for finding tasks.
     * Edit this to adjust the message sent when the command is executed.
     *
     * @param taskList list of tasks containing the search string.
     * @return formatted String notifying of the update.
     */
    protected String createFindMessage(TaskList taskList) {
        if (taskList.size() == 0) {
            return NONE_FOUND_MESSAGE;
        } else {
            return FOUND_MESSAGE + '\n' + taskList.toString();
        }
    }




}
