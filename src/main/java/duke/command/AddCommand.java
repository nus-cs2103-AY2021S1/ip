package duke.command;

import duke.exceptions.DukeException;
import duke.Storage;
import duke.Ui;
import duke.exceptions.IncompleteDukeCommandException;
import duke.task.*;

public class AddCommand extends Command {

    private Task createdTask;
    private int remainingTaskCount;

    public AddCommand(TaskType type, String taskParameters) throws DukeException {
        switch(type) {
        case DEADLINE:
            createdTask = Deadline.createTask(taskParameters);
            break;
        case EVENT:
            createdTask = Event.createTask(taskParameters);
            break;
        case TODO:
            createdTask = ToDo.createTask(taskParameters);
            break;
        default:
            throw new DukeException("I don't understand.");
        }
    }

    public void execute(TaskList list, Storage storage) {
        list.add(createdTask);
        this.remainingTaskCount = list.taskCount();
        super.completed = true;
    }

    public void printFeedback(Ui ui) throws IncompleteDukeCommandException {
        if (super.completed) {
            String feedback = String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in your list.\n", createdTask.toString(), remainingTaskCount);
            ui.formattedPrint(ui.prependIndent(feedback, 1));
        } else {
            throw new IncompleteDukeCommandException("Add command was not completed.");
        }
    }

    public boolean isExit() {
        return false;
    }
}
