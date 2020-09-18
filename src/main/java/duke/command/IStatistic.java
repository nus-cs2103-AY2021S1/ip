package duke.command;

import duke.task.TaskList;

public class IStatistic extends Instruction {

    public IStatistic(TaskList taskList, String taskDescription) {
        super(taskList, taskDescription);
    }

    public String execute() {
        return taskList.getStatistic();
    }
}
