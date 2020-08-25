package viscount.command;

import viscount.*;
import viscount.exception.ViscountIOException;
import viscount.task.*;

import java.time.LocalDateTime;

/**
 * Represents an add command.
 */
public class AddCommand extends Command {
    private TaskType taskType;
    private String description;
    private LocalDateTime dateTime;
    
    public AddCommand(TaskType taskType, String description, LocalDateTime dateTime) {
        this.taskType = taskType;
        this.description = description;
        this.dateTime = dateTime;
    }

    /**
     * Executes the add command.
     * 
     * @param taskList Task list where tasks are stored.
     * @param ui Ui to display response.
     * @param storage Storage to save changes to disk.
     * @throws ViscountIOException If exception occurs with writing to disk.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ViscountIOException {
        Task newTask = getNewTask();
        taskList.add(newTask);
        storage.saveToDisk(taskList.getTasks());
        ui.showAdd(newTask, taskList.getTasksSize());
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
