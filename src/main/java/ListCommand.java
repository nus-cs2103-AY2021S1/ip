/**
 * A list command to list all tasks
 */
class ListCommand extends Command {
    ListCommand(TaskList tasks, Ui ui, Storage storage) {
        super(tasks, ui, storage);
    }
    @Override
    public String execute() {
        return ui.printf("Here are the tasks in your list:\n" + tasks.toString());
    }
}
