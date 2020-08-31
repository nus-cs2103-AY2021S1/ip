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
    public String execute() {
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
     * @return Resulting string prompt.
     * @throws DukeIllegalArgumentException If the description is incorrect.
     */
    private String handleToDo(String description) throws DukeIllegalArgumentException {
        if (description.matches("\\s*")) {
            throw new DukeIllegalArgumentException(
                    "The description of todo cannot be empty!");
        }
        ITask task = ToDo.getToDo(description);
        storeTask(task);
        String output = "Got it. I've added this task:\n\t" + task.toString()
                + "\nNow you have "
                +  duke.getNumTask() + " task(s) in the list.";
        System.out.print(TextFormatter.getFormattedText(output));
        return output;
    }

    /**
     * Adds task in Duke object.
     * Also invokes storage class to store task list on disk.
     *
     * @param task The tasks to be stored.
     */
    public void storeTask(ITask task) {
        Storage storage = duke.getStorage();
        TaskList list = duke.getTasks();
        list.add(task);
        storage.save(list.getList());
    }

    @Override
    public Command setDuke(IDuke duke) {
        return new ToDoCommand(description, duke);
    }
}
