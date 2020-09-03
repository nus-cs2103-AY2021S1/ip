package dobby.command;

import dobby.DobbyException;
import dobby.TaskList;
import dobby.task.Todo;

public class TodoCommand implements Command {
    protected static final String USAGE = "todo _description_";

    @Override
    public String parseInput(TaskList tasks, String text) throws DobbyException {
        String message = "";
        try {
            text = text.substring(5).trim();
            Todo todo = new Todo(text);
            tasks.addToList(todo);
            message = "Great! I've added the following task:\n  " + todo.getDescription()
                    + String.format("\nNow you have %d task%s in the list.", tasks.getSize(),
                    tasks.getSize() > 1 ? "s" : "");
        } catch (StringIndexOutOfBoundsException e) {
            throw new DobbyException("Incorrect usage of command.\nDescription cannot be empty. "
                    + "Please try again.\n  " + USAGE);
        }
        return message;
    }

}
