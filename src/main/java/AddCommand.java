import java.util.List;

public class AddCommand implements Command {
    Task task;
    List<Task> list;

    public AddCommand(Task task, List<Task> list) {
        this.task = task;
        this.list = list;
    }

    @Override
    public void execute() {
        list.add(task);
        new Duke().print("added: " +  task.getDescription());
    }
}
