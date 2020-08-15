package operation;

import task.Task;
import task.TaskStorage;
import task.TaskFactory;

public class AddOperation extends Operation {
    private TaskStorage taskStorage;

    public AddOperation(String[] commands, TaskStorage taskStorage) {
        super(commands);
        this.taskStorage = taskStorage;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private Task createTask() {
        switch(this.commands[0]) {
            case "deadline":
                return TaskFactory.createDeadline(this.commands);
            case "event":
                return TaskFactory.createEvent(this.commands);
            default :
                return TaskFactory.createToDo(this.commands);
        }
    }

    @Override
    public void execute() {
        Task newTask = createTask();
        this.taskStorage.addTask(newTask);
        System.out.println("I have added the task: \n" + newTask);
        String capacity = String.format("You now have %d tasks.", this.taskStorage.getCurrCapacity());
        System.out.println(capacity);
    }
}