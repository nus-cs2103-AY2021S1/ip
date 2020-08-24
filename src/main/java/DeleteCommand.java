import java.io.IOException;

public class DeleteCommand extends Command {
    private final int index;

    DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (index > tasks.getSize()) {
                throw new InvalidArgumentException("☹ OOPS!!! The task index you give is not found.");
            }
            String output = "Noted. I've removed this task:\n";
            output += ("\t" + tasks.getTask(index) + "\n");
            output += ("\tNow you have " + (tasks.getSize() - 1) + " tasks in the list.");
            ui.displayMessage(output);
            tasks.deleteTask(index);
            storage.writeData(tasks.getTasks());
        } catch (InvalidArgumentException e) {
            ui.displayMessage(e.getMessage());
        } catch (IOException e) {
            throw new StorageAccessException("☹ OOPS!!! Something went wrong when removing the task from storage.");
        }
    }
}
