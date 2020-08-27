package Duke.Command;

import Duke.*;
import Duke.Exception.*;

import java.io.IOException;

/**
 * TodoCommand deals with todo input.
 */
public class TodoCommand extends Command {
    public String input;

    /**
     * Initiate TodoCommand.
     * @param input  User input
     * @throws InvalidTodoInputException
     */
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

    /**
     * Execute todo command.
     * @param tasklist  TaskList for ToDo to be added
     * @param ui        User interface used
     * @param storage   Storage to update save file
     * @throws IOException
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws IOException {
        ToDo todo = new ToDo(this.input);
        tasklist.addTask(todo);
        tasklist.updateData(storage);
        ui.showAdd(todo, tasklist);
    }
}
