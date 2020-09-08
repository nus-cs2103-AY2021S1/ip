package command;

import duke.DukeException;
import task.TaskList;

public abstract class Instruction{

    String taskDescription;
    TaskList taskList;

    public Instruction(TaskList taskList, String taskDescription){
        this.taskDescription = taskDescription;
        this.taskList = taskList;
    }

    public String execute() throws DukeException {
        return taskList.listTask();
    }
}
