import java.util.ArrayList;

public class ListKeywordCommand extends ListCommand {
    private final String keyWord;

    /**
     * Creates a ListCommand.
     *
     * @param keyWord Keyword that tasks to be displayed must contain in their description.
     */
    public ListKeywordCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * Displays all current tasks with their TaskType, done status and description.
     *
     * @param tasks   TaskList to be printed.
     * @param storage Storage is not activated.
     * @return ArrayList containing response message from Duke.
     */
    @Override
    public ArrayList<String> execute(TaskList tasks, Storage storage) {
        return Ui.getTaskList(tasks, null, keyWord);
    }

    public static String toInputString() {
        return "find";
    }
}
