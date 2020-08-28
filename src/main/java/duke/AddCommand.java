package duke;

import java.time.DateTimeException;
import java.time.LocalDate;

public class AddCommand extends Command {
    public TaskType type;
    public String description;
    public String time;

    public AddCommand(TaskType type, String description) {
        this.type = type;
        this.description = description;
        this.time = "";
    }

    public AddCommand(TaskType type, String description, String time) {
        this.type = type;
        this.description = description;
        this.time = time;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage store) throws DukeException {
        Task newTask = null;
        try {
            switch (type) {
                case TODO:
                    newTask = new ToDo(description);
                    break;
                case DEADLINE:
                    LocalDate by = LocalDate.parse(time);
                    newTask = new Deadline(description, by);
                    break;
                case EVENT:
                    LocalDate at = LocalDate.parse(time);
                    newTask = new Event(description, at);
                    break;
                default: 
                    throw new DukeException("I don't get what you're saying :(");
            }
        } catch (DateTimeException | DukeException error) {
            System.out.println("Please provide valid date and time");
        }
        taskList.addTask(newTask);
        store.write(taskList);
        ui.showAddition(newTask, taskList);
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
