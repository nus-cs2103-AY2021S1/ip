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
        new Duke().print("Got it. I've added this task:", "\t" + task,
                         "Now you have " + list.size() + " task(s) in the list");
        new Duke().writeFile(list);
    }
}
