import java.io.IOException;

/**
 * A Deadline command to add a Deadline Task to the TaskList
 */
class DeadlineCommand extends Command {
    private String task;
    private String deadline;

    DeadlineCommand(String toParse, TaskList tasks, Ui ui, Storage storage) {
        super(tasks, ui, storage);
        String[] split = toParse.split(" /by ");
        this.task = split[0];
        this.deadline = split[1];
    }

    @Override
    public String execute() throws IOException {
        Task task = tasks.addDeadline(this.task, this.deadline);
        storage.saveFile(tasks);
        return ui.printf("Got it. I've added this task:\n" + task + "\n" + tasks.taskCount());
    }
}
