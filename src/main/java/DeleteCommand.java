import java.util.ArrayList;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> lib = tasks.getTaskList();

        if (index >= lib.size() || lib.size() == 0 || index < 0) {
            ui.printExceptions(new DukeException(
                    "This task ID does not exist in the database!"));
        } else {
            tasks.deleteTask(index);
            ui.printDeleteStatement(lib.get(index).toString(), lib.size());
        }
    }

    @Override
    public boolean isDone() {
        return false;
    }

}
