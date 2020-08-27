package rogue.logic.directives;

import rogue.model.task.Deadline;
import rogue.model.task.Event;
import rogue.model.task.Task;
import rogue.model.task.TaskList;
import rogue.model.task.Todo;

import rogue.logic.Report;

import rogue.storage.Storage;
import rogue.storage.exceptions.StorageException;

import rogue.ui.Ui;

import java.time.LocalDate;

/**
 * Adds a {@code Task} to the {@code TaskList}.
 */
public class AddDirective implements Executable {
    /** Message to add to a {@code Report} upon adding a {@code Task} successfully. */
    private final String MESSAGE_ADD_SUCCESS = "sInCe yOu'rE So hElPlEsS, i'lL ReMeMbEr \"%s\" FoR YoU.\n"
            + "yOu hAvE MaDe mE ReMeMbEr %d tAsK(s).";

    private final Action action;
    private final String description;
    private final LocalDate date;

    /**
     * Constructs an {@code AddDirective}.
     *
     * @param action        The type of {@code Task} to add.
     * @param description   The description of the {@code Task}.
     */
    public AddDirective(Action action, String description) {
        this.action = action;
        this.description = description;
        this.date = null;
    }

    /**
     * Constructs an {@code AddDirective}.
     *
     * @param action        The type of {@code Task} to add.
     * @param description   The description of the {@code Task}.
     * @param date          The date of the {@code Deadline} or {@code Event}.
     */
    public AddDirective(Action action, String description, LocalDate date) {
        this.action = action;
        this.description = description;
        this.date = date;
    }

    /**
     * Adds a {@code Todo}, {@code Deadline}, or {@code Event} to the {@code TaskList}.
     * Which {@code Task} is added depends on the {@code Action}: {@code Action.ADD_TODO},
     * {@code Action.ADD_DEADLINE}, and {@code Action.ADD_EVENT} respectively.
     *
     * All {@code Task} are then saved to {@code Storage}.
     *
     * @param storage   An instance of {@code Storage}.
     * @param tasks     The {@code TaskList} to which a {@code Task} is added.
     * @param ui        An instance of {@code Ui}.
     * @return A {@code Report} with a success message
     * @throws StorageException if data cannot be saved to file.
     */
    @Override
    public Report execute(Storage storage, TaskList tasks, Ui ui) throws StorageException {
        Task task;

        switch (action) {
        case ADD_DEADLINE:
            task = new Deadline(description, date);
            break;

        case ADD_EVENT:
            task = new Event(description, date);
            break;

        default:
            task = new Todo(description);
            break;
        }

        tasks.add(task);

        storage.save(tasks.getTaskList());

        return new Report(String.format(MESSAGE_ADD_SUCCESS, task, tasks.count()));
    }
}
