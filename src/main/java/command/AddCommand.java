package command;

import task.Task;
import task.ToDoTask;
import task.DeadlineTask;
import task.EventTask;
import util.Storage;
import util.TaskList;
import util.Ui;

import java.io.IOException;

public class AddCommand extends Command {
    private enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }
    
    private final TaskType taskType;
    private final String taskDesc;
    private final String taskDate;
    
    public AddCommand(String type, String desc) {
        this.taskType = TaskType.valueOf(type);
        this.taskDesc = desc;
        this.taskDate = "";
    }

    public AddCommand(String type, String desc, String date) {
        this.taskType = TaskType.valueOf(type);
        this.taskDesc = desc;
        this.taskDate = date;
    }

    public void execute(TaskList lst, Ui ui, Storage storage) {
        try {
            Task task;
            int taskNum = lst.size();
            switch (taskType) {
                case TODO:
                    task = new ToDoTask(taskDesc, false);
                    lst.add(task);
                    storage.addLine("TODO | 0 | " + taskDesc);
                    ui.showAddTask(task, taskNum);
                    break;
                case EVENT:
                    task = new EventTask(taskDesc, false, taskDate);
                    lst.add(task);
                    storage.addLine("EVENT | 0 | " + taskDesc + "| " + taskDate);
                    ui.showAddTask(task, taskNum);
                    break;
                case DEADLINE:
                    task = new DeadlineTask(taskDesc, false, taskDate);
                    lst.add(task);
                    storage.addLine("DEADLINE | 0 | " + taskDesc + "| " + taskDate);
                    ui.showAddTask(task, taskNum);
                    break;
            }
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }
}
