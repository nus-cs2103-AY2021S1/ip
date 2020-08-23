import exceptions.DukeException;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {

    private final String description;
    private final String dateStr;

    public DeadlineCommand(String description, String dateStr) {
        this.description = description;
        this.dateStr = dateStr;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        LocalDateTime deadlineDateTime = DateParser.parseString(dateStr);
        Deadline deadline = new Deadline(description, deadlineDateTime);
        tasks.addTask(deadline);
        ui.printMessage(String.format("Okay, I've added the following deadline: \n %s", deadline.toString()));
        storage.updateTasks(tasks.getListOfTasks());
    }
}
