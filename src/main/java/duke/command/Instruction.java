package duke.command;

import duke.DukeException;
import duke.task.TaskList;

public abstract class Instruction {

    protected String taskDescription;
    protected TaskList taskList;

    /**
     *Class constructor
     */
    public Instruction(TaskList taskList, String taskDescription) {
        this.taskDescription = taskDescription;
        this.taskList = taskList;
    }

    public String execute() throws DukeException {
        return taskList.listTask();
    }
}
