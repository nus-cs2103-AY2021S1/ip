package dobby.command;

import dobby.DobbyException;
import dobby.TaskList;
import dobby.task.Task;

public class DeleteCommand implements Command {

    @Override
    public String parseInput(TaskList tasks, String text) throws DobbyException {
        String message = "";
        assert text.startsWith("delete") : "Delete command must start with delete";
        try {
            int commandLen = "delete".length();
            text = text.substring(commandLen).trim();

            int index = Integer.parseInt(text);
            if (tasks.getSize() < index) { // if index is out of range throw exception
                throw new DobbyException("Incorrect usage of command.\n"
                        + "Task number must be within the correct range.");
            }

            assert index <= tasks.getSize() : "Task to delete must be in correct range";
            Task task = tasks.getTask(index - 1);
            tasks.removeTask(index - 1);

            message = "Noted. I've removed this task:\n  " + task.getDescription();
        } catch (DobbyException e) { // if index is out of range return message
            return e.getMessage();
        } catch (Exception e) { // missing number after done
            throw new DobbyException("Incorrect usage of command.\n"
                    + "Please enter a task number after delete.");
        }
        assert message != null : "Return message to user cannot be empty";
        return message;
    }
}
