package duke.command;

import duke.io.Layout;
import duke.io.Parser;
import duke.task.Deadline;
import duke.task.DukeException;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

/**
 * Represents add task command.
 */
public class AddTask extends Command {

    Type type;
    protected String [] input;
    
    public AddTask(Type type, String [] input) {
        this.type = type;
        this.input = input;
    }

    public enum Type {
        TODO,
        DEADLINE,
        EVENT
    };

    /**
     * Add specific task to the task list.
     * @param tasks Existing task list.
     * @param layout Format the return output.
     * @return String to return to the user.
     */
    @Override
    public String execute(ArrayList<Task> tasks, Layout layout) {
        Task task;

        try {
            String date = Parser.getDateAndDescription(input)[0];
            String description = Parser.getDateAndDescription(input)[1];
            if (description.equals("") || input.length == 1) {
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
            return layout.printAddedMessage(task.toString(), tasks.size());
        } catch (DukeException e) {
            return layout.print(e.getMessage());
        }
    }
}
