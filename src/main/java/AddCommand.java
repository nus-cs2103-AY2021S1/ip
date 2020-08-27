import java.time.LocalDate;
import java.time.LocalTime;

public class AddCommand implements Command {
    private String key;
    private String taskDescription;
    private LocalDate date;
    private LocalTime time;
    
    AddCommand(String key, String taskDescription) {
        this.key = key;
        this.taskDescription = taskDescription;
        this.date = null;
        this.time = null;
    }
    
    AddCommand(String key, String taskDescription, LocalDate date, LocalTime time) {
        this.key = key;
        this.taskDescription = taskDescription;
        this.date = date;
        this.time = time;
    }
    
    @Override 
    public void execute(TaskList tasks, Ui ui) {
        if (CommandKey.equalsCommandKey(key, CommandKey.TODO)) {
            Task task = new ToDo(taskDescription);
            tasks.add(task);
            ui.displayAddTaskSuccess(task, tasks.size());
        } else if (CommandKey.equalsCommandKey(key, CommandKey.DEADLINE)) {
            Task task = new Deadline(taskDescription, date, time);
            tasks.add(task);
            ui.displayAddTaskSuccess(task, tasks.size());
        } else {
            Task task = new Event(taskDescription, date, time);
            tasks.add(task);
            ui.displayAddTaskSuccess(task, tasks.size());
        }
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
