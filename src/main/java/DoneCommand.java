import java.util.List;

public class DoneCommand implements Command {
    int index; // 0-based
    List<Task> list;

    public DoneCommand(int index, List<Task> list) {
        this.index = index;
        this.list = list;
    }

    public void execute() {
        Task task = list.get(index);
        task.markAsDone();
        new Duke().print("Nice, I've marked this task as done:", task.toString());
    }
}
