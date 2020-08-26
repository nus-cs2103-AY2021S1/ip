package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

import java.time.LocalDate;

public class CreateTaskCommand extends Command {
    private final String type;
    private final String description;
    private final LocalDate date;

    public CreateTaskCommand(String type, String description, LocalDate... date) {
        this.commandName = "Create";
        this.type = type;
        this.description = description;
        this.date = date.length > 0 ? date[0] : null;
        this.isExit = false;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        Task task = type.equals("todo") ? new Task(type,description) : new Task(type, description, date);
        list.addTask(task);
        storage.write(list.getList());
        ui.showAdd(task);
        list.printList("All");
    }
}
