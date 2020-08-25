import java.util.Date;

public class DeadlineCommand extends Command {
    String description;
    Date by;
    
    DeadlineCommand(String description, Date by) {
        super(true);
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Deadline(description, by);
        tasks.add(task);
        storage.save(tasks);
        String response = String.format(
                "I've added this task:\n  %s \nNow you have %s tasks in the list.",
                task, tasks.size()
        );
        ui.printResponse(response);
    }

    @Override
    public String toString() {
        return "deadline <task> /by <date>";
    }
}
