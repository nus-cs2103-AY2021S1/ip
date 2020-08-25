package luoyi.duke.commands;

import luoyi.duke.common.Message;
import luoyi.duke.common.TextFormatter;
import luoyi.duke.data.Duke;
import luoyi.duke.data.IDuke;
import luoyi.duke.data.exception.DukeIllegalArgumentException;
import luoyi.duke.data.task.ITask;
import luoyi.duke.data.task.TaskList;
import luoyi.duke.data.task.ToDo;
import luoyi.duke.storage.Storage;

/**
 * ToDoCommand class to encapsulate a todo command.
 * A todo command creates a new event task,
 * which has a description.
 *
 * A command must be initiated with a Duke object before
 * it can execute.
 */
public class ToDoCommand extends Command {
    private final String description;
    private ToDoCommand(String description, IDuke duke) {
        super(-1, duke);
        this.description = description;
    }

    /**
     * Returns an ToDoCommand object.
     *
     * @param description Description of todo.
     * @return ToDoCommand object with specified properties, not yet initiated with duke.
     */
    public static ToDoCommand getToDoCommand(String description) {
        return new ToDoCommand(description, null);
    }

    /**
     * Executes the todo command.
     * Duke object duke must be initiated.
     *
     * @return Resultant duke object.
     */
    @Override
    public IDuke execute() {
        if (duke == null) {
            throw new RuntimeException(Message.ERR_DUKE_NOT_INIT.toString());
        }
        return handleToDo(description);
    }

    /**
     * Handles the todo operation.
     * Creates a new todo task and store it in the returning Duke object.
     *
     * @param description Description of the event.
     * @return The resulting duke object after adding the todo.
     * @throws DukeIllegalArgumentException If the description is incorrect.
     */
    private IDuke handleToDo(String description) throws DukeIllegalArgumentException {
        if (description.matches("\\s*")) {
            throw new DukeIllegalArgumentException(
                    "The description of todo cannot be empty!");
        }
        ITask task = ToDo.getToDo(description);
        IDuke newDuke = storeTask(task);
        System.out.print(TextFormatter.getFormattedText(
                "Got it. I've added this task:\n\t" + task.toString()
                        + "\nNow you have "
                        +  newDuke.getNumTask() + " task(s) in the list."));
        return newDuke;
    }

    /**
     * Adds task in Duke object.
     * Also invokes storage class to store task list on disk.
     *
     * @param task The tasks to be stored.
     * @return The resultant Duke object with the task stored.
     */
    public IDuke storeTask(ITask task) {
        Storage storage = duke.getStorage();
        TaskList newList = new TaskList(duke.getTasks().getList());
        newList.add(task);
        storage.save(newList.getList());
        return new Duke(newList, storage);
    }

    @Override
    public Command setDuke(IDuke duke) {
        return new ToDoCommand(description, duke);
    }
}
