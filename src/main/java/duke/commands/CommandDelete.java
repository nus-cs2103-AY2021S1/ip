package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;

public class CommandDelete extends Command {

    public static final String COMMAND_STRING = "delete";

    private int toDeleteIdx;

    /**
     * Constructor for CommandDelete.
     * @param taskList task list.
     * @param toDeleteIdx index of task to delete.
     */
    public CommandDelete(TaskList taskList, int toDeleteIdx) {
        super(taskList);
        this.toDeleteIdx = toDeleteIdx;
    }

    @Override
    public String execute() throws DukeException {
        String output = "Feeling weak? Giving up? Well done... Removed this task: \n";
        output += taskList.remove(toDeleteIdx) + "\n";
        return output;
    }
}
