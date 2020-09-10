package duke.command;

import duke.main.TaskList;
import duke.task.Todo;

/**
 * TodoCommand is Command to add a Todo to the related TaskList.
 */
public class TodoCommand extends Command {
    /** The Todo that wants to be added to the related TaskList. **/
    private Todo todo;
    /** TaskList that is related to this command **/
    private TaskList tasks;

    /**
     * Constructs a TodoCommand.
     *
     * @param description The description of the Todo.
     */
    public TodoCommand(String description) {
        this.todo = new Todo(description);
    }

    /**
     * Adds the Todo to the related TaskList.
     *
     * @param t The related TaskList.
     */
    @Override
    public void perform(TaskList t) {
        tasks = t;
        int a = tasks.getSize();
        tasks.add(todo);
        assert tasks.getSize() == a + 1;
    }

    /**
     * Gets the reply after performing the Command.
     *
     * @return A reply as a String based on the perform method.
     **/
    public String getReply() {
        return " Okay! I have added this task:" + "\n" + "   "
            + todo.toString() + "\n" + " Now you have " + tasks.getSize() + (tasks.getSize() > 1 ? " tasks."
            : " task.");
    }

    /**
     * Checks if this is a termination Command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
