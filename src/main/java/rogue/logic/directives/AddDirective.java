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

public class AddDirective implements Executable {
    private final String MESSAGE_ADD_SUCCESS = "sInCe yOu'rE So hElPlEsS, i'lL ReMeMbEr \"%s\" FoR YoU.\n"
            + "yOu hAvE MaDe mE ReMeMbEr %d tAsK(s).";

    private final Action action;
    private final String description;
    private final LocalDate date;

    public AddDirective(Action action, String description) {
        this.action = action;
        this.description = description;
        this.date = null;
    }

    public AddDirective(Action action, String description, LocalDate date) {
        this.action = action;
        this.description = description;
        this.date = date;
    }

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
