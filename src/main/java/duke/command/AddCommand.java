package duke.command;

import duke.error.IncorrectFormat;
import duke.parts.Storage;
import duke.parts.TaskList;
import duke.parts.Ui;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Event;

/**
 * Represents a command which is used to add a task to the list.
 * It is executed when the execute method is called.
 */
public class AddCommand extends Command{

    String input;

    public AddCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the add command.
     * It will create and add the task as specified by the input when the command is created.
     * @param tasks The task list of the system.
     * @param ui The UI of the system which interacts with user.
     * @param storage The storage of the system.
     * @throws IncorrectFormat
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IncorrectFormat {
        int space = input.indexOf(" ");
        int slash = input.indexOf("/");
        int length = input.length();
        Task newTask;
        switch(input.substring(0, space)) {
        case "todo":
            newTask = new ToDo(input.substring(space, length));
            ui.printNew(newTask, "ToDo", tasks.numTask() + 1);
            break;
        case "deadline":
            String dateStringDeadline = input.substring(slash + 1, input.length());
            newTask = new Deadline(input.substring(space, slash), dateStringDeadline);
            ui.printNew(newTask, "Deadline", tasks.numTask() + 1);
            break;
        case "event":
            String dateStringEvent = input.substring(slash + 1, input.length());
            newTask = new Event(input.substring(space, slash), dateStringEvent);
            ui.printNew(newTask, "Event", tasks.numTask() + 1);
            break;
        default:
            throw new IncorrectFormat();
        }
        tasks.addTask(newTask, storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
