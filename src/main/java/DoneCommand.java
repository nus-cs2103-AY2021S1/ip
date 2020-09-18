/**
 * Represents a done command.
 */
public class DoneCommand extends Command {

    /**
     * Creates done command.
     *
     * @param str done command
     */
    DoneCommand(String str) {
        super(str);
    }

    /**
     * Mark a specific task in the task list as done.
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
            list.getList().get(num - 1).markDone();
            s = ui.printDone(list, num);
        } catch (IndexOutOfBoundsException e) {
            s = ui.printInvalidNumber();
        }
        return s;
    }
}
