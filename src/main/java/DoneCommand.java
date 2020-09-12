import java.io.IOException;

/**
 * A done command to mark a given task number as done
 */
class DoneCommand extends Command {
    private int doneTask;

    DoneCommand(int task, TaskList tasks, Ui ui, Storage storage) {
        super(tasks, ui, storage);
        this.doneTask = task;
    }

    @Override
    public String execute() throws IOException {
        tasks.done(this.doneTask);
        storage.saveFile(tasks);
        return ui.printf("Nice! I've marked this task as done:\n" + tasks.get(this.doneTask));
    }
}
