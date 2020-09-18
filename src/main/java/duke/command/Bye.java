package duke.command;

import duke.task.TaskList;

public class Bye extends Instruction {

    public Bye(TaskList taskList, String taskDescription) {
        super(taskList, taskDescription);
    }

    @Override
    public String execute() {
        taskList.setTaskListNotUpdating();
        return "Bye, Have a Great Time!";
    }
}
