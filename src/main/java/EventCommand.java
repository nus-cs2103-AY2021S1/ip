import java.io.IOException;

/**
 * An Event command to add an Event Task to the TaskList
 */
class EventCommand extends Command {
    private String task;
    private String at;
    EventCommand(String toParse) {
        String[] split = toParse.split(" /at ");
        this.task = split[0];
        this.at = split[1];
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.addEvent(this.task, this.at);
        storage.saveFile(tasks);
        return ui.printf("Got it. I've added this task:\n" + task + "\n" + tasks.taskCount());
    }
}
