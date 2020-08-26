package duke.command;

import duke.main.TaskList;
import duke.task.Todo;

public class TodoCommand extends Command {

    protected Todo todo;

    public TodoCommand(String taskDescription) {
        this.todo = new Todo(taskDescription);
    }

    @Override
    public void perform(TaskList tasks) {
        tasks.add(todo);
        System.out.println(" Okay! I have added this task:" + "\n" + "   "
                + todo.toString() + "\n" + " Now you have " + tasks.size() + (tasks.size() > 1 ? " tasks."
                : " task."));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
