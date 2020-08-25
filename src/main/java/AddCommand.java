import java.io.IOException;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.add(this.task);
            ui.printAdd(this.task, taskList.size());
            storage.add(this.task);
        } catch(IOException e) {
            System.out.println("Something went wrong!");
        }
    }
}
