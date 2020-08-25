import java.time.LocalDate;

public class DeadlineCommand extends Command {
    private String description;
    private LocalDate time;

    public DeadlineCommand(String description, LocalDate time) {
        this.description = description;
        this.time = time;
    }

    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Deadline(this.description, false, this.time);
        tasks.addTask(newTask);
    }
}
