import java.io.IOException;

/**
 * A done command to mark a given task number as done
 */
class DoneCommand extends Command {
    private int doneTask;

    DoneCommand(int task) {
        super();
        this.doneTask = task;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.done(this.doneTask);
        storage.saveFile(tasks);
        return ui.printf("Nice! I've marked this task as done:\n" + tasks.get(this.doneTask));
    }
}
