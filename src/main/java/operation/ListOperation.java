package operation;

import task.TaskList;

public class ListOperation extends Operation {
    private final TaskList taskList;

    public ListOperation(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute() {
        System.out.println("Here are your tasks:");
        this.taskList.printTaskList();
    }
}
