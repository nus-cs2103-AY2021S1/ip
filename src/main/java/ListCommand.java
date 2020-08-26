/**
 * Represents a list command from a user.
 */
public class ListCommand extends Command{

    ListCommand(String str) {
        super(str);
    }

    /**
     * Displays all tasks in the list to the user.
     *
     * @param list A list of task.
     * @param ui Ui that prints out the output.
     * @param storage Storage that reads from and writes to hard disk.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.showList(list);
    }
}
