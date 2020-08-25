package viscount.command;

import viscount.*;
import viscount.task.*;
import viscount.exception.ViscountException;

import java.time.LocalDateTime;

public class AddCommand extends Command {
    private TaskType taskType;
    private String description;
    private LocalDateTime dateTime;
    
    public AddCommand(TaskType taskType, String description, LocalDateTime dateTime) {
        this.taskType = taskType;
        this.description = description;
        this.dateTime = dateTime;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ViscountException {
        Task newTask = getNewTask();
        tasks.add(newTask);
        storage.saveToDisk(tasks.getTasks());
        ui.showAdd(newTask, tasks.getTasksSize());
    }
    
    private Task getNewTask() {
        Task newTask = null;
        
        switch(taskType) {
        case TODO:
            newTask = new Todo(description, false);
            break;
        case DEADLINE:
            newTask = new Deadline(description, false, dateTime);
            break;
        case EVENT:
            newTask = new Event(description, false, dateTime);
            break;
        default:
            break;
        }
        
        return newTask;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
