package command;

import duke.DukeException;
import task.Event;
import task.Task;
import task.TaskList;

public class IEvent extends Instruction{


    public IEvent(TaskList taskList, String taskDescription) {
        super(taskList, taskDescription);
    }

    @Override
    public String execute() throws DukeException {
        Task newTask = Event.create(taskDescription);
        return taskList.addTask(newTask, true);
    }
}
