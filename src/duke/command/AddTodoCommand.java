package duke.command;

import duke.*;
import duke.task.TaskList;
import duke.task.Todo;

import java.io.IOException;

/**
 * AddTodoCommand class that represents add Todo commands
 */
public class AddTodoCommand extends Command {

    /**
     * AddTodoCommand Class constructor
     * @param command the command from the user
     */
    public AddTodoCommand(String command) {
        super(command);
    }

    /**
     * Method that execute the current AddTodoCommand object
     * @param list TaskList object from the current Duke instance
     * @param ui    UI object from the current Duke instance
     * @param saveData Storage object from the current Duke instance
     */
    public void execute(TaskList list, Ui ui, Storage saveData) {
        try {
            if (this.command.trim().length() == 4) {
                throw new DukeException("☹ OOPS!!! Check todo formatting, include description");
            }
            Todo task = new Todo(this.command.substring(5));
            list.add(task);
            ui.saySomthing("Got it. I've added this task:\n" + task.toString() + "\n" + String.format("Now you have %d tasks in the list.", list.size()));
            String save = "T>0>"+this.command.substring(5);
            saveData.addTask(save);
        } catch (DukeException | IOException e) {
            ui.saySomthing(e.getMessage());
        }
    }

    /**
     * Method that return isExit of the current Command
     * @return boolean object showing if Duke should terminate
     */
    @Override
    public boolean isExit() {
        return isExit;
    }
}
