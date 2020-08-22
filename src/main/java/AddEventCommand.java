import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddEventCommand extends Command {
    private final String[] instructions;

    public AddEventCommand(String[] instructions) {
        super();
        this.instructions = instructions;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[] eventInfo = instructions[1].split(" /at ", 2); // [name, date]
        if (eventInfo.length < 2) {
            ui.conditionError(Constants.TaskTypes.event);
            return;
        }
        try {
            Task event = new Event(eventInfo[0], LocalDate.parse(eventInfo[1]));
            tasks.addTask(event);
            storage.save(tasks);
        } catch (DateTimeParseException e) {
            ui.invalidDateError();
        }
    }
}