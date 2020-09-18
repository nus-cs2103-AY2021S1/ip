package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.ToDo;
import duke.Ui;
import duke.exception.InvalidTodoInputException;

import java.io.IOException;

/**
 * TodoCommand deals with todo input.
 */
public class TodoCommand extends Command {
    private String input;

    /**
     * Initiate Todo Command.
     * @param input  User input
     * @throws InvalidTodoInputException
     */
    public TodoCommand(String input) throws InvalidTodoInputException {
        boolean isInvalidInput = input.trim().length() == 0;
        boolean isValidInput = ! isInvalidInput;

        if (isInvalidInput) {
            throw new InvalidTodoInputException();
        }

        assert isValidInput;
        this.input = input;
    }

    @Override
    public boolean isExited() {
        return false;
    }

    /**
     * Execute todo command.
     * @param tasklist  TaskList for ToDo to be added
     * @param storage   Storage to update save file
     * @throws IOException
     */
    @Override
    public String execute(TaskList tasklist, Storage storage) throws IOException {
        ToDo todo = new ToDo(this.input);
        tasklist.addTask(todo);
        tasklist.updateData(storage);
        return Ui.showAdd(todo, tasklist);
    }
}
