package main.java;

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
    FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * Creates a TaskList of tasks that contains key words or phrases, that is then printed out by the UI
     * alongside appropriate messages.
     *
     * @param tasks the TaskList consisting of all tasks tracked by Bob.
     * @param ui the UI which prints out all messages corresponding to the Command.
     * @param storage the Storage which manages all saved data to be updated.
     * @throws BobException
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws BobException {
        ui.findKeyWord(tasks.contains(keyWord));
    }
}
