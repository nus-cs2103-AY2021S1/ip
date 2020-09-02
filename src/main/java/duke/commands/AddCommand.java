package duke.commands;

import duke.*;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Represents the add commands that adds different types of tasks to the task list
 */
public class AddCommand extends Command {
    private final CommandType commandType;
    private final String description;
    private final LocalDate timeOfTask;

    /**
     * Initializes the add command
     * @param commandType The type of add task; todo deadline or event
     * @param description The description of the task by the user
     * @param timeOfTask The time for the task (if given)
     */
    public AddCommand(CommandType commandType, String description, LocalDate timeOfTask) {
        this.commandType = commandType;
        this.description = description;
        this.timeOfTask = timeOfTask;
    }

    /**
     * Adds and then displays the task from the task list
     * Then writes the updated task list to computer
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        switch(commandType) {
        case TODO:
            if (description.equals("")) {
                throw new DukeException("Todo description cannot be empty lah!");
            }
            Task todo = new ToDo(description);
            return addThenSave(tasks, ui, storage, todo);
        case DEADLINE:
            if (description.equals("")) {
                throw new DukeException("Deadline description cannot be empty lah!");
            } else if (timeOfTask.equals("")) {
                throw new DukeException("Date and time of deadline cannot be empty lah!");
            } else {
                Task deadline = new Deadline(description, timeOfTask);
                return addThenSave(tasks, ui, storage, deadline);
            }
        case EVENT:
            if (description.equals("")) {
                throw new DukeException("Event description cannot be empty lah!");
            } else if (timeOfTask.equals("")) {
                throw new DukeException("Date and time of event cannot be empty lah!");
            } else {
                Task event = new Event(description, timeOfTask);
                return addThenSave(tasks, ui, storage, event);
            }
        default:
            throw new DukeException("Unknown add command lah!");
        }
    }

    /**
     * Adds then saves the task to the task list while showing an appropriate message
     */
    private String addThenSave(TaskList tasks, Ui ui, Storage storage, Task task) throws DukeException {
        try {
            tasks.add(task);
            storage.save(tasks);
            return ui.showAddTaskMessage(tasks, task);
        } catch (IOException e) {
            throw new DukeException("cannot save due to exception lah!");
        }
    }
}
