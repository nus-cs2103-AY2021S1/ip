package duke.command;

import duke.task.TaskList;

public class Find extends Instruction {

    public Find(TaskList taskList, String taskDescription) {
        super(taskList, taskDescription);
    }
    @Override
    public String execute() {
        return taskList.findTask(taskDescription);
    }
}
