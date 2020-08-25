import java.util.List;

public class DoneCommand implements Command {
    int index; // 0-based
    List<Task> list;

    public DoneCommand(int index, List<Task> list) throws DukeException {
        if(index < 0) {
            throw new DukeException("That is not a valid item number.");
        }
        if(index >= list.size()) {
            throw new DukeException("There are only " + list.size() +  " items in the list, try entering a valid item number.");
        }
        this.index = index;
        this.list = list;
    }

    public void execute() throws DukeException {
        Task task = list.get(index);
        if(task.isDone) {
            throw new DukeException("The task \'" + task.getDescription() + "\' has already been marked as done.");
        }
        task.markAsDone();
        new Duke().print("Nice, I've marked this task as done:", "\t" + task.toString());
        new Duke().writeFile(list);
    }
}
