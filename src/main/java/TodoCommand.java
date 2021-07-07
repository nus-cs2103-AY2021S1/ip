/**
 * Represents a to-do command.
 */
public class TodoCommand extends Command {

    /**
     * Creates a to-do command.
     *
     * @param str to-do command
     */
    TodoCommand(String str) {
        super(str);
    }

    /**
     * Executes a to-do command.
     *
     * @param list task list
     * @param ui ui object
     * @param storage storage object
     * @return message for user to indicate whether they successfully add a to-do task
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        String s;
        try {
            Task newTask = new Todo(str.substring(5));
            list.getList().add(newTask);
            s = ui.printAddTask(newTask, list.getList().size());
        } catch (IndexOutOfBoundsException e) {
            s = ui.printNoDescription();
        }
        return s;
    }
}
