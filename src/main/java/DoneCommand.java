public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String task = tasks.done(index);
        ui.say("Marked this task as done:\n" + task);
    }
}
