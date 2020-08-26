public class DeleteCommand extends Command {
    
    private final int taskIdx;
    
    public DeleteCommand(String taskIdx) throws DukeException {
        try {
            this.taskIdx = Integer.parseInt(taskIdx);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid arguments provided!");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(taskIdx);
        tasks.deleteTask(task);
        storage.deleteTask(task);
        ui.botOutput("Noted. I've removed this task:\n" + task);
    }
}
