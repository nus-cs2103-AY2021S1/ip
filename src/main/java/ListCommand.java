/**
 * A list command to list all tasks
 */
class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printf("Here are the tasks in your list:\n" + tasks.toString());
    }
}
