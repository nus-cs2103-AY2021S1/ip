import java.util.List;

public class DoneCommand implements Command {
    int index; // 0-based

    public DoneCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if(index < 0) {
            throw new DukeException("That is not a valid item number.");
        }
        Task task = tasks.get(index);
        if(task.isDone) {
            throw new DukeException("The task \'" + task.getDescription() + "\' has already been marked as done.");
        }
        if(index >= tasks.size()) {
            throw new DukeException("There are only " + tasks.size() +  " items in the list, try entering a valid item number.");
        }
        task.markAsDone();
        ui.print("Nice, I've marked this task as done:", "\t" + task.toString());
        storage.write(tasks);
    }
}
