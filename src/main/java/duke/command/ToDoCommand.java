package duke.command;

import java.time.LocalDateTime;

import duke.Storage;
import duke.TaskList;
import duke.task.TaskType;
import duke.Ui;
import duke.exception.DukeException;

public class ToDoCommand extends Command {
    private String task;

    public ToDoCommand(String task) {
        this.task = task;
    }

    /**
     * Adds a ToDo task with task to the TaskList.
     *
     * @param tasks The TaskList.
     * @param ui The Ui.
     * @param storage The Storage.
     * @throws DukeException
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(task, LocalDateTime.now(), TaskType.TODO);
        return ("Added ToDo '" + task + "' to your list!");
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ToDoCommand;
    }
}
