package bob.command;

import bob.exception.BobException;
import bob.Storage;
import bob.TaskList;
import bob.UI;

/**
 * This command when executed creates a TaskList of tasks that contains key words or phrases. The UI is called on
 * to print out this list alongside the appropriate messages.
 */
public class FindCommand extends Command {

    /**
     * The key word or phrases to be searched for in a TaskList.
     */
    private String keyWord;

    /**
     * Constructs a FindCommand by assigning a String to the keyWord parameter.
     *
     * @param keyWord the key word or phrases to be searched for in the TaskList.
     */
    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * Creates a TaskList of tasks that contains key words or phrases, that is then return in message by the UI
     * alongside other appropriate messages.
     *
     * @param tasks the TaskList consisting of all tasks tracked by Bob.
     * @param ui the UI which prints out all messages corresponding to the Command.
     * @param storage the Storage which manages all saved data to be updated.
     * @return the message provided by the UI.
     * @throws BobException
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws BobException {
        assert tasks != null : "A tasklist should be provided";
        assert storage != null : "Storage should be provided";
        assert ui != null : "A UI should be provided";

        return ui.findKeyWord(tasks.contains(keyWord));
    }
}
