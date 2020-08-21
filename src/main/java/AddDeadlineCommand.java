import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {
    private final String[] instructions;

    public AddDeadlineCommand(String[] instructions) {
        super();
        this.instructions = instructions;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (instructions.length < 2) {
            ui.descriptionError(Constants.TaskTypes.deadline);
            return;
        }
        String[] deadlineInfo = instructions[1].split(" /by ", 2); // [name, deadline]
        if (deadlineInfo.length < 2) {
            ui.conditionError(Constants.TaskTypes.deadline);
            return;
        }
        try {
            Task deadline = new Deadline(deadlineInfo[0], LocalDate.parse(deadlineInfo[1]));
            tasks.addTask(deadline);
            storage.save(tasks);
        } catch (DateTimeParseException e) {
            ui.invalidDateError();
        }
    }
}