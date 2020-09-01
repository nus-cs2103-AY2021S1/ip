package duke.command;

import duke.model.Deadline;
import duke.model.Event;
import duke.model.ToDo;
import duke.task.Task;
import duke.task.TaskList;
import duke.utils.Ui;

public class AddCommand extends Command {
    String taskType;
    String info;

    public AddCommand(String taskType, String info) {
        this.taskType = taskType;
        this.info = info;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {

        Task task;

        if(taskType.equals("todo")) {
            task = new ToDo(info);
        } else if(taskType.equals("deadline")) {
            String[] deadlineParts = info.split(" /by ");
            task = new Deadline(deadlineParts[0], deadlineParts[1]);
        } else { // event
            String[] eventParts = info.split(" /at ");
            task = new Event(eventParts[0], eventParts[1]);
        }

        tasks.addTask(task);

        ui.showAddMessage(task, tasks);

    }
}
