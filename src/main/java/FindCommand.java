import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * FindCommand inherits from Command and is used when user input starts with "find".
 */
class FindCommand extends Command {
    protected String word;

    /**
     * Constructor for FindCommand which takes in the input keyed in by user.
     *
     * @param word The word to be found within the list of tasks.
     */
    FindCommand(String word) {
        this.word = word;
    }

    /**
     * Used to find the tasks that match the user's input.
     *
     * @param tasks This is the saved TaskList in saved file.
     * @return A boolean to indicate whether the program should be exited.
     */
    @Override
    boolean execute(TaskList tasks) {
        Ui.printTasks(tasks.find(word), Action.FIND);
        return false;
    }
}