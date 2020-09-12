package main.java.farrell.duke.command;

import main.java.farrell.duke.DukeException;
import main.java.farrell.duke.task.TaskList;
import main.java.farrell.duke.task.ToDo;

public class ToDoCommand extends Command {
    private String description;

    /**
     * Creates a ToDoCommand with a specified description.
     *
     * @param description The description for the resulting task.
     */
    public ToDoCommand(String description) {
        this.description = description;
        type = CommandType.TODO;
    }

    @Override
    public String execute(TaskList taskList) throws DukeException {
        ToDo todo = new ToDo(description);
        taskList.addTask(todo);
        return "Added: " + todo.toString();
    }
}
