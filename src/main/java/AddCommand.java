import java.io.IOException;

public class AddCommand extends Command {

    private Task task;

    /**
     * Constructor for AddCommand.
     * @param task
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Execute the command.
     * @param tasks
     * @param ui
     * @param storage
     * @return a String of message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.addTask(this.task);
            storage.saveTask(this.task);
//            ui.addTask(this.task);
            return ui.addTask(this.task) + "\n" + tasks.printTaskSize();
        } catch (IOException e) {
//            System.out.println("to be changed");
            return e.getMessage();
        }
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
