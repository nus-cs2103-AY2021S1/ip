package duke.command;

import duke.io.Layout;
import duke.task.DukeException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represent a task manager. Can mark a task as done and delete a task.
 */
public class ManageTask extends Command {
    private Action type; 
    private String inputIndex;
    
    public ManageTask(Action type, String inputIndex) {
        this.type = type;
        this.inputIndex = inputIndex;
    }

    public enum Action {
        DONE,
        DELETE
    }

    /**
     * Modify specific tasks in the task list.
     * Actions include deleting and marking a task as done.
     * @param tasks Existing task list.
     * @param layout Format the return output.
     * @return String to return to the user.
     */
    @Override
    public String execute(ArrayList<Task> tasks, Layout layout) {
        try {
            int index = Integer.parseInt(inputIndex);
            assert index >= 1;
            Task task = tasks.get(index - 1);
            switch (type) {
            case DONE:
                task.markDone();
                return layout.printMarkedDone(task);
            case DELETE:
                tasks.remove(index - 1);
                return layout.printDeleted(task, tasks.size());
            default:
                return "Should never reach here";
            }
        } catch (IndexOutOfBoundsException e) {
            DukeException d = new DukeException("Task " + inputIndex + " cannot be found");
            return layout.print(d.getMessage());
        } catch (NumberFormatException e) {
            DukeException d = new DukeException(inputIndex + " is not an integer");
            return layout.print(d.getMessage());
        }
    }
    
}
