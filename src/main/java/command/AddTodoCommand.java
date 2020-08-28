package command;

import java.io.IOException;
import java.util.Arrays;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import exception.TaskException;



/**
 * Represents a command to add a todo to the tasklist.
 */
public class AddTodoCommand extends Command {
    public AddTodoCommand(String[] command) {
        super(command);
    }

    /**
     * Adds Todo to the Duke.TaskList and save it to storage.
     * @param tasks the list of task saved.
     * @param ui deals with interaction with the user.
     * @param storage deals with loading tasks from the file and saving tasks in the file.
     * @throws DukeException if there are no description.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task temp = new Task(commands[1]);
            tasks.addTask(temp);
            try {
                storage.saveFile(tasks);
            } catch (IOException e) {
                System.out.println(e);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new TaskException();
        }
    }

    /**
     * Indicates to not exit the loop.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof AddTodoCommand) {
            AddTodoCommand cur = (AddTodoCommand) o;
            if (Arrays.equals(this.commands, cur.commands)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
