import java.io.IOException;
import java.time.DateTimeException;

public class AddCommand extends Command {

    private final String commandWord;
    private final String taskName;

    public AddCommand(String commandWord, String taskName) {
        this.commandWord = commandWord;
        this.taskName = taskName;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        try {
            switch (commandWord) {
                case "todo":
                    taskList.addTodo(taskName, storage);
                    break;
                case "deadline":
                    taskList.addDeadline(taskName, storage);
                    break;
                case "event":
                    taskList.addEvent(taskName, storage);
                    break;
            }
        } catch (DateTimeException e) {
            throw new DukeException("Please enter deadline in this format: dd/MM/yyyy timeIn24Hr" +
                    "\nE.g. 01/12/2020 2359");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Invalid description of a deadline item!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
