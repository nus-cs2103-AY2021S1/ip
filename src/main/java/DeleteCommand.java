/**
 * Represents a delete command from a user
 * which will lead to the deletion of a task in the task list.
 */
public class DeleteCommand extends Command {

    /**
     * Creates a delete command.
     *
     * @param str user input command
     */
    DeleteCommand(String str) {
        super(str);
    }

    /**
     * Deletes a specific task from the task list.
     *
     * @param list A list of task.
     * @param ui Ui that prints out the output.
     * @param storage Storage that reads from and writes to hard disk.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        String s;
        try {
            int num = Integer.parseInt(str.split(" ")[1]);
            s = ui.printDelete(list, num);
            list.getList().remove(num - 1);
        } catch (IndexOutOfBoundsException e) {
            s = ui.printInvalidNumber();
        }
        return s;
    }
}
