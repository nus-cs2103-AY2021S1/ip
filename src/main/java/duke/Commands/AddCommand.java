package duke.Commands;

import duke.Exceptions.DukeException;
import duke.Storage.Storage;
import duke.Tasks.TaskList;
import duke.Ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;

public class AddCommand extends Command{

    private String taskType;
    private String description;
    private LocalTime localTime = null;
    private LocalDate localDate = null;

    public AddCommand(String taskType, String description) {
        this.taskType = taskType;
        this.description = description;
    }

    public AddCommand(String taskType, String description, LocalDate localDate, LocalTime localTime) {
        this.taskType = taskType;
        this.description = description;
        this.localDate = localDate;
        this.localTime = localTime;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        switch (taskType) {
        case "todo" :
            tasks.addTodo(ui, this.description);
            storage.Save(tasks.convertToFile());
            break;
        case "event" :
            tasks.addEvent(ui, this.description, this.localDate, this.localTime);
            storage.Save(tasks.convertToFile());
            break;
        case "deadline" :
            tasks.addDeadline(ui, this.description, this.localDate, this.localTime);
            storage.Save(tasks.convertToFile());
            break;
        }
    }
}
