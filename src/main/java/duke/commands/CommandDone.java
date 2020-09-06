package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class CommandDone extends Command {

    public static final String COMMAND_STRING = "done";

    private int doneIdx;

    /**
     * Constructor for CommandDone.
     * @param taskList Task List.
     * @param ui Ui.
     * @param doneIdx Index of task to be set as done.
     */
    public CommandDone(TaskList taskList, Ui ui, int doneIdx) {
        super(taskList, ui);
        this.doneIdx = doneIdx;
    }

    @Override
    public void execute() throws DukeException {
        taskList.get(doneIdx).setDone();
        String output = "Finally... about time you finished that. Marked this task as done: \n";
        output += taskList.get(doneIdx) + "\n";
        ui.outputBlockToUser(output);
    }
}
