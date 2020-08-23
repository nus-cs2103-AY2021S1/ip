public class DeleteCommand extends Command {
    private static int taskindex;

    public DeleteCommand(int taskindex) {
        DeleteCommand.taskindex = taskindex - 1;
    }

    public void execute (TaskList tasks, Ui ui, Storage storage) throws CustomException {
        try {
            Ui.display("Noted. I've removed this task:\n   " + tasks.getTask(taskindex) +
                    "\nNow you have " + tasks.getSize() + " tasks in your list.");
            tasks.deleteTask(taskindex);
            Storage.writeToFile(tasks.getList());
        } catch (Exception e) {
            throw new CustomException("This task does not exist!");
        }
    }
}
