package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;
import duke.task.Todo;



/**
 * AddTodoCommand class that represents add Todo commands
 */
public class AddTodoCommand extends Command {

    /**
     * AddTodoCommand Class constructor
     *
     * @param command the command from the user
     */
    public AddTodoCommand(String command) {
        super(command);
    }

    /**
     * Method that execute the current AddTodoCommand object
     *
     * @param list     TaskList object from the current Duke instance
     * @param ui       UI object from the current Duke instance
     * @param saveData Storage object from the current Duke instance
     */
    public String execute(TaskList list, Ui ui, Storage saveData) {
        try {
            if (this.getCommand().trim().length() == 4) {
                throw new DukeException("☹ OOPS!!! Check todo formatting, include description");
            }
            Todo task = new Todo(this.getCommand().substring(5));
            String description = this.getCommand().substring(5);
            if (detectDuplicate(list, description)) {
                throw new DukeException("☹ OOPS!!! This is a duplicated task!.");
            }
            list.add(task);
            String saySomthing = ("Got it. I've added this task:\n" + task.toString()
                    + "\n" + String.format("Now you have %d tasks in the list.", list.size()));
            String save = "T>0>" + this.getCommand().substring(5);
            saveData.addTask(save);
            ui.saySomthing(saySomthing);
            return saySomthing;
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Method that return isExit of the current Command
     *
     * @return boolean object showing if Duke should terminate
     */
    @Override
    public boolean isExit() {
        return getIsExit();
    }
}
