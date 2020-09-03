package dobby.command;

import dobby.DobbyException;
import dobby.TaskList;
import dobby.task.Task;

public class DoneCommand implements Command {

    protected static final String USAGE = "done _task_number_";

    @Override
    public String parseInput(TaskList tasks, String text) throws DobbyException {
        String message = "";
        try {
            text = text.substring(4).trim();
            int index = Integer.parseInt(text);
            if (tasks.getSize() < index) { // if index is out of range throw exception
                throw new DobbyException("Incorrect usage of command.\n"
                        + "Task number must be within the correct range.\n  "
                        + USAGE);
            }
            Task task = tasks.getTask(index - 1);
            task.setDone();

            message = "Great! I've marked this task as done:\n  " + task.getDescription();
        } catch (DobbyException e) { // if index is out of range return message
            return e.getMessage();
        } catch (Exception e) { // missing number after done
            throw new DobbyException("Incorrect usage of command.\n"
                    + "Please enter a task number after done.\n  "
                    + USAGE);
        }
        return message;
    }

}
