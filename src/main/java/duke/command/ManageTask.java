package duke.command;

import duke.task.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class ManageTask extends Command {
    
    public ManageTask(ArrayList<Task> tasks) {
        super(tasks);
    }

    public enum Action {
        DONE,
        DELETE
    }

    /**
     * Modify specific tasks in the task list.
     * Actions include deleting and marking a task as done.
     *
     * @param type Type of action to execute: delete or mark done.
     * @param i Index of the task in the task list.
     */
    public String manageTask(Action type, String i) {
        try {
            int index = Integer.parseInt(i);
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
            DukeException d = new DukeException("Task " + i + " cannot be found");
            return layout.print(d.getMessage());
        } catch (NumberFormatException e) {
            DukeException d = new DukeException(i + " is not an integer");
            return layout.print(d.getMessage());
        }

    }
    
}
