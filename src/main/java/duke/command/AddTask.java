package duke.command;

import duke.io.Layout;
import duke.io.Parser;
import duke.task.Deadline;
import duke.task.DukeException;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.util.ArrayList;

public class AddTask extends Command {

    protected String [] arr;
    
    public AddTask(ArrayList<Task> tasks) {
        super(tasks);
    }


    public enum Type {
        TODO,
        DEADLINE,
        EVENT
    };
    
    /**
     * Add specific tasks to task list.
     *
     * @param type Type of task.
     * @param arr Array of words from the tasks's description and (if any) date.
     */
    public void addTask(Type type, String [] arr) {
        Task task;

        try {
            String date = parser.getDateAndDescription(arr)[0];
            String description = parser.getDateAndDescription(arr)[1];
            if (description.equals("") || arr.length == 1) {
                throw new DukeException("The description of a " + type + " cannot be empty");
            }

            switch (type) {
            case DEADLINE:
                if (date.equals("")) {
                    throw new DukeException("Please specify a due date using /by");
                } else {
                    task = new Deadline(description, date);
                }
                break;
            case EVENT:
                if (date.equals("")) {
                    throw new DukeException("Please specify an event date using /at");
                } else {
                    task = new Event(description, date);
                }
                break;
            default: //case: todo
                task = new Todo(description);
                break;
            }

            tasks.add(task);
            layout.printAddedMessage(task.toString(), tasks.size());
        } catch (DukeException e) {
            layout.print(e.getMessage());
        }
    }
    
}
