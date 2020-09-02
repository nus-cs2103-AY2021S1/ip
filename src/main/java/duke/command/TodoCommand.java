package duke.command;

import duke.main.TaskList;
import duke.task.Todo;

/**
 * TodoCommand is Command to add a Todo to the related TaskList.
 */
public class TodoCommand extends Command {

    /**
     * The Todo that wants to be added to the related TaskList.
     */
    protected Todo todo;
    protected TaskList tasks;

    /**
     * Constructs a TodoCommand.
     *
     * @param taskDescription The description of the Todo.
     */
    public TodoCommand(String taskDescription) {
        this.todo = new Todo(taskDescription);
    }

    /**
     * Adds the Todo to the related TaskList.
     *
     * @param t The related TaskList.
     */
    @Override
    public void perform(TaskList t) {
        tasks = t;
        tasks.add(todo);
    }

    public String getReply() {
        return " Okay! I have added this task:" + "\n" + "   "
            + todo.toString() + "\n" + " Now you have " + tasks.size() + (tasks.size() > 1 ? " tasks."
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
