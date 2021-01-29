package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;

public class CommandDone extends Command {

    public static final String COMMAND_STRING = "done";

    private int doneIdx;

    /**
     * Constructor for CommandDone.
     * @param taskList Task List.
     * @param doneIdx Index of task to be set as done.
     */
    public CommandDone(TaskList taskList, int doneIdx) {
        super(taskList);
        this.doneIdx = doneIdx;
    }

    @Override
    public String execute() throws DukeException {
        taskList.get(doneIdx).setDone();
        String output = "Finally... about time you finished that. Marked this task as done: \n";
        output += taskList.get(doneIdx) + "\n";
        return output;
    }
}
