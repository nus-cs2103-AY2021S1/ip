public class DoneCommand extends Command {
    private final int taskNo;

    public DoneCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.doTask(taskNo);
        ui.showPrompt("Nice! I've marked this task as done:\n  " + task);
    }
}
