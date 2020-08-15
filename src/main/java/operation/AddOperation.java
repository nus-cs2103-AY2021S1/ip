package operation;

import exception.DukeException;
import task.Deadline;
import task.Task;
import task.TaskStorage;
import task.Event;
import task.Todo;

public abstract class AddOperation extends Operation {
    protected TaskStorage taskStorage;

    AddOperation(String[] commands, TaskStorage taskStorage) {
        super(commands);
        this.taskStorage = taskStorage;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public abstract Task createTask() throws DukeException;

    @Override
    public void execute() {
        try {
            Task newTask = createTask();
            this.taskStorage.addTask(newTask);
            System.out.println("I have added the task:\n" + newTask);
            String capacity = String.format("You now have %d tasks.",
                    this.taskStorage.getCurrCapacity());
            System.out.println(capacity);
        } catch (DukeException exception) {
            System.out.println(exception.getMessage());
        }
    }
}