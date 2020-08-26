public class DoneCommand extends Command {
    
    private final int taskIdx;
    
    public DoneCommand(String taskIdx) throws DukeException {
        try {
            this.taskIdx = Integer.parseInt(taskIdx);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid arguments provided!");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(taskIdx);
        tasks.markAsDone(task);
        storage.doneTask(tasks.getTask(taskIdx));
        ui.botOutput("Nice! I've marked this task as done:\n" + task);
    }
}
