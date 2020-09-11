package duke.command;

import duke.task.TaskList;

public class List extends Instruction {

    public List(TaskList taskList, String taskDescription) {
        super(taskList, taskDescription);
    }

    @Override
    public String execute() {
        return taskList.listTask();
    }
}
