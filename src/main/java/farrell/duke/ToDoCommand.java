package main.java.farrell.duke;

public class ToDoCommand extends Command{
    private String description;

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
