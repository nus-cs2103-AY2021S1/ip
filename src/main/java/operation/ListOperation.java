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
    public String execute() {
        return "Here are your tasks:\n" + this.taskList.toString();
    }
}
