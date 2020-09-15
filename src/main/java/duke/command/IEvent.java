package duke.command;

import duke.DukeException;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

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
