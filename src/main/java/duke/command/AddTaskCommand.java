package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.InvalidTaskException;
import duke.exception.StorageException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;

import java.time.LocalDate;

public class AddTaskCommand extends Command {
    private TaskType type;
    private String taskName;
    private LocalDate taskDate;

    public AddTaskCommand(TaskType type, String name) {
        this.type = type;
        this.taskName = name;
    }

    public AddTaskCommand(TaskType type, String name, LocalDate date) {
        this.type = type;
        this.taskName = name;
        this.taskDate = date;
    }

    @Override
    public void execute(TaskList list, Storage storage) throws StorageException, InvalidTaskException {
        switch (this.type) {
        case TODO:
            Task newTodo = list.addTask(this.taskName);
            Ui.addTaskMessage(newTodo, list.taskListSize());
            storage.appendTaskStorage(newTodo.toSaveString());
            break;
        case EVENT:
        case DEADLINE:
            Task newTask = list.addTask(this.type, this.taskName, this.taskDate);
            Ui.addTaskMessage(newTask, list.taskListSize());
            storage.appendTaskStorage(newTask.toSaveString());
            break;
        }
    }
}
