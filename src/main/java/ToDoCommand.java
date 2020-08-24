import java.time.LocalDateTime;

public class ToDoCommand extends Command {
    private String task;

    public ToDoCommand(String task) {
        this.task = task;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(task, LocalDateTime.now(), TaskType.TODO);
        ui.say("Added ToDo '" + task + "' to your list!");
    }
}
