import java.io.IOException;

public class TodoCommand extends Command {
    public String input;

    public TodoCommand(String input) throws InvalidTodoInputException {
        if (input.length() == 0) {
            throw new InvalidTodoInputException();
        }
        this.input = input.startsWith(" ")
                ? input.substring(1)
                : input;
    }

    @Override
    public boolean isExited() {
        return false;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws IOException {
        ToDo todo = new ToDo(this.input);
        tasklist.addTask(todo);
        tasklist.updateData(storage);
        ui.showAdd(todo, tasklist);
    }
}
