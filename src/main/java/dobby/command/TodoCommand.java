package dobby.command;

import dobby.DobbyException;
import dobby.TaskList;
import dobby.task.Todo;

public class TodoCommand implements Command {

    @Override
    public String parseInput(TaskList tasks, String text) throws DobbyException {
        String message = "";
        assert text.startsWith("todo") : "Todo command must start with todo";
        try {
            int commandLen = "todo".length();
            String description = text.substring(commandLen + 1).trim();

            Todo todo = new Todo(description);
            tasks.addToList(todo);
            message = "Great! I've added the following task:\n  " + todo.getDescription()
                    + String.format("\nNow you have %d task%s in the list.", tasks.getSize(),
                    tasks.getSize() > 1 ? "s" : "");
        } catch (StringIndexOutOfBoundsException e) {
            throw new DobbyException("Incorrect usage of command.\nDescription cannot be empty. "
                    + "Please try again.");
        }
        assert message != null : "Return message to user cannot be empty";
        return message;
    }

}
