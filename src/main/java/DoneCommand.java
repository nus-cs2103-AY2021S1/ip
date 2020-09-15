public class DoneCommand extends Command {

    private int i;

    /**
     * Constructor for the class.
     * @param i
     */
    public DoneCommand(int i) {
        this.i = i;
    }

    /**
     * Execute the command.
     * @param tasks
     * @param ui
     * @param storage
     * @return a string of message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getList().get(this.i - 1);
        task.markAsDone();
        tasks.setTask(this.i, task);
        // update task in storage
//            storage.saveTask(this.task);
        return  ui.completedTask(task) + "\n" + tasks.printTaskSize();
    }

    /**
     * Check if the command is an exit command.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
