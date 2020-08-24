public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            ui.displayMessage("No tasks on your agenda.");
        } else {
            ui.displayMessage(String.format("Here are the tasks in your agenda:\n%s", tasks.listTasks()));
        }
    }
}
