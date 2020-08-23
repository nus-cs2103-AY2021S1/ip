import java.io.IOException;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, DukeStorage storage) {
        try {
            storage.saveStorage(taskList.getTasks());
        } catch (IOException ex) {
            System.out.println("Error in saving!");
            ex.printStackTrace();
        }
        ui.format("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isCompleted() {
        return true;
    }
}
