package dobby.command;

import dobby.DobbyException;
import dobby.TaskList;
import dobby.task.Task;

public class DoneCommand implements Command {

    @Override
    public String parseInput(TaskList tasks, String text) throws DobbyException {
        String message = "";
        assert text.startsWith("done") : "Done command must start with done";
        try {
            int commandLen = "done".length();
            text = text.substring(commandLen).trim();

            int index = Integer.parseInt(text);
            if (tasks.getSize() < index) { // if index is out of range throw exception
                throw new DobbyException("Incorrect usage of command.\n"
                        + "Task number must be within the correct range.");
            }

            Task task = tasks.getTask(index - 1);
            task.setDone();
            assert task.isDone() : "Task should be marked done";

            message = "Great! I've marked this task as done:\n  " + task.getDescription();
        } catch (DobbyException e) { // if index is out of range return message
            return e.getMessage();
        } catch (Exception e) { // missing number after done
            throw new DobbyException("Incorrect usage of command.\n"
                    + "Please enter a task number after done.");
        }
        assert message != null : "Return message to user cannot be empty";
        return message;
    }

}
