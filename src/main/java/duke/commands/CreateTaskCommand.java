package duke.commands;

import java.time.LocalDate;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.TaskType;
import duke.ui.Ui;


/**
 * Represents a command that creates a new task.
 * @version 1.0
 */
public class CreateTaskCommand extends Command {
    private final TaskType type;
    private final String description;
    private final LocalDate date;

    /**
     * Creates a new CreateTaskCommand object with the given task type, task description, and optionally date.
     *
     * @param type The type of the task that will be created.
     * @param description A String representation of the description of the task that will be created.
     * @param date An optional LocalDate object storing the date information if the task type is deadline or event.
     */
    public CreateTaskCommand(TaskType type, String description, LocalDate... date) {
        this.commandName = "Create";
        this.type = type;
        this.description = description;
        this.date = date.length > 0 ? date[0] : null;
        this.isExit = false;
    }

    /**
     * Creates a new task with the given type, description, with or without date.
     * Adds the new task to the specified TaskList.
     * Updates the specified storage with the changed TaskList.
     * Shows action feedback to user.
     *
     * @param list A TaskList object of which the command is executed on.
     * @param ui An UI object to interact with the user if required by the command.
     * @param storage A Storage object to write/access information to/from a file if required by the command.
     * @throws DukeException if any DukeException is thrown by the called functions.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        Task task = type == TaskType.TODO ? new Task(type, description) : new Task(type, description, date);
        list.addTask(task);
        storage.write(list.getList());
        ui.showAdd(task);
        list.printList("All");
    }
}
