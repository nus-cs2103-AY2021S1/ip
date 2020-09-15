import java.io.IOException;

public class DeleteCommand extends Command {

    private int i;

    /**
     * Constructor for the class
     * @param i
     */
    public DeleteCommand(int i) {
        this.i = i;
    }

    /**
     * Execute the command.
     * @param tasks
     * @param ui
     * @param storage
     * @return a String of the response message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task deletedTask = tasks.getList().get(this.i - 1);
        tasks.delete(this.i - 1);
        // delete file in storage
//            storage.saveTask(this.task);
        return ui.deleteTask(deletedTask) + "\n" + tasks.printTaskSize();
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
