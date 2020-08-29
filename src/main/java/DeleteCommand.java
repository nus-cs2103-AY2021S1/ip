import java.util.ArrayList;

/**
 * A subclass of Command.
 * Handles "delete" command.
 */
public class DeleteCommand extends Command {
    private static final String TAB = "  ";
    private static final String DELETE_TITLE = TAB + " Noted. I've removed this task:";
    private String[] input;

    /**
     * A subclass of command.
     * @param input user input.
     */
    public DeleteCommand(String[] input) {
        super();
        this.input = input;
    }

    /**
     * Execute the command.
     * @param tasks list of existing tasks.
     * @param ui Ui.
     * @param storage storage of data.
     * @throws DeleteException exception for invalid input.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DeleteException {
        ArrayList<Task> store = tasks.getTaskList();
        if (this.input.length == 1) { //incomplete done command
            throw new DeleteException(" ☹ OOPS!!! The description of a delete cannot be empty.");
        }

        int indexOfMarkingTask = Integer.parseInt(this.input[1]) - 1;
        if (indexOfMarkingTask + 1 > store.size() || indexOfMarkingTask + 1 <= 0) {
            throw new DeleteException(" ☹ OOPS!!! There is no such task.");
        }

        //complete done command
        Task deletingTask = store.get(indexOfMarkingTask);
        store.remove(indexOfMarkingTask);
        storage.save(new TaskList(store));

        System.out.println(DELETE_TITLE);
        System.out.println(TAB + "   " + deletingTask);
        System.out.println(TAB + " Now you have " + store.size() + " tasks in the list.");
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

