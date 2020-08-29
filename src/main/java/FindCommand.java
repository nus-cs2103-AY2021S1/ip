import java.util.ArrayList;

/**
 *  A subclass of Command.
 *  Handles "find" command.
 */
public class FindCommand extends Command {
    private static final String TAB = "  ";
    private static final String FIND_TITLE = TAB + " Here are the matching tasks in your list:";
    private String[] input;

    /**
     * Constructor.
     * @param input user input.
     */
    public FindCommand(String[] input) {
        super();
        this.input = input;
    }

    /**
     * Executes the command.
     * @param tasks a list of tasks.
     * @param ui ui.
     * @param storage the storage working on data file.
     * @throws FindException to show incorrect user input.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FindException {
        ArrayList<Task> store = tasks.getTaskList();
        ArrayList<Task> filteredList = new ArrayList<>();

        if (input.length == 1) { //incomplete done command
            throw new FindException(" ☹ OOPS!!! The description of a find cannot be empty.");
        }
        String keyword = input[1];
        for (Task task : store) {
            String[] description = task.getDescription().split("\\s");
            boolean isFound = false;
            for (String word : description) {
                if (word.equals(keyword)) {
                    isFound = true;
                    break;
                }
            }
            if (isFound) {
                filteredList.add(task);
            }
        }
        if (filteredList.size() == 0) {
            throw new FindException(" ☹ OOPS!!! There is no task with this keyword.");
        }
        int i = 1;
        System.out.println(FIND_TITLE);
        for (Task task : filteredList) {
            System.out.println(TAB + " " + i++ + "." + task);
        }
    }

    /**
     * Returns isDone to stop user from entering command.
     * @return false to continue to accept user input.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
